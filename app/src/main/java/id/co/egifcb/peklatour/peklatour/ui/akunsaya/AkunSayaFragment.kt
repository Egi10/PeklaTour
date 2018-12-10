package id.co.egifcb.peklatour.peklatour.ui.akunsaya

import android.support.v7.widget.CardView
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import id.co.egifcb.peklatour.peklatour.R
import id.co.egifcb.peklatour.peklatour.base.BaseFragment
import id.co.egifcb.peklatour.peklatour.preferences.PreferencesUser
import id.co.egifcb.peklatour.peklatour.ui.akunsaya.daftar.DaftarActivity
import id.co.egifcb.peklatour.peklatour.ui.akunsaya.masuk.MasukActivity
import id.co.egifcb.peklatour.peklatour.ui.main.MainActivity
import kotlinx.android.synthetic.main.fragment_akun_saya.*
import org.jetbrains.anko.*

class AkunSayaFragment : BaseFragment(), View.OnClickListener {
    private lateinit var preferencesUser: PreferencesUser
    private lateinit var llLogin: CardView
    private lateinit var tvEmail: TextView
    private lateinit var tvNama: TextView
    private lateinit var llKeluar: LinearLayout

    override fun contentView(): Int {
        return R.layout.fragment_akun_saya
    }

    override fun onCreated(view: View) {
        llLogin = view.find(R.id.ll_login)
        tvEmail = view.find(R.id.tv_email)
        tvNama = view.find(R.id.tv_nama)
        llKeluar = view.find(R.id.ll_keluar)

        btn_login.setOnClickListener(this)
        btn_register.setOnClickListener(this)
        llKeluar.setOnClickListener(this)

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

            R.id.ll_keluar -> {
                requireContext().alert("Apakah anda yakin akan keluar ?") {
                    yesButton {
                        preferencesUser.logoutUser()

                        requireContext().startActivity<MainActivity>()
                    }

                    noButton {
                        it.dismiss()
                    }
                }.show()
            }
        }
    }
}
