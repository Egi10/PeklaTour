package id.co.egifcb.peklatour.peklatour.ui.pesanan

import android.support.v7.widget.LinearLayoutManager
import android.view.View

import id.co.egifcb.peklatour.peklatour.R
import id.co.egifcb.peklatour.peklatour.adapter.AdapterPesanan
import id.co.egifcb.peklatour.peklatour.base.BaseFragment
import id.co.egifcb.peklatour.peklatour.model.DaftarpesananItem
import kotlinx.android.synthetic.main.fragment_pesanan.*
import org.jetbrains.anko.toast

class PesananFragment : BaseFragment(), PesananView {
    private lateinit var pesananPresenter: PesananPresenter
    private lateinit var adapterPesanan: AdapterPesanan
    private var listPesanan: MutableList<DaftarpesananItem> = mutableListOf()

    override fun contentView(): Int {
        return R.layout.fragment_pesanan
    }

    override fun onCreated(view: View) {
        pesananPresenter = PesananPresenter(this)

        swipeRefresh.post {
            loadData()
        }

        swipeRefresh.setOnRefreshListener {
            loadData()
        }
    }

    private fun loadData() {
        pesananPresenter.getPesanan("0")
        adapterPesanan = AdapterPesanan(requireContext(), listPesanan) {

        }
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
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

    }

    override fun showLoading() {
        swipeRefresh.isRefreshing = true
    }

    override fun hideLoading() {
        swipeRefresh.isRefreshing = false
    }

    override fun onFailed(message: String?) {
        requireContext().toast(message.toString())
    }

    override fun onError(message: String?) {
        requireContext().toast(message.toString())
    }
}
