package br.sscode.todomovies4.ui.activity.base

/**
 *  Classe abstrata utilizada como base para o Presenter
 * */
abstract class BasePresenter<V: BaseContract.View> : BaseContract.Presenter<V> {
    protected var view: V? = null

    override fun onAttach(view: V) {
        this.view = view
    }

    override fun onViewCreated() {
    }

    override fun onDestroy() {
        view = null
    }
}