package br.sscode.todomovies4.data.remote.api.response

import br.sscode.todomovies4.data.model.Genre
import com.google.gson.annotations.SerializedName

/**
 *  Classe representa um APOIO ao Response de Genres List da API TMDB
 * */
class GenresResponse {

    @SerializedName("genres")
    val genres: MutableList<Genre>? = null
}