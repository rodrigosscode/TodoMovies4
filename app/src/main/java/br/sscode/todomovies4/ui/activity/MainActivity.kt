package br.sscode.todomovies4.ui.activity

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.sscode.todomovies4.R
import br.sscode.todomovies4.data.model.Movie
import br.sscode.todomovies4.data.remote.api.APIClientKeys
import br.sscode.todomovies4.ui.activity.adapter.AdapterSimilarMovie
import br.sscode.todomovies4.ui.activity.base.MainContract
import br.sscode.todomovies4.ui.activity.extension.getPopularityDescription
import br.sscode.todomovies4.ui.activity.extension.getVoteCountDescription
import br.sscode.todomovies4.ui.activity.presenter.MainPresenter
import br.sscode.todomovies4.ui.activity.util.LogConsts
import br.sscode.todomovies4.ui.activity.util.ProgressBarHandler
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity(), MainContract.View {

    // Presenter da Activity
    private val presenter: MainPresenter = MainPresenter()

    // Views da activity
    private var toolbar: Toolbar? = null
    private var movieImage: ImageView? = null
    private var movieLike: ImageView? = null
    private var movieTitle: TextView? = null
    private var movieVoteCount: TextView? = null
    private var moviePopularityViews: TextView? = null
    private var similarMovies: RecyclerView? = null
    private var handlerProgress: ProgressBarHandler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Definimos a barra de status transparente
        setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
        window.statusBarColor = Color.TRANSPARENT

        // Definimos o layout da view
        setContentView(R.layout.activity_main)

        // Vinculamos a view ao presenter
        presenter.onAttach(this)
    }

    /**
     * Usado para definir atributos da View
     * */
    private fun setWindowFlag(bits: Int, on: Boolean) {
        val win = window
        val winParams = win.attributes

        // Se estiver ON ainda definimos um nivel para a transparencia
        winParams.flags = if (on) {
             winParams.flags or bits
        }
        // Senão definimos total transparencia
        else {
             winParams.flags and bits.inv()
        }

        win.attributes = winParams
    }

    /**
     *  Vinculamos todos os campos da activity as variaveis
     * */
    override fun bindViews() {
        toolbar = findViewById(R.id.toolbar)
        movieImage = findViewById(R.id.movieImage)
        movieLike = findViewById(R.id.movieLike)
        movieTitle = findViewById(R.id.movieTitle)
        movieVoteCount = findViewById(R.id.movieVoteCount)
        moviePopularityViews = findViewById(R.id.moviePopularityViews)
        similarMovies = findViewById(R.id.similarMovies)

        // Definimos a nossa support, a toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = null
    }

    /**
     *  Método definido para realizar as ações pós configuração das views
     * */
    override fun initActions() {

        // Definimos a barra de progresso para a tela
        handlerProgress = ProgressBarHandler(this)

        // Chamamos o método para obter o filme
        presenter.getMovie()

        // Definimos o click da imagem
        movieLike?.setOnClickListener { presenter.onClickLike() }
    }

    /**
     *  Configura o clique da toolbar
     * */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val menuId = item.itemId

        // Se for o botao de voltae
        if (menuId == android.R.id.home) {

            // finalizamos a activity
            finish()
        }

        return super.onOptionsItemSelected(item)
    }

    /**
     *  Método definido para realizar a configuração da view com base nos retornos das requisiçoes
     * */
    override fun updateView(movie: Movie?) {
        try {
            // Carregamos a imagem no campo
            Glide.with(this).load(APIClientKeys.BASE_URL_MOVIE_IMAGE_W780 + movie?.posterPath).into(movieImage!!)

            // Carregamos os demais campos
            movieTitle?.text = movie?.title
            movieVoteCount?.text = movie?.getVoteCountDescription()
            moviePopularityViews?.text = movie?.getPopularityDescription()

            // Configuramos o recycler view com o adapter para os similar movies
            val adapter = AdapterSimilarMovie(movie?.similarMovies!!)
            similarMovies?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            similarMovies?.adapter = adapter

        } catch (exception: Exception) {
            Log.e(LogConsts.LOG_ERRO, exception.message!!)
        }
    }

    /**
     *  Mostra  a barra de progresso de Requisicao em Andamento
     * */
    override fun showProgressDialog() = handlerProgress!!.show()

    /**
     *  Esconde  a barra de progresso de Requisicao em Andamento já encerrada
     * */
    override fun hideProgressDialog() = handlerProgress!!.hide()

    /**
     *  Método usado pelo click like para alterar dados de imagem
     * */
    override fun onLikeDeslikeMovie(liked: Boolean) {

        // Se o movie está curtido/like definimos a imagem correspondente
        val imageId = if (liked) {
            R.drawable.icon_liked
        } else {
            R.drawable.icon_like
        }

        // Definimos a imagem respectiva ao like
        movieLike?.setImageResource(imageId)
    }
}