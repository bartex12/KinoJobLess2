   package com.bartex.joblesson2.fragments.details

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bartex.joblesson2.R
import com.bartex.joblesson2.entity.Constants
import com.bartex.joblesson2.entity.details.DetailsFromNet
import com.bartex.joblesson2.entity.details.DetailsSealed
import com.bartex.joblesson2.entity.details.Persons
import com.bartex.joblesson2.fragments.adapters.ActorsRVAdapter
import com.squareup.picasso.Picasso

class DetailsFragment : Fragment() {

    private var filmId:Int? = 550
    private val viewModelDetails by lazy{
        ViewModelProvider(requireActivity()).get(DetailsViewModel::class.java)
    }
    private lateinit var progressBar: ProgressBar
    private lateinit var ivFilmDetails: ImageView
    private lateinit var ivPosterDetail: ImageView
    private lateinit var textViewTitleDetail: TextView
    private lateinit var tvDetailRelease: TextView
    private lateinit var runtimeDetail: TextView
    private lateinit var genresDetail: TextView
    private lateinit var tvDetailDescription: TextView
    private lateinit var rvActors: RecyclerView
    private lateinit var adapter:ActorsRVAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        filmId = arguments?.getInt(Constants.FILM_ID, 550)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        initAdapter()

        filmId?. let{viewModelDetails. loadDetailSealed(it) }
        viewModelDetails. getDetailsSealed()
                .observe(viewLifecycleOwner, { details->
                    renderDataSealed(details)
                })
    }

    private fun initViews(view: View) {
        progressBar = view.findViewById(R.id.progressBarDetails)
        ivFilmDetails = view.findViewById(R.id.iv_film_details)
        textViewTitleDetail = view.findViewById(R.id.textViewTitleDetail)
        ivPosterDetail = view.findViewById(R.id.ivPosterDetail)
        tvDetailRelease = view.findViewById(R.id.tv_detail_release)
        runtimeDetail = view.findViewById(R.id.runtimeDetail)
        genresDetail = view.findViewById(R.id.genresDetail)
        tvDetailDescription = view.findViewById(R.id.tv_detail_description)
        rvActors = view.findViewById(R.id.rv_actors)

    }

    private fun initAdapter() {
        rvActors.layoutManager = LinearLayoutManager(requireActivity(),
            LinearLayoutManager.HORIZONTAL, false)
        adapter = ActorsRVAdapter()
        rvActors.adapter = adapter
    }

    private fun renderDataSealed(detailsSealed: DetailsSealed?) {
        when(detailsSealed){
            is DetailsSealed.Success -> {
                renderLoadingStop()
                renderData(detailsSealed.details)
            }
            is DetailsSealed.Error -> {
                renderLoadingStop()
                renderError(detailsSealed.error)
            }
            is DetailsSealed.Loading -> {
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

    @SuppressLint("SetTextI18n")
    private fun renderData(details: DetailsFromNet) {
//        Log.d(TAG, "DetailsFragment renderData " +
//                "original_name = ${details.credits?.cast?.get(0)?.original_name} " +
//                " character = ${details.credits?.cast?.get(0)?.character}")

        val listOfActors:List<Persons>? = details.credits?.cast
        listOfActors?. let{
            adapter.listOfActors = it
        }

        textViewTitleDetail.text =details.title
        tvDetailDescription.text =details.overview
        genresDetail.text =details.genres[0].name

        val date: List<String>? =details.release_date?.split("-")
        tvDetailRelease.text = "${date?.get(2)}-${date?.get(1)}-${date?.get(0)}"

        details.runtime?. let{runtimeDetail.text = String.format("%s час %s мин",it/60, it%60)}

        details.backdrop_path?. let{
            val filmUrlBackdrop = Constants.imageUrl + it
            Picasso.get()
                .load(filmUrlBackdrop)
                .placeholder(R.drawable.post)
                .error(R.drawable.mistake)
                .into(ivFilmDetails)
        }
        details.poster_path?. let{
            val filmUrlPoster = Constants.imageUrl + it
            Picasso.get()
                .load(filmUrlPoster)
                .placeholder(R.drawable.post)
                .error(R.drawable.mistake)
                .resize(100, 200)
                .into(ivPosterDetail)
        }
    }
}