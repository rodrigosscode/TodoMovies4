package br.sscode.todomovies4.ui.activity.presenter

import android.os.AsyncTask
import android.util.Log
import br.sscode.todomovies4.data.model.Genre
import br.sscode.todomovies4.data.model.Movie
import br.sscode.todomovies4.data.model.SimilarMovie
import br.sscode.todomovies4.ui.activity.base.BasePresenter
import br.sscode.todomovies4.ui.activity.base.MainContract
import br.sscode.todomovies4.ui.activity.task.TaskGetGenres
import br.sscode.todomovies4.ui.activity.task.TaskGetMovie
import br.sscode.todomovies4.ui.activity.task.TaskGetSimilarMovies
import java.lang.Exception

/**
 *  Presenter da MainActivity contendo seus controlares, acessos
 * */
class MainPresenter : BasePresenter<MainContract.View>(), MainContract.Presenter {

    private var movie: Movie? = null

    /**
     *  Vinculamos a view e solicitamos os binds dos elementos
     * */
    override fun onAttach(view: MainContract.View) {
        super.onAttach(view)
        this.view?.bindViews()
    }

    /**
     *  Obtem o Movie do API
     * */
    override fun getMovie() {
        TaskGetMovie(this).execute()
    }

    /**
     *  Obtem os Similar Movies da API
     * */
    private fun getSimilarMovie() = TaskGetSimilarMovies(this).execute()

    /**
     *  Obtem os Genres da API
     * */
    private fun getGenres() = TaskGetGenres(this).execute()

    /**
     *  Solicita a View para mostrar o Dialog Proress Se Houver
     * */
    override fun showProgressDialog() {
        view?.showProgressDialog()
    }

    /**
     *  Solicita a View para esconder o Dialog Proress Se Houver
     * */
    override fun hideProgressDialog() {
        view?.hideProgressDialog()
    }

    /**
     *  Método usado para os retornos assincronos
     * */
    override fun onReceiveData(
        clazz: Class<out AsyncTask<Void, Void, Boolean>>,
        data: Any?,
        result: Boolean
    ) {
        try {

            // Se o retorno for da Task GetMovie
            if (clazz == TaskGetMovie::class.java) {

                // Ocorrendo sucesso
                if (result) {

                    // Definimos o movie no Movie Principal
                    movie = data as Movie

                    // Obtemos os similares
                    getSimilarMovie()
                }
            }
            // Se o retorno for da Task GetSimilarMovies
            else if (clazz == TaskGetSimilarMovies::class.java) {

                // Ocorrendo sucesso
                if (result) {

                    // Definimos os movies similares no Movie Principal
                    movie?.similarMovies = data as MutableList<SimilarMovie>

                    // Obtemos os generos
                    getGenres()
                }
            }
            // Se o retorno for da Task GetGenres
            else if (clazz == TaskGetGenres::class.java) {

                // Ocorrendo sucesso
                if (result) {

                    // Vinculamos os generos ao similar movies do
                    bindsGenresToSimilarMovies(data as MutableList<Genre>)

                    // Solicitamos a view a atualização dos dados
                    view?.updateView(movie)
                }
            }
        } catch (exception: Exception) {
            Log.e("ERRO", exception.message!!)
        }
    }

    /**
     *  Método usada para vincular os genres aos similarMovies pois a API somente retorna os seus IDS
     *  consequentemente devemos obter os Genres por outra API e vincular pelo ID
     * */
    private fun bindsGenresToSimilarMovies(genres: MutableList<Genre>) {

        var genresToSimilar: MutableList<Genre>? = null

        // Aplicamos caso similarMovies não nulo
        movie?.similarMovies?.let { similarMovies ->

            // Percorremos todos os similar movies do movie atual
            for (movie in similarMovies) {

                // Aplicamos caso genreIds não nulo
                movie.genreIds?.let { genreIds ->

                    // Instanciamos uma nova mutable list para o movie
                    genresToSimilar = mutableListOf()

                    // Percorremos por cada ID dentro de
                    for (genreId in genreIds) {

                        // Percorremos por todos os Genres da API
                        for (genre in genres) {

                            // Se for igual os IDS vinculamos os Genres aos Similar Movies
                            if (genreId == genre.id) {

                                // Adicionamos na nova lista
                                genresToSimilar!!.add(genre)
                            }
                        }
                    }

                    // Definimos os genres no semilar movie
                    movie.genres = genresToSimilar
                }
            }
        }
    }
}