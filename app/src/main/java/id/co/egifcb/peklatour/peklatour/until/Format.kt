package id.co.egifcb.peklatour.peklatour.until

import android.os.Build
import android.text.Html
import java.text.SimpleDateFormat
import java.util.*

fun String.formatHtml(): String? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(this, Html.FROM_HTML_MODE_COMPACT).toString()
    } else {
        Html.fromHtml(this).toString()
    }
}

fun String.formatDate(fromDate: String = "dd/MM/yyyy", toDateFormat: String = "EEEE, dd MMMM yyyy"): String? {
    val date = SimpleDateFormat(fromDate, Locale.getDefault())
    val dateFrom = date.parse(this)

    val dateFormater = SimpleDateFormat(toDateFormat, Locale.getDefault())
    return dateFormater.format(dateFrom)
}