package id.co.egifcb.peklatour.peklatour.ui.home

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.ImageView
import com.smarteist.autoimageslider.SliderLayout
import com.smarteist.autoimageslider.SliderView

import id.co.egifcb.peklatour.peklatour.R
import id.co.egifcb.peklatour.peklatour.adapter.AdapterJenisTour
import id.co.egifcb.peklatour.peklatour.adapter.AdapterTourFavorite
import id.co.egifcb.peklatour.peklatour.base.BaseFragment
import id.co.egifcb.peklatour.peklatour.model.DestinasifavoriteItem
import id.co.egifcb.peklatour.peklatour.model.JenisTourItem
import id.co.egifcb.peklatour.peklatour.model.PromotourItem
import id.co.egifcb.peklatour.peklatour.ui.daftartour.DaftarTourActivity
import kotlinx.android.synthetic.main.fragment_home.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class HomeFragment : BaseFragment(), HomeView {
    private lateinit var homePresenter: HomePresenter
    private var listJenisTour: MutableList<JenisTourItem> = mutableListOf()
    private var listJenisTourFavorite: MutableList<DestinasifavoriteItem> = mutableListOf()
    private lateinit var adapterJenisTour: AdapterJenisTour
    private lateinit var adapterTourFavorite: AdapterTourFavorite

    override fun contentView(): Int {
        return R.layout.fragment_home
    }

    override fun onCreated(view: View) {
        homePresenter = HomePresenter(this)

        swipeRefresh.post {
            loadData()
        }

        swipeRefresh.setOnRefreshListener {
            loadData()
        }
    }

    private fun loadData() {
        homePresenter.getJenisTour()

        //LoadDataMenu
        adapterJenisTour = AdapterJenisTour(requireContext(), listJenisTour) {
            requireContext().startActivity<DaftarTourActivity>("jenis_tempat" to it.jenisWisata)
        }
        recyclerViewJenisTour.layoutManager = GridLayoutManager(requireContext(), 3)
        recyclerViewJenisTour.adapter = adapterJenisTour

        //LoadDataDestinasiFavorite
        adapterTourFavorite = AdapterTourFavorite(requireContext(), listJenisTourFavorite) {

        }
        recyclerViewTourFavorite.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerViewTourFavorite.adapter = adapterTourFavorite
    }

    override fun onSuccess(list: List<JenisTourItem>?) {
        listJenisTour.clear()
        list?.let {
            listJenisTour.addAll(it)
        }
        adapterJenisTour.notifyDataSetChanged()
    }

    override fun onSuccessFavorite(list: List<DestinasifavoriteItem>?) {
        listJenisTourFavorite.clear()
        list?.let {
            listJenisTourFavorite.addAll(it)
        }
        adapterTourFavorite.notifyDataSetChanged()
    }

    override fun onSuccessPromo(list: List<PromotourItem>?) {
        imageSlider.setIndicatorAnimation(SliderLayout.Animations.SCALE_DOWN)
        imageSlider.scrollTimeInSec = 2

        list?.let {
            for (i in it.indices) {
                val sliderView = SliderView(requireContext())

                sliderView.imageUrl = it[i].image

                sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP)

                imageSlider.addSliderView(sliderView)
            }
        }
    }

    override fun onEmpty() {
        requireContext().toast("onEmpty")
    }

    override fun showLoading() {
        swipeRefresh.isRefreshing = true
        tv_piloih_tour.visibility = View.GONE
        tv_tour_favorite.visibility = View.GONE
    }

    override fun hideLoading() {
        swipeRefresh.isRefreshing = false
        tv_piloih_tour.visibility = View.VISIBLE
        tv_tour_favorite.visibility = View.VISIBLE
    }

    override fun onFailed(message: String?) {
        requireContext().toast(message.toString())
    }

    override fun onError(message: String?) {
        requireContext().toast(message.toString())
    }
}
