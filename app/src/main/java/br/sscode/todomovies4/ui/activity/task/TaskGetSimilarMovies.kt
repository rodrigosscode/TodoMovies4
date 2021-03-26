package br.sscode.todomovies4.ui.activity.task

import android.os.AsyncTask
import android.util.Log
import br.sscode.todomovies4.data.model.SimilarMovie
import br.sscode.todomovies4.data.remote.api.APIClient
import br.sscode.todomovies4.data.remote.api.movie.MovieService
import br.sscode.todomovies4.data.remote.api.response.SimilarMovieResponse
import br.sscode.todomovies4.ui.activity.base.BaseContract
import br.sscode.todomovies4.ui.activity.util.LogConsts

/**
 *  Async task para obter os dados de Similar Movies
 * */
class TaskGetSimilarMovies<V : BaseContract.View>(
    private val context: BaseContract.Presenter<V>,
    private val apiClient: MovieService? = APIClient().movieService
) : AsyncTask<Void, Void, Boolean>() {

    private var result: Boolean = false
    private var similarMovies: MutableList<SimilarMovie>? = null

    override fun onPreExecute() {
        try {
            super.onPreExecute()

            // Pedimos para que a Origem execute o método para mostrar o dialogo
            context.showProgressDialog()
        } catch (exception: Exception) {
            Log.e(LogConsts.LOG_ERRO, exception.message!!)
        }
    }

    override fun doInBackground(vararg params: Void?): Boolean {
        try {
            // Obtemos o response do Client
            val responseMovie = apiClient?.getSimilarMovies()?.execute()

            // Verificamos se ocorreu tudo OK na chamada
            if (responseMovie!!.isSuccessful) {

                // Tudo OK convertemos o response em lista de movies e retornamos
                similarMovies = (responseMovie.body() as SimilarMovieResponse).similarMovies!!

                // definimos que ouve sucesso
                result = true
            }
        } catch (exception: Exception) {
            Log.e(LogConsts.LOG_ERRO, exception.message!!)
        }

        return result
    }

    override fun onPostExecute(result: Boolean?) {
        try {
            super.onPostExecute(result)

            // Retornamos a quem chama os dados da task
            context.onReceiveData(this@TaskGetSimilarMovies::class.java, similarMovies, result!!)

            // Após todas as ações executadas solicitamos a nossa view para esconder o progress de andamento
            context.hideProgressDialog()
        } catch (exception: Exception) {
            Log.e(LogConsts.LOG_ERRO, exception.message!!)
        }
    }
}