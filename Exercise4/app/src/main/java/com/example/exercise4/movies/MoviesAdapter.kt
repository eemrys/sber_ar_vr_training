package com.example.exercise4.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.exercise4.data.Movie
import com.example.exercise4.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_item_view.*

class MoviesAdapter(private val clickListener: (movie: Movie) -> Unit) :
    RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    var data = emptyList<Movie>()

    class MovieViewHolder (private val clickListener: (movie: Movie) -> Unit,
                           override val containerView: View):
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(movie: Movie) {
            movie.apply {
                txtvTitle.text = title
                txtvSummary.text = summary
                imgvPoster.setImageResource(poster)

                containerView.setOnClickListener {
                    clickListener(this)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_item_view, parent, false)
        return MovieViewHolder(clickListener, view)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(data[position])
    }
}