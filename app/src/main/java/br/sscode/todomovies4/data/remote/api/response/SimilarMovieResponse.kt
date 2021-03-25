package br.sscode.todomovies4.data.remote.api.response

import br.sscode.todomovies4.data.model.SimilarMovie
import com.google.gson.annotations.SerializedName

/**
 *  Classe representa um APOIO ao Response de Similar Movie (Filme) da API TMDB
 * */
class SimilarMovieResponse {

    @SerializedName("results")
    val similarMovies: MutableList<SimilarMovie>? = null
}