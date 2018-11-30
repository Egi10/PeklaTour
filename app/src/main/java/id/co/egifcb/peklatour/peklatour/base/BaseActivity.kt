package id.co.egifcb.peklatour.peklatour.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {
    abstract fun contentView(): Int
    abstract fun onCreated()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(contentView())
        onCreated()
    }
}