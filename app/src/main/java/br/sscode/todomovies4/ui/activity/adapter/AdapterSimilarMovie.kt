package br.sscode.todomovies4.ui.activity.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.sscode.todomovies4.R
import br.sscode.todomovies4.data.model.SimilarMovie
import br.sscode.todomovies4.data.remote.api.APIClientKeys
import br.sscode.todomovies4.ui.activity.extension.getGenresDescription
import br.sscode.todomovies4.ui.activity.extension.getYearReleaseDate
import com.bumptech.glide.Glide

/**
 *  Classe Adapter para a lista de SimilarMovies
 * */
class AdapterSimilarMovie(
    private val similarMovies: MutableList<SimilarMovie>
) : RecyclerView.Adapter<AdapterSimilarMovie.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {

        // Criamos um modelo para o holder
        val viewCriada = LayoutInflater.from(parent.context).inflate(R.layout.list_item_movie, parent, false)

        return Holder(viewCriada)
    }

    override fun getItemCount(): Int = similarMovies.size

    override fun onBindViewHolder(holder: Holder, position: Int) {

        // Obtemos o movie da posicao
        val movie: SimilarMovie = similarMovies[position]

        // Vinculamos o movie na holder
        holder.bind(movie)
    }

    /**
     *  Classe holder de Apoio para o RecyclerView
     * */
    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        // Todos os campos da view
        private val movieImage = itemView.findViewById<ImageView>(R.id.movieImage)
        private val movieTitle = itemView.findViewById<TextView>(R.id.movieTitle)
        private val movieReleaseDate = itemView.findViewById<TextView>(R.id.movieReleaseDate)
        private val movieGenres = itemView.findViewById<TextView>(R.id.movieGenres)

        /**
         *  Método usado para carregar os dados no respectivo campos
         * */
        fun bind(movie: SimilarMovie) {

            // Carregamos a imagem no campo
            Glide.with(movieImage).load(APIClientKeys.BASE_URL_MOVIE_IMAGE_W500 + movie.posterPath).into(movieImage)

            // Definimos o restante dos campos
            movieTitle.text = movie.title
            movieReleaseDate.text = movie.getYearReleaseDate()
            movieGenres.text = movie.getGenresDescription()
        }
    }
}