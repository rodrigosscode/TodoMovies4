package br.sscode.todomovies4.ui.activity.extension

import br.sscode.todomovies4.data.model.Movie
import java.text.SimpleDateFormat
import java.util.*

/**
 *  Extension usada para obter o Ano da Data em String do Movie (Por padrão temos a data inteira em String da API)
 * */
fun Movie.getYearReleaseDate(): String {
    var releaseDateYear: String = ""

    // Definimos um Formatador/Conversor para Datas
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    // Com a data em mãos
    releaseDate?.let { dateString ->

        // Transformamos o releaseDate de String em Date
        val releaseDate = dateFormat.parse(dateString)

        // Definimos no Calendar para obter os dados de Ano, mês ou dia
        val calendarDate = Calendar.getInstance()
        calendarDate.time = releaseDate!!

        // Obtemos o ano em String
        releaseDateYear = calendarDate.get(Calendar.YEAR).toString()
    }

    return releaseDateYear
}

/**
 *  Extension usada para obter os Genres em String formatados
 *  Ex: " Terror, Drama "
 * */
fun Movie.getGenresDescription(): String {
    val sbGenres = StringBuilder("")
    var genresDescription = ""

    // Se houver generos
    genres?.let { genres ->

        // Percorremos os Genres
        genres.forEach {    genre ->
            sbGenres.append(genre.name)
            sbGenres.append(",")
        }

        // Convertemos o StringBuilder e obtemos o Description dos Genres removendo a Ultima ","
        genresDescription = sbGenres.toString()
        genresDescription  = genresDescription.substring(0, genresDescription.length-1)
    }

    return genresDescription
}

/**
 *  Extension usada para obter os Likes em String formatados
 *  Ex: " 1.2K Likes "
 * */
fun Movie.getVoteCountDescription(): String {
    val voteCountDescription: String = ""

    // Se o movie tiver mais de 1000 likes
    if(voteCount!! > 1000) {
        voteCountDescription.plus(String.format("%02d", voteCount)).plus("K")
    }
    // Senão, só adicionamos a contagem
    else {
        voteCountDescription.plus(voteCount)
    }

    // Definimos Likes para completar a informação
    voteCountDescription.plus(" Likes")

    return voteCountDescription
}

/**
 *  Extension usada para obter os Popularity Views em String formatados
 *  Ex: " 1.2K Views "
 * */
fun Movie.getPopularityDescription(): String {
    val popularityDescription: String = String.format("%.2f", popularity)

    // Se o movie tiver mais de 1000 popularity view
    if(popularity!! > 1000) {
        popularityDescription.plus("K")
    }
    // Senão, só adicionamos a contagem
    else {
        popularityDescription.plus(popularity)
    }

    // Definimos Likes para completar a informação
    popularityDescription.plus(" Views")

    return popularityDescription
}