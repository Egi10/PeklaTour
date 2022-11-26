package id.co.egifcb.peklatour.peklatour.ui.akunsaya.daftar

import android.view.View
import id.co.egifcb.peklatour.peklatour.R
import id.co.egifcb.peklatour.peklatour.base.BaseActivity
import id.co.egifcb.peklatour.peklatour.ui.akunsaya.masuk.MasukActivity
import id.co.egifcb.peklatour.peklatour.until.DialogLoading
import id.co.egifcb.peklatour.peklatour.until.startActivity
import id.co.egifcb.peklatour.peklatour.until.toast
import kotlinx.android.synthetic.main.activity_daftar.*

class DaftarActivity : BaseActivity(), DaftarView, View.OnClickListener {
    private lateinit var daftarPresenter: DaftarPresenter
    private lateinit var alertDialog: android.app.AlertDialog

    override fun contentView(): Int {
        return R.layout.activity_daftar
    }

    override fun onCreated() {
        title = "Daftar Pekla Tour"

        daftarPresenter = DaftarPresenter(this)

        alertDialog = DialogLoading.getDialog(this)

        btn_daftar.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btn_daftar -> {
                val namaLengkap = et_nama.text
                val email = et_email.text
                val password = et_password.text.toString()
                val konfirmasiPassword = et_konfir_password.text.toString()

                if (password == konfirmasiPassword) {
                    daftarPresenter.register(email.toString(), password, namaLengkap.toString())
                } else {
                    toast("Konfirmasi Password Dengan Benar")
                }
            }
        }
    }

    override fun onSuccess(message: String?) {
        startActivity<MasukActivity>()
        finish()
    }

    override fun showLoading() {
        alertDialog.apply {
            show()
        }
    }

    override fun hideLoading() {
        alertDialog.apply {
            dismiss()
        }
    }

    override fun onFailed(message: String?) {
        toast(message.toString())
    }

    override fun onError(message: String?) {
        toast(message.toString())
    }
}
