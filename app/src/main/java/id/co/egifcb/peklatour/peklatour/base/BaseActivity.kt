package id.co.egifcb.peklatour.peklatour.base

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import id.co.egifcb.peklatour.peklatour.R
import uk.co.chrisjenx.calligraphy.CalligraphyConfig
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

abstract class BaseActivity : AppCompatActivity() {
    abstract fun contentView(): Int
    abstract fun onCreated()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(contentView())
        onCreated()
        CalligraphyConfig.initDefault(
            CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Ubuntu-Light.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build())
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }
}