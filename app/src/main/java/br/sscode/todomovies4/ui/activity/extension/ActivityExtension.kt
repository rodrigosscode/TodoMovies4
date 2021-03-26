package br.sscode.todomovies4.ui.activity.extension

import android.app.Activity
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import br.sscode.todomovies4.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

/**
 *  Método definimos para exibir um dialogo alerta de um botao na activity
 * */
fun Activity.showDialogAlert(title: String, message: String,
                             positiveButtonText: String,
                             actionOnClickListener: (dialog: DialogInterface, id: Int) -> Unit) {

    // Construimos o dialogo e definimos seus textos
    val builder: AlertDialog.Builder = MaterialAlertDialogBuilder(this, R.style.AlertDialogTheme)
    builder.setTitle(title)
    builder.setMessage(message)

    // Definimos o botao do dialogo
    builder.setPositiveButton(positiveButtonText, actionOnClickListener)

    // Definimos que não será cancelavel
    builder.setCancelable(false)

    // Exibimos o dialog
    val dialog: AlertDialog = builder.create()
    dialog.show()
}