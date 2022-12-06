package id.co.egifcb.peklatour.peklatour.ui.main

import android.view.KeyEvent
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import id.co.egifcb.peklatour.peklatour.R
import id.co.egifcb.peklatour.peklatour.base.BaseActivity
import id.co.egifcb.peklatour.peklatour.ui.akunsaya.AkunSayaFragment
import id.co.egifcb.peklatour.peklatour.ui.home.HomeFragment
import id.co.egifcb.peklatour.peklatour.ui.order.PesananFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    private lateinit var fragment: Fragment
    private var viewIsAtHome: Boolean = false

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {
        loadFragment(it.itemId)

        true
    }

    override fun contentView(): Int {
        return R.layout.activity_main
    }

    override fun onCreated() {
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        val posisi = intent.getStringExtra("posisi")
        when(posisi) {
            null -> {
                loadFragment(R.id.navigation_home)
            }

            "2" -> {
                viewIsAtHome = true
                navigation.selectedItemId = R.id.navigation_pesanan
                loadFragment(R.id.navigation_pesanan)
            }
        }


    }

    private fun loadFragment(itemId: Int) {
        when (itemId) {
            R.id.navigation_home -> {
                fragment = HomeFragment()
                viewIsAtHome = true
            }

            R.id.navigation_pesanan -> {
                fragment = PesananFragment()
                viewIsAtHome = false
            }

            R.id.navigation_akun_saya -> {
                fragment = AkunSayaFragment()
                viewIsAtHome = false
            }
        }

        val fragmentManager = supportFragmentManager
        fragmentManager.executePendingTransactions()
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            alert("Apakah anda yakin untuk keluar ?") {
//                yesButton {
//                    val intent = Intent(Intent.ACTION_MAIN)
//                    intent.addCategory(Intent.CATEGORY_HOME)
//                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
//                    startActivity(intent)
//                    finish()
//                }
//
//                noButton {
//                    it.dismiss()
//                }
//            }.show()
        }
        return super.onKeyDown(keyCode, event)
    }
}
