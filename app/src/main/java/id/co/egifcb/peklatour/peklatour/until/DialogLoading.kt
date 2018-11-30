package id.co.egifcb.peklatour.peklatour.until

import android.app.AlertDialog
import android.content.Context
import dmax.dialog.SpotsDialog
import id.co.egifcb.peklatour.peklatour.R

class DialogLoading {
    companion object {
        private lateinit var dialog: AlertDialog

        fun getDialog(context: Context?): AlertDialog {
            dialog = SpotsDialog.Builder()
                    .setContext(context)
                    .setMessage("Mohon Menunggu...")
                    .setTheme(R.style.Custom)
                    .setCancelable(false)
                    .build()

            return dialog
        }
    }
}