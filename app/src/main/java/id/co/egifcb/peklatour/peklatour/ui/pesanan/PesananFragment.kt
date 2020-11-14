package id.co.egifcb.peklatour.peklatour.ui.pesanan

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView

import id.co.egifcb.peklatour.peklatour.R
import id.co.egifcb.peklatour.peklatour.adapter.AdapterPesanan
import id.co.egifcb.peklatour.peklatour.base.BaseFragment
import id.co.egifcb.peklatour.peklatour.model.DaftarpesananItem
import id.co.egifcb.peklatour.peklatour.preferences.PreferencesUser
import id.co.egifcb.peklatour.peklatour.ui.tikettour.TiketTourActivity
import kotlinx.android.synthetic.main.fragment_pesanan.*
import org.jetbrains.anko.*

class PesananFragment : BaseFragment(), PesananView {
    private lateinit var pesananPresenter: PesananPresenter
    private lateinit var adapterPesanan: AdapterPesanan
    private var listPesanan: MutableList<DaftarpesananItem> = mutableListOf()
    private lateinit var preferencesUser: PreferencesUser
    private lateinit var llEmpty: LinearLayout
    private lateinit var textMesage: TextView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView

    override fun contentView(): Int {
        return R.layout.fragment_pesanan
    }

    override fun onCreated(view: View) {
        pesananPresenter = PesananPresenter(this)

        preferencesUser = PreferencesUser(requireContext())
        llEmpty = view.find(R.id.ll_empty)
        textMesage = view.find(R.id.text_message)
        swipeRefreshLayout = view.find(R.id.swipeRefresh)
        recyclerView = view.find(R.id.recyclerView)
        val title = view.find<TextView>(R.id.tv_title)
        title.text = getString(R.string.pesanan)

        swipeRefresh.post {
            loadData()
        }

        swipeRefresh.setOnRefreshListener {
            loadData()
        }
    }

    private fun loadData() {
        val user = preferencesUser.getUserDetail()
        val id = user[preferencesUser.NO]
        id?.let {
            if (it.isEmpty()) {
                llEmpty.visibility = View.VISIBLE
                textMesage.text = getString(R.string.message_pemesanan)
            } else {
                pesananPresenter.getPesanan(it)
            }
        }
        adapterPesanan = AdapterPesanan(listPesanan) {
            when(it.statusPesanan) {
                "Pengajuan" -> {
                    requireContext().alert ("Mohon Menunggu, kami masih melakukan proses pesanan Anda") {
                        yesButton { dialog ->
                            dialog.dismiss()
                        }
                    }.show()
                }

                "Pengajuan Diterima" -> {
                    requireContext().startActivity<TiketTourActivity>("items" to it)
                }

                else -> {

                }
            }
        }
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext())
        recyclerView.adapter = adapterPesanan
    }

    override fun onSuccess(list: List<DaftarpesananItem>?) {
        listPesanan.clear()
        list?.let {
            listPesanan.addAll(it)
        }
        adapterPesanan.notifyDataSetChanged()
    }

    override fun onEmpty() {
        llEmpty.visibility = View.VISIBLE
        textMesage.text = getString(R.string.message_pemesanan)
    }

    override fun showLoading() {
        swipeRefreshLayout.isRefreshing = true
    }

    override fun hideLoading() {
        swipeRefreshLayout.isRefreshing = false
    }

    override fun onFailed(message: String?) {
        requireContext().toast(message.toString())
    }

    override fun onError(message: String?) {
        requireContext().toast(message.toString())
    }
}
