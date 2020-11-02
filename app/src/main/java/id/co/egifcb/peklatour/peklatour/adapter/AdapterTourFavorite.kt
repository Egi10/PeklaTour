package id.co.egifcb.peklatour.peklatour.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.co.egifcb.peklatour.peklatour.R
import id.co.egifcb.peklatour.peklatour.model.DestinasifavoriteItem
import id.co.egifcb.peklatour.peklatour.until.loadImage
import kotlinx.android.synthetic.main.layout_list_tour_favorite.view.*

class AdapterTourFavorite(private val list: List<DestinasifavoriteItem>, private val listener: (DestinasifavoriteItem) -> Unit)
    : RecyclerView.Adapter<AdapterTourFavorite.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_list_tour_favorite, parent, false))

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(list[position], listener)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(destinasifavoriteItem: DestinasifavoriteItem, listener: (DestinasifavoriteItem) -> Unit) {
            itemView.iv_image.loadImage(destinasifavoriteItem.image)
            itemView.tv_jenis_wisata.text = destinasifavoriteItem.namaTempat
            itemView.tv_lama_perjalanan.text = destinasifavoriteItem.lamaPerjalanan

            itemView.setOnClickListener {
                listener(destinasifavoriteItem)
            }
        }
    }
}