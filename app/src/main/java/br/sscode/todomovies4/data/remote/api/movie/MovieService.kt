package br.sscode.todomovies4.data.remote.api.movie

import br.sscode.todomovies4.data.remote.api.APIClientKeys
import br.sscode.todomovies4.data.remote.model.Movie
import br.sscode.todomovies4.data.remote.model.SimilarMovie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 *  Interface representa o Servico para Movie API, com os seguinte métodos:
 *  Pode-se:
 *  -- Obter as informações de um filme -> getMovie()
 * */
interface MovieService {

    /**
     *  Método usado para obter os detalhes de Um Movie da API
     * */
    @GET("movie/{movieId}")
    fun getMovie(
        @Path("movieId") movieId: Int = 5,
        @Query("api_key") apiKey: String = APIClientKeys.API_KEY,
        @Query("language") language: String = APIClientKeys.LANGUAGE_DEFAULT): Call<Movie>

    /**
     *  Método usado para obter os filmes similares de um determinado Movie da API
     * */
    @GET("movie/{movieId}/similar")
    fun getSimilarMovies(
        @Path("movieId") movieId: Int = 5,
        @Query("api_key") apiKey: String = APIClientKeys.API_KEY,
        @Query("language") language: String = APIClientKeys.LANGUAGE_DEFAULT,
        @Query("page") page: Int = 1): Call<MutableList<SimilarMovie>>
}