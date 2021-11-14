package com.bartex.joblesson2.fragments.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bartex.joblesson2.R
import com.bartex.joblesson2.entity.Constants
import com.bartex.joblesson2.entity.details.Persons
import com.squareup.picasso.Picasso

class ActorsRVAdapter():RecyclerView.Adapter<ActorsRVAdapter.ViewHolder>() {

    var listOfActors:List<Persons> = listOf()
    set(value) {
        field =value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorsRVAdapter.ViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_of_actors,parent,false)
         return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ActorsRVAdapter.ViewHolder, position: Int) {
        holder.bind(listOfActors[position])
    }

    override fun getItemCount(): Int {
        return listOfActors.size
    }

    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view) {
        private val ivActors:ImageView = view.findViewById(R.id.iv_actors)
        private val tvActorsName:TextView = view.findViewById(R.id.tv_actors_name)
        private val tvActorsCharacter:TextView = view.findViewById(R.id.tv_actors_character)

        fun bind(actor:Persons){
            tvActorsName.text  = actor.original_name
            tvActorsCharacter.text  = actor.character

            actor.profile_path?. let{
               val actorUrl = Constants.imageUrl + it
                Picasso.get()
                    .load(actorUrl)
                    .placeholder(R.drawable.post)
                    .error(R.drawable.mistake)
                    .into(ivActors)
            }
        }
    }
}
