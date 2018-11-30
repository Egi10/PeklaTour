package id.co.egifcb.peklatour.peklatour.ui.splashscreen

import android.content.Intent
import android.os.Handler
import android.view.WindowManager
import id.co.egifcb.peklatour.peklatour.R
import id.co.egifcb.peklatour.peklatour.base.BaseActivity
import id.co.egifcb.peklatour.peklatour.ui.main.MainActivity

class SplashScreenActivity : BaseActivity() {
    override fun contentView(): Int {
        return R.layout.activity_splash_screen
    }

    override fun onCreated() {
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000)
    }
}
