package id.co.egifcb.peklatour.peklatour.ui.home

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.ImageView
import android.widget.TextView
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
import id.co.egifcb.peklatour.peklatour.until.PeekingLinearLayoutManager
import kotlinx.android.synthetic.main.fragment_home.*
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class HomeFragment : BaseFragment(), HomeView {
    private lateinit var homePresenter: HomePresenter
    private var listJenisTour: MutableList<JenisTourItem> = mutableListOf()
    private var listJenisTourFavorite: MutableList<DestinasifavoriteItem> = mutableListOf()
    private var listImage: MutableList<PromotourItem> = mutableListOf()
    private lateinit var adapterJenisTour: AdapterJenisTour
    private lateinit var adapterTourFavorite: AdapterTourFavorite
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var sliderLayout: SliderLayout
    private lateinit var pilihTour: TextView
    private lateinit var tourFavorite: TextView

    override fun contentView(): Int {
        return R.layout.fragment_home
    }

    override fun onCreated(view: View) {
        homePresenter = HomePresenter(this)

        swipeRefreshLayout = view.find(R.id.swipeRefresh)
        sliderLayout = view.find(R.id.imageSlider)
        pilihTour = view.find(R.id.tv_piloih_tour)
        tourFavorite = view.find(R.id.tv_tour_favorite)

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
        adapterJenisTour = AdapterJenisTour(listJenisTour) {
            requireContext().startActivity<DaftarTourActivity>("jenis_tempat" to it.jenisWisata)
        }
        recyclerViewJenisTour.layoutManager = GridLayoutManager(requireContext(), 3)
        recyclerViewJenisTour.adapter = adapterJenisTour

        //LoadDataDestinasiFavorite
        adapterTourFavorite = AdapterTourFavorite(listJenisTourFavorite) {

        }
        recyclerViewTourFavorite.layoutManager = PeekingLinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
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
        listImage.clear()
        list?.let {
            listImage.addAll(it)
        }
        sliderLayout.setIndicatorAnimation(SliderLayout.Animations.SCALE_DOWN)
        sliderLayout.scrollTimeInSec = 2
        val sliderView = SliderView(requireContext())
        listImage.let {
            for (i in it.indices) {
                sliderView.imageUrl = it[i].image
                sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                sliderLayout.addSliderView(sliderView)
            }
        }
    }

    override fun onEmpty() {
        requireContext().toast("onEmpty")
    }

    override fun showLoading() {
        swipeRefreshLayout.isRefreshing = true
        pilihTour.visibility = View.GONE
        tourFavorite.visibility = View.GONE
    }

    override fun hideLoading() {
        swipeRefreshLayout.isRefreshing = false
        pilihTour.visibility = View.VISIBLE
        tourFavorite.visibility = View.VISIBLE
    }

    override fun onFailed(message: String?) {
        requireContext().toast(message.toString())
    }

    override fun onError(message: String?) {
        requireContext().toast(message.toString())
    }
}
