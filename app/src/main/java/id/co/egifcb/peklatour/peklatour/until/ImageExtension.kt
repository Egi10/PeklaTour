package id.co.egifcb.peklatour.peklatour.until

import android.annotation.SuppressLint
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import id.co.egifcb.peklatour.peklatour.R

@SuppressLint("CheckResult")
fun ImageView.loadImage(url: Any?) {
    val requestOption = RequestOptions()
    requestOption.placeholder(R.drawable.no_image)
    requestOption.error(R.drawable.no_image)
    Glide.with(this.context)
        .load(url)
        .apply(requestOption)
        .into(this)
}