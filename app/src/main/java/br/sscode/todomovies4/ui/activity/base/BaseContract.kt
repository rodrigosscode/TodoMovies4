package br.sscode.todomovies4.ui.activity.base

import android.os.AsyncTask

/**
 *  Interface para a configuracao das ações base dos presenters
 * */
interface BaseContract {
    interface View {
        fun bindViews()
    }

    interface Presenter<V : View> {
        fun onAttach(view: V)
        fun onViewCreated()
        fun onDestroy()
        fun showProgressDialog()
        fun hideProgressDialog()
        fun onReceiveData(clazz: Class<out AsyncTask<Void, Void, Boolean>>, data: Any?, result: Boolean)
    }
}