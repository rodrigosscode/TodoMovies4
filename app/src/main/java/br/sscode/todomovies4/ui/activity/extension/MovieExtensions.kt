package br.sscode.todomovies4.ui.activity.extension

import br.sscode.todomovies4.data.model.Movie
import java.text.NumberFormat
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
            sbGenres.append(", ")
        }

        // Convertemos o StringBuilder e obtemos o Description dos Genres removendo a Ultima ", "
        genresDescription = sbGenres.toString()
        genresDescription  = genresDescription.substring(0, genresDescription.length-2)
    }

    return genresDescription
}

/**
 *  Extension usada para obter os Likes em String formatados
 *  Ex: " 1.2K Likes "
 * */
fun Movie.getVoteCountDescription(): String {
    var voteCountDescription: String = ""

    // Se o movie tiver mais de 1000 likes
    voteCountDescription = if(voteCount!! > 1000) {

        // Definimos o separador no Likes Count pelo milhar (#.###)
        voteCountDescription = String.format("%,d", voteCount)

        // Formatamos o valor obtendo apenas os 3 primeiros digitos assim sendo (#.###) -> (#.#)
        voteCountDescription = voteCountDescription.substring(0, 3)

        // Adicionamos e Retornamos com K para representar o  milhar: (#.#) -> (#.#K)
        voteCountDescription.plus("K")
    }
    // Senão, só adicionamos a contagem (###)
    else {
        voteCountDescription.plus(voteCount)
    }

    // Definimos Likes para completar a informação (#.#K) -> (#.#K Likes)
    voteCountDescription = voteCountDescription.plus(" Likes")

    return voteCountDescription
}

/**
 *  Extension usada para obter os Popularity Views em String formatados
 *  Ex: " 1,2000 Views "
 * */
fun Movie.getPopularityDescription(): String {

    // Definimos o formato do numero para o "popularity"
    val numberFormat = NumberFormat.getNumberInstance(Locale("pt", "BR"))

    // Definimos a breve description com a "popularity" formatada no padrão brasileiro "###.###.###,00"
    var popularityDescription: String = numberFormat.format(popularity)

    // Definimos Likes para completar a informação
    popularityDescription = popularityDescription.plus(" Views")

    return popularityDescription
}