package br.sscode.todomovies4.ui.activity.base

import br.sscode.todomovies4.data.model.Movie

/**
 *  Interface para a configuracao das ações do presenter main
 * */
interface MainContract {
    interface View : BaseContract.View {
        fun updateView(movie: Movie?)
        fun showProgressDialog()
        fun hideProgressDialog()
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun getMovie()
    }
}