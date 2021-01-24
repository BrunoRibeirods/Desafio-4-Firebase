package com.digitalhouse.desafiofirebase.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.digitalhouse.desafiofirebase.R
import com.digitalhouse.desafiofirebase.models.Game

class HomeAdapter(private val listOfGames: List<Game>): RecyclerView.Adapter<HomeAdapter.HomeCardViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeCardViewHolder {
        val itemview = LayoutInflater.from(parent.context).inflate(R.layout.item_games_home, parent, false)

        return HomeCardViewHolder(itemview)
    }

    override fun onBindViewHolder(holder: HomeCardViewHolder, position: Int) {
        val current = listOfGames[position]

        holder.title.text = current.title
        holder.date.text = current.date.toString()

        Glide.with(holder.itemView).asBitmap()
                .load(current.img)
                .into(holder.img)

    }

    override fun getItemCount() = listOfGames.size

    inner class HomeCardViewHolder(view: View): RecyclerView.ViewHolder(view){
        val title = view.findViewById<TextView>(R.id.tv_title_game)
        val date = view.findViewById<TextView>(R.id.tv_date_game)
        val img = view.findViewById<ImageView>(R.id.iv_img_game)
    }

}