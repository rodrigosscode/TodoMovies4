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
     *  Iniciamos a variavel client ao chamar
     *  Configurada para o mapeamento das requisicoes do retrofit
     * */
    private val client by lazy {
        // Definimos um interceptor a nivel de Corpo para as requisições
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        // Definimos no nosso acessor de serviço http com o interceptor criado
        OkHttpClient().newBuilder().addInterceptor(interceptor).build()
    }

    /**
     *  Iniciamos a variavel client ao chamar
     *  Configurada para as requisicoes e gerenciamento dos serviços
     * */
    private val retrofit by lazy {
        // Configuramos o retrofit para acesso aos Services devidamente configurado com um
        // Conversor e um AcessorHttp
        Retrofit.Builder()
            .baseUrl(APIClientKeys.BASE_URL_MOVIE)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client).build()
    }

    /**
     *  Definida para gerar a Interface de acesso com os Métodos da API
     * */
    val movieService: MovieService? by lazy {
       retrofit?.create(MovieService::class.java)
    }
}