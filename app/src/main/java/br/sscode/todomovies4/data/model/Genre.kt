package br.sscode.todomovies4.data.model

import com.google.gson.annotations.SerializedName

/**
 *  Classe representa Genre (Genero) para o Movie (Filme) da API TMDB
 * */
class Genre {

    @SerializedName("id")
    val id: Long? = 0

    @SerializedName("name")
    val name: String? = ""
}