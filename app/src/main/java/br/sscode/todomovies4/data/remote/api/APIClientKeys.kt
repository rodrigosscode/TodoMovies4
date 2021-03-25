package br.sscode.todomovies4.data.remote.api

/**
 *  Object utilizado para definir as chaves para as requisições via Retrofit para a API Movies TDMB
 * */
object APIClientKeys {
    const val API_KEY: String = "6b46828926df2e6122463ebb05a122c9"
    const val BASE_URL_MOVIE: String = "https://api.themoviedb.org/3/"
    const val BASE_URL_MOVIE_IMAGE_W500: String = "https://image.tmdb.org/t/p/w500"
    const val BASE_URL_MOVIE_IMAGE_W780: String = "https://image.tmdb.org/t/p/w780"
    const val LANGUAGE_DEFAULT: String = "pt-BR"
}