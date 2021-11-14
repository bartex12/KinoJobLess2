package com.bartex.joblesson2.fragments.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bartex.joblesson2.R
import com.bartex.joblesson2.entity.Constants
import com.bartex.joblesson2.entity.films.Films
import com.squareup.picasso.Picasso

class FilmsRVAdapter(
        private val onFilmClickListener: OnFilmClickListener,
):RecyclerView.Adapter<FilmsRVAdapter.ViewHolder>() {

    var listOfFilms:List<Films> = listOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    interface OnFilmClickListener{
        fun onFilmClick(film: Films)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_of_films,parent, false )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listOfFilms[position])
    }

    override fun getItemCount(): Int {
       return listOfFilms.size
    }

   inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val ivFilm = view.findViewById<ImageView>(R.id.iv_film)
        private val tvFilmName = view.findViewById<TextView>(R.id.tv_film_name)
        private val tvFilmDate = view.findViewById<TextView>(R.id.tv_film_description)
        private val llFilm = view.findViewById<ConstraintLayout>(R.id.ll_film)

        @SuppressLint("SetTextI18n")
        fun bind(film: Films){
            tvFilmName.text = film.title
            val date: List<String>? =film.release_date?.split("-")
            tvFilmDate.text = "${date?.get(2)}-${date?.get(1)}-${date?.get(0)}"

            var filmUrl = ""
            film.poster_path?. let{
                filmUrl = Constants.imageUrl + film.poster_path
            }
                Picasso.get()
                    .load(filmUrl)
                    .placeholder(R.drawable.post)
                    .error(R.drawable.mistake)
                    .into(ivFilm)

                llFilm.setOnClickListener {
                    onFilmClickListener.onFilmClick(film)
                }
        }
    }
}