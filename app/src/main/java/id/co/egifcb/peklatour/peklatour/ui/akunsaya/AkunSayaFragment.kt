package id.co.egifcb.peklatour.peklatour.ui.akunsaya

import android.view.View

import id.co.egifcb.peklatour.peklatour.R
import id.co.egifcb.peklatour.peklatour.base.BaseFragment
import id.co.egifcb.peklatour.peklatour.preferences.PreferencesUser
import kotlinx.android.synthetic.main.fragment_akun_saya.*
import org.jetbrains.anko.startActivity
import id.co.egifcb.peklatour.peklatour.ui.akunsaya.daftar.DaftarActivity
import id.co.egifcb.peklatour.peklatour.ui.akunsaya.masuk.MasukActivity
import org.jetbrains.anko.toast

class AkunSayaFragment : BaseFragment(), View.OnClickListener {
    private lateinit var preferencesUser: PreferencesUser

    override fun contentView(): Int {
        return R.layout.fragment_akun_saya
    }

    override fun onCreated(view: View) {
        btn_login.setOnClickListener(this)
        btn_register.setOnClickListener(this)

        preferencesUser = PreferencesUser(requireContext())

        if (!preferencesUser.isLooggedIn()) {
            requireContext().toast("Belum Login")
        } else {
            requireContext().toast("Anda Sudah Login")
        }
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btn_login -> {
                requireContext().startActivity<MasukActivity>()
            }

            R.id.btn_register -> {
                requireContext().startActivity<DaftarActivity>()
            }
        }
    }
}
