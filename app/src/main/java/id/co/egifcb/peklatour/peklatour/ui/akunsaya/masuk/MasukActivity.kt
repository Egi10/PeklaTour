package id.co.egifcb.peklatour.peklatour.ui.akunsaya.masuk

import android.app.AlertDialog
import android.view.View
import id.co.egifcb.peklatour.peklatour.R
import id.co.egifcb.peklatour.peklatour.base.BaseActivity
import id.co.egifcb.peklatour.peklatour.model.LoginItem
import id.co.egifcb.peklatour.peklatour.preferences.PreferencesUser
import id.co.egifcb.peklatour.peklatour.ui.main.MainActivity
import id.co.egifcb.peklatour.peklatour.until.DialogLoading
import kotlinx.android.synthetic.main.activity_masuk.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class MasukActivity : BaseActivity(), MasukView, View.OnClickListener {
    private lateinit var masukPresenter: MasukPresenter
    private lateinit var alertDialog: AlertDialog
    private lateinit var preferencesUser: PreferencesUser

    override fun contentView(): Int {
        return R.layout.activity_masuk
    }

    override fun onCreated() {
        title = "Masuk Pekla Tour"

        masukPresenter = MasukPresenter(this)
        alertDialog = DialogLoading.getDialog(this)
        preferencesUser = PreferencesUser(this)

        btn_masuk.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btn_masuk -> {
                masukPresenter.login(et_email.text.toString(), et_password.text.toString())
            }
        }
    }

    override fun onSuccess(list: LoginItem?, message: String?) {
        list?.let {
            preferencesUser.createLogin(it.no.toString(), it.email, it.nama)
        }
        startActivity<MainActivity>()
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
