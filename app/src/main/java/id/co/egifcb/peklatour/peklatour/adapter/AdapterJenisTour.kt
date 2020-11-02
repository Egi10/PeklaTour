package id.co.egifcb.peklatour.peklatour.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.co.egifcb.peklatour.peklatour.R
import id.co.egifcb.peklatour.peklatour.model.JenisTourItem
import id.co.egifcb.peklatour.peklatour.until.loadImage
import kotlinx.android.synthetic.main.layout_list_jenis_tour.view.*

class AdapterJenisTour(private val list: List<JenisTourItem>, private val listener: (JenisTourItem) -> Unit)
    : RecyclerView.Adapter<AdapterJenisTour.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_list_jenis_tour, parent, false))

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(list[position], listener)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(jenisTourItem: JenisTourItem, listener: (JenisTourItem) -> Unit) {
            itemView.iv_image.loadImage(jenisTourItem.image)
            itemView.tv_jenis_wisata.text = jenisTourItem.jenisWisata

            itemView.setOnClickListener {
                listener(jenisTourItem)
            }
        }
    }
}