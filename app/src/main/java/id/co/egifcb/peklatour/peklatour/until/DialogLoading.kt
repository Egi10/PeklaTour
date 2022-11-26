package id.co.egifcb.peklatour.peklatour.until

import android.app.AlertDialog
import android.content.Context

class DialogLoading {
    companion object {
        private lateinit var dialog: AlertDialog

        fun getDialog(context: Context?): AlertDialog {
            dialog = AlertDialog.Builder(context)
                .setMessage("Loading")
                .create()

            return dialog
        }
    }
}