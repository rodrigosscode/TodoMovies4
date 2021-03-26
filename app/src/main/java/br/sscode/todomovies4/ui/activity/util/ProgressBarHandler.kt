package br.sscode.todomovies4.ui.activity.util

import android.app.Activity
import android.content.Context
import android.view.Gravity
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout
import br.sscode.todomovies4.R

/**
 *  Classe de apoio para exibir de progress na activity dinamicamente
 * */
class ProgressBarHandler(context: Context) {

    private var mProgressBar: ProgressBar? = null
    private var mRelativeLayout: RelativeLayout? = null
    private var mContentLayout: ViewGroup? = null
    private var mLayoutParams: RelativeLayout.LayoutParams? = null

    /**
     *  Iniciamos a Classe configurando o layout de apresentação
     * */
    init {
        // Instanciamos um progress bar
        mProgressBar = ProgressBar(context, null, android.R.attr.progressBarStyleLarge)
        mProgressBar?.isIndeterminate = true

        // Obtemos o content aonde o relative junto ao progress será adicionado
        mContentLayout = (((context as Activity).findViewById(android.R.id.content) as ViewGroup).rootView) as ViewGroup

        // Configuramos um Relative Layout para o progress
        mLayoutParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT)
        mRelativeLayout = RelativeLayout(context)
        mRelativeLayout?.gravity = Gravity.CENTER
        mRelativeLayout?.setBackgroundColor(context.getColor(R.color.black))

        // Adicionamos o progress ao relative
        mRelativeLayout?.addView(mProgressBar)
    }

    /**
     *  Adiciona/Mostra o progress criado
     * */
    fun show() {
        mContentLayout?.addView(mRelativeLayout, mLayoutParams)
    }

    /**
     *  Remove o progress/layout criado
     * */
    fun hide() {
        mContentLayout?.removeView(mRelativeLayout)
    }
}