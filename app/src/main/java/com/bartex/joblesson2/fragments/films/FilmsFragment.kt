package com.bartex.joblesson2.fragments.films

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bartex.joblesson2.R
import com.bartex.joblesson2.entity.Constants
import com.bartex.joblesson2.entity.films.Films
import com.bartex.joblesson2.entity.films.FilmsFromNet
import com.bartex.joblesson2.fragments.adapters.FilmsRVAdapter

class FilmsFragment : Fragment() {

    private lateinit var rvFilms:RecyclerView
    private lateinit var tvEmpty:TextView
    private lateinit var textViewStatus:TextView
    private lateinit var adapter:FilmsRVAdapter
    private lateinit var navController: NavController
    private lateinit var progressBar: ProgressBar
    private lateinit var buttonLoadElse: Button
    private lateinit var buttonLoadBefore: Button
    private lateinit var buttonLoadOne: Button
    private  var page = 1
    private  var totalPages = 1
    private var position = 0

    private val filmsViewModel by lazy {
        ViewModelProvider(requireActivity()).get(FilmsViewModel::class.java)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_films, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        initAdapter()
        initButtonListener()

        //восстанавливаем номер страницы после поворота
        page = filmsViewModel.getPage()
        Log.d(TAG, "***FilmsFragment onViewCreated page = $page")

        filmsViewModel. loadFilmsSealed(page) //загружаем данные
        filmsViewModel.getFilmsSealed().observe(viewLifecycleOwner, Observer { filmSealed->
            position =  filmsViewModel.getPosition()
            renderDataSealed(filmSealed)
        })
    }

    //запоминаем  позицию списка, на которой сделан клик - на случай поворота экрана
    override fun onPause() {
        super.onPause()
        //определяем первую видимую позицию
        val manager = rvFilms.layoutManager as GridLayoutManager
        val firstPosition = manager.findFirstVisibleItemPosition()
        filmsViewModel.savePosition(firstPosition)
        filmsViewModel.savePage(page) // запоминаем страницу на случай поворота
        Log.d(TAG, "FilmsFragment onPause firstPosition = $firstPosition")
    }

    private fun initButtonListener() {
        buttonLoadElse.setOnClickListener {
            filmsViewModel.savePosition(0)
            filmsViewModel.loadFilmsSealed(page+1)
        }

        buttonLoadBefore.setOnClickListener {
            filmsViewModel.savePosition(0)
            filmsViewModel. loadFilmsSealed(page-1)
        }

        buttonLoadOne.setOnClickListener {
            filmsViewModel.savePosition(0)
            filmsViewModel. loadFilmsSealed(1)
        }
    }

    private fun renderDataSealed(filmSealed: FilmSealed) {
        when(filmSealed){
            is FilmSealed.Success ->{
                renderLoadingStop()
                renderData(filmSealed.films)
            }
            is FilmSealed.Error ->{
                renderLoadingStop()
                renderError(filmSealed.error)
            }
            is FilmSealed.Loading ->{
                renderLoadingStart()
            }
        }

    }

    private fun renderLoadingStart() {
        progressBar.visibility = View.VISIBLE
    }

    private fun renderLoadingStop() {
        progressBar.visibility = View.GONE
    }

    private fun renderError(error: Throwable) {
        Toast.makeText(requireActivity(), error.message, Toast.LENGTH_LONG).show()
    }

    private fun renderData(films: FilmsFromNet) {
        page = films.page //запоминаем
        totalPages = films.total_pages
        if(page <= 1){
            buttonLoadBefore.isEnabled = false
            buttonLoadOne.isEnabled = false
            buttonLoadElse.isEnabled = true
        }else if(page >= totalPages){
            buttonLoadBefore.isEnabled = true
            buttonLoadOne.isEnabled = true
            buttonLoadElse.isEnabled = false
        }else{
            buttonLoadBefore.isEnabled = true
            buttonLoadOne.isEnabled = true
            buttonLoadElse.isEnabled = true
        }
        val listOfFilms:List<Films>? = films.results
        textViewStatus.text = String.format(getString(R.string.pageLoading), page, films.total_pages )

        listOfFilms?. let{
            adapter.listOfFilms = it
            rvFilms.layoutManager?.scrollToPosition(position) //крутим в запомненную позицию списка
            Log.d(TAG, "FilmsFragment renderData scrollToPosition = $position")
        }
    }

    private fun initViews(view: View) {
        rvFilms = view.findViewById(R.id.rv_films)
        tvEmpty = view.findViewById(R.id.empty_view_films)
        textViewStatus = view.findViewById(R.id.textViewStatus)
        navController = Navigation.findNavController(view)
        progressBar = view.findViewById(R.id.progressBarFilms)
        buttonLoadElse = view.findViewById(R.id.buttonLoadElse)
        buttonLoadBefore = view.findViewById(R.id.buttonLoadBefore)
        buttonLoadOne = view.findViewById(R.id.buttonLoadOne)
    }

    private fun initAdapter() {
        if(resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT){
            rvFilms.layoutManager = GridLayoutManager(requireActivity(), 2)
        }else{
            rvFilms.layoutManager = GridLayoutManager(requireActivity(), 4)
        }
        adapter = FilmsRVAdapter(getOnFilmListener())
        rvFilms.adapter = adapter
    }

    private fun getOnFilmListener(): FilmsRVAdapter.OnFilmClickListener =
        object :FilmsRVAdapter.OnFilmClickListener{
            override fun onFilmClick(film: Films) {

                val bundle = Bundle()
                bundle.putInt(Constants.FILM_ID, film.id)
                navController.navigate(R.id.DetailsOfFilmFragment, bundle)
            }
        }

    companion object {
        const val TAG = "33333"
    }
}