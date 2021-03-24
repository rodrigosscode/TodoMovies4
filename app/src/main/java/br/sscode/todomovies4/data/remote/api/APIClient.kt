package br.sscode.todomovies4.data.remote.api

import br.sscode.todomovies4.data.remote.api.movie.MovieService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 *  Classe de configuração do Retrofit para as Requisições a API Movie TDMB
 * */
class APIClient {

    /**
     *  Objeto retrofit configurado estaticamente
     * */
    companion object {

        var retrofit: Retrofit? = getClient()

        private fun getClient(): Retrofit? {

            // Definimos um interceptor a nivel de Corpo para as requisições
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            // Definimos no nosso acessor de serviço http com o interceptor criado
            val client = OkHttpClient().newBuilder().addInterceptor(interceptor).build()

            // Configuramos o retrofit para acesso aos Services devidamente configurado com um
            // Conversor e um AcessorHttp
            retrofit = Retrofit.Builder()
                .baseUrl(APIClientKeys.BASE_URL_MOVIE)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client).build()

            return retrofit
        }
    }

    /**
     *  Função definida para gerar a Interface de acesso com os Métodos da API
     * */
    fun getMovieService(): MovieService? {
        return retrofit?.create(MovieService::class.java)
    }
}