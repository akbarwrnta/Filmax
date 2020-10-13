package com.example.filmax.home.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.filmax.R
import com.example.filmax.model.Film

class ComingSoonAdapter(private var data: List<Film>,
                        private  val Listener: (Film) -> Unit)
    : RecyclerView.Adapter<ComingSoonAdapter.ViewHolder>() {

    lateinit var contextAdapter : Context


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ComingSoonAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context

        val inflatedView = layoutInflater.inflate(R.layout.row_item_coming_soon, parent, false)
        return ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: ComingSoonAdapter.ViewHolder, position: Int) {
        holder.bindItem(data[position], Listener, contextAdapter)
    }

    override fun getItemCount(): Int = data.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val tvTitle:TextView = view.findViewById(R.id.tv_judul)
        private val tvGenre:TextView = view.findViewById(R.id.tv_genre)
        private val tvRate:TextView = view.findViewById(R.id.tv_rating)
        private val tvDirector:TextView = view.findViewById(R.id.tv_director)

        private val tvImage:ImageView = view.findViewById(R.id.iv_poster_image)

        fun bindItem(data: Film, Listener: (Film) -> Unit, context: Context){
            tvTitle.setText(data.judul)
            tvGenre.setText(data.genre)
            tvRate.setText(data.rating)
            tvDirector.setText(data.director)

            Glide.with(context)
                .load(data.poster)
                .into(tvImage)

            itemView.setOnClickListener{
                Listener(data)
            }
        }

    }

}
