package id.co.egifcb.peklatour.peklatour.ui.daftartour

import android.support.v7.widget.GridLayoutManager
import android.view.KeyEvent
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import id.co.egifcb.peklatour.peklatour.R
import id.co.egifcb.peklatour.peklatour.adapter.AdapterDaftarTour
import id.co.egifcb.peklatour.peklatour.base.BaseActivity
import id.co.egifcb.peklatour.peklatour.model.DaftartourItem
import id.co.egifcb.peklatour.peklatour.ui.main.MainActivity
import id.co.egifcb.peklatour.peklatour.ui.pesantour.PesanTourActivity
import kotlinx.android.synthetic.main.activity_daftar_tour.*
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class DaftarTourActivity : BaseActivity(), DaftarTourView {
    private lateinit var daftarTourPresenter: DaftarTourPresenter
    private var listDaftarTour: MutableList<DaftartourItem> = mutableListOf()
    private lateinit var adapterDaftarTour: AdapterDaftarTour
    private lateinit var jenisTempat: String
    private lateinit var llEmpty: LinearLayout

    override fun contentView(): Int {
        return R.layout.activity_daftar_tour
    }

    override fun onCreated() {
        title = "Daftar Tour"

        daftarTourPresenter = DaftarTourPresenter(this)
        llEmpty = find(R.id.ll_empty)

        val inten = intent
        jenisTempat = inten.getStringExtra("jenis_tempat")

        swipeRefresh.post {
            loadData()
        }

        swipeRefresh.setOnRefreshListener {
            loadData()
        }
    }

    private fun loadData() {
        daftarTourPresenter.getDaftarTour(jenisTempat)

        adapterDaftarTour = AdapterDaftarTour(this, listDaftarTour) {
            startActivity<PesanTourActivity>("items" to it)
        }
        val layoutManager = GridLayoutManager(this, 2)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapterDaftarTour
    }

    override fun onSuccess(list: List<DaftartourItem>?) {
        listDaftarTour.clear()
        list?.let {
            listDaftarTour.addAll(it)
        }
        adapterDaftarTour.notifyDataSetChanged()
    }

    override fun onEmpty() {
        llEmpty.visibility = View.VISIBLE
    }

    override fun showLoading() {
        swipeRefresh.isRefreshing = true
    }

    override fun hideLoading() {
        swipeRefresh.isRefreshing = false
    }

    override fun onFailed(message: String?) {
        toast(message.toString())
    }

    override fun onError(message: String?) {
        toast(message.toString())
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            android.R.id.home -> {
                back()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            back()
        }
        return super.onKeyDown(keyCode, event)
    }

    private fun back() {
        startActivity<MainActivity>()
        finish()
    }
}
