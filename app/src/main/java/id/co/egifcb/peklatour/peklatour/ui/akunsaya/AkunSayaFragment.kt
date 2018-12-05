package id.co.egifcb.peklatour.peklatour.ui.akunsaya

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView

import id.co.egifcb.peklatour.peklatour.R
import id.co.egifcb.peklatour.peklatour.base.BaseFragment
import id.co.egifcb.peklatour.peklatour.preferences.PreferencesUser
import kotlinx.android.synthetic.main.fragment_akun_saya.*
import org.jetbrains.anko.startActivity
import id.co.egifcb.peklatour.peklatour.ui.akunsaya.daftar.DaftarActivity
import id.co.egifcb.peklatour.peklatour.ui.akunsaya.masuk.MasukActivity
import org.jetbrains.anko.find

class AkunSayaFragment : BaseFragment(), View.OnClickListener {
    private lateinit var preferencesUser: PreferencesUser
    private lateinit var llLogin: LinearLayout
    private lateinit var tvEmail: TextView
    private lateinit var tvNama: TextView
    private lateinit var tvKeluar: TextView

    override fun contentView(): Int {
        return R.layout.fragment_akun_saya
    }

    override fun onCreated(view: View) {
        llLogin = view.find(R.id.ll_login)
        tvEmail = view.find(R.id.tv_email)
        tvNama = view.find(R.id.tv_nama)
        tvKeluar = view.find(R.id.tv_keluar)

        btn_login.setOnClickListener(this)
        btn_register.setOnClickListener(this)

        preferencesUser = PreferencesUser(requireContext())

        if (!preferencesUser.isLooggedIn()) {
            ll_no_login.visibility = View.VISIBLE
            llLogin.visibility = View.GONE
        } else {
            ll_no_login.visibility = View.GONE
            llLogin.visibility = View.VISIBLE

            val user = preferencesUser.getUserDetail()
            tvEmail.text = user[preferencesUser.EMAIL]
            tvNama.text = user[preferencesUser.NAMA]
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

            R.id.tv_keluar -> {
                preferencesUser.logoutUser()
            }
        }
    }
}
