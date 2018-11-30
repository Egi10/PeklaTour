package id.co.egifcb.peklatour.peklatour.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import id.co.egifcb.peklatour.peklatour.R
import id.co.egifcb.peklatour.peklatour.model.DestinasifavoriteItem
import kotlinx.android.synthetic.main.layout_list_tour_favorite.view.*

class AdapterTourFavorite(private val context: Context, private val list: List<DestinasifavoriteItem>, private val listener: (DestinasifavoriteItem) -> Unit)
    : RecyclerView.Adapter<AdapterTourFavorite.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=
            AdapterTourFavorite.ViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_list_tour_favorite, parent, false))

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(list[position], listener, context)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(destinasifavoriteItem: DestinasifavoriteItem, listener: (DestinasifavoriteItem) -> Unit, context: Context) {
            Glide.with(context).load(destinasifavoriteItem.image).into(itemView.iv_image)
            itemView.tv_jenis_wisata.text = destinasifavoriteItem.namaTempat
            itemView.tv_lama_perjalanan.text = destinasifavoriteItem.lamaPerjalanan

            itemView.setOnClickListener {
                listener(destinasifavoriteItem)
            }
        }
    }
}