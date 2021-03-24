package br.sscode.todomovies4.data.remote.model

import com.google.gson.annotations.SerializedName

/**
 *  Classe representa Similar Movie (Filme) da API TMDB
 * */
class SimilarMovie : Movie() {

    @SerializedName("genre_ids")
    val genreIds: MutableList<Long>? = null
}