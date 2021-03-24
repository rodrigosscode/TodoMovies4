package br.sscode.todomovies4.data.remote.model

import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName

/**
 *  Classe representa Movie (Filme) da API TMDB
 * */
open class Movie {

    @SerializedName("id")
    val id: Long? = 0

    @SerializedName("poster_path")
    val posterPath: String? = ""

    @SerializedName("vote_count")
    val voteCount: Int? = 0

    @SerializedName("popularity")
    val popularity: Double? = 0.0

    @SerializedName("release_date")
    val releaseDate: String? = ""

    @SerializedName("genres")
    val genres: MutableList<Genre>? = null
}