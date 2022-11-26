package id.co.egifcb.peklatour.peklatour.ui.tikettour

import android.view.MenuItem
import id.co.egifcb.peklatour.peklatour.R
import id.co.egifcb.peklatour.peklatour.base.BaseActivity
import id.co.egifcb.peklatour.peklatour.model.DaftarpesananItem
import id.co.egifcb.peklatour.peklatour.ui.main.MainActivity
import id.co.egifcb.peklatour.peklatour.until.startActivity
import kotlinx.android.synthetic.main.activity_tiket_tour.*
import net.glxn.qrgen.android.QRCode

class TiketTourActivity : BaseActivity() {
    override fun contentView(): Int {
        return R.layout.activity_tiket_tour
    }

    override fun onCreated() {
        title = "Tiket Tour"

        val items = intent.getParcelableExtra<DaftarpesananItem>("items")

        tv_no_pesanan.text = getString(R.string.nopesanan, items?.no.toString())
        tv_biaya_tour.text = getString(R.string.biayatour, items?.biayaTour)
        tv_tujuan_tour.text = items?.tujuanTour
        tv_jumlah_penumpang.text = getString(R.string.jumlahpenumpang, items?.jumlahPenumpang)
        tv_durasi_tour.text = getString(R.string.durasitour, items?.durasiTour)
        val myBitmap = QRCode.from(items?.no.toString()).withSize(450, 450).bitmap()
        iv_qr_code.setImageBitmap(myBitmap)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                back()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        back()
    }

    private fun back() {
        startActivity<MainActivity> {
            putExtra("posisi", "2")
        }
    }
}
