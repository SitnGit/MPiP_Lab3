package com.example.lab3.adapters


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lab3.R
import com.example.lab3.models.Movie

class MovieRecyclerViewAdapter(private val context: Context, private var movies: MutableList<Movie>, private val onMovieClick: (position: Int) -> Unit) :
    RecyclerView.Adapter<MovieRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(view: View, private val onMovieClick: (position: Int) -> Unit) : RecyclerView.ViewHolder(view), View.OnClickListener{
        val title: TextView = view.findViewById(R.id.Title)
        val poster: ImageView = view.findViewById(R.id.image)
        val year: TextView = view.findViewById(R.id.Year)

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position = absoluteAdapterPosition
            onMovieClick(position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie, parent, false)
        return ViewHolder(view) { position ->
            onMovieClick(
                position
            )
        }

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentMovie = movies[position]

        Glide.with(context).load(currentMovie.Poster).into(holder.poster)
        holder.title.text = "title: ${currentMovie.Title}"
        holder.year.text = "year: ${currentMovie.Year}"
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun updateMovies(data: MutableList<Movie>){
        this.movies = data
        this.notifyDataSetChanged()
    }
}