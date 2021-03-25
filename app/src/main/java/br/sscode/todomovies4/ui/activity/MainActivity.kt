package br.sscode.todomovies4.ui.activity

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import br.sscode.todomovies4.R
import br.sscode.todomovies4.data.model.Movie
import br.sscode.todomovies4.data.remote.api.APIClientKeys
import br.sscode.todomovies4.ui.activity.base.MainContract
import br.sscode.todomovies4.ui.activity.extension.getPopularityDescription
import br.sscode.todomovies4.ui.activity.extension.getVoteCountDescription
import br.sscode.todomovies4.ui.activity.presenter.MainPresenter
import com.bumptech.glide.Glide
import java.lang.Exception

class MainActivity : AppCompatActivity(), MainContract.View {

    // Presenter da Activity
    private val presenter: MainPresenter = MainPresenter()

    // Views da activity
    private var movieImage: ImageView? = null
    private var movieTitle: TextView? = null
    private var movieVoteCount: TextView? = null
    private var moviePopularityViews: TextView? = null
    private var similarMovies: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Vinculamos a view ao presenter
        presenter.onAttach(this)

        // Chamamos o método para obter o filme
        presenter.getMovie()
    }

    /**
     *  Vinculamos todos os campos da activity as variaveis
     * */
    override fun bindViews() {
        movieImage = findViewById(R.id.movieImage)
        movieTitle = findViewById(R.id.movieTitle)
        movieVoteCount = findViewById(R.id.movieVoteCount)
        moviePopularityViews = findViewById(R.id.moviePopularityViews)
        similarMovies = findViewById(R.id.similarMovies)
    }

    override fun updateView(movie: Movie?) {
        try {
            // Carregamos a imagem no campo
            Glide.with(movieImage!!).load(APIClientKeys.BASE_URL_MOVIE_IMAGE_W780 + movie?.posterPath).into(movieImage!!)

            // Carregamos os demais campos
            movieTitle?.text = movie?.title
            movieVoteCount?.text = movie?.getVoteCountDescription()
            moviePopularityViews?.text = movie?.getPopularityDescription()
        } catch (exception: Exception) {
            Log.e("ERRO", exception.message!!)
        }
    }

    override fun showProgressDialog() {
    }

    override fun hideProgressDialog() {
    }
}