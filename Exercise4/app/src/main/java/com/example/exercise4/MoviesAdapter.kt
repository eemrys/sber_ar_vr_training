package com.example.exercise4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_item_view.*

class MoviesAdapter(private val data: List<Movie>,
                    private val clickListener: (movie: Movie) -> Unit) :
    RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_item_view, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(data[position])
    }

    inner class MovieViewHolder (override val containerView: View):
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(movie: Movie) {
            containerView.setOnClickListener {
                clickListener(movie)
            }
            txtvTitle.text = movie.title
            txtvSummary.text = movie.summary
            imgvPoster.setMoviePoster(movie.posterId)
        }
    }
}