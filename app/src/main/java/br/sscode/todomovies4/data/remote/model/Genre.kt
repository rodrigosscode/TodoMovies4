package br.sscode.todomovies4.data.remote.model

import com.google.gson.annotations.SerializedName

/**
 *  Classe representa Genre (Genero) para o Movie (Filme) da API TMDB
 * */
class Genre {

    @SerializedName("id")
    private val id: Long? = 0

    @SerializedName("name")
    private val name: String? = ""
}