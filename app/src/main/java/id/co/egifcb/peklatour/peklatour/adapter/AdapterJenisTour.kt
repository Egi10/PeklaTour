package id.co.egifcb.peklatour.peklatour.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import id.co.egifcb.peklatour.peklatour.R
import id.co.egifcb.peklatour.peklatour.model.JenisTourItem
import kotlinx.android.synthetic.main.layout_list_jenis_tour.view.*

class AdapterJenisTour(private val context: Context, private val list: List<JenisTourItem>, private val listener: (JenisTourItem) -> Unit)
    : RecyclerView.Adapter<AdapterJenisTour.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=
            AdapterJenisTour.ViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_list_jenis_tour, parent, false))

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(list[position], listener, context)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(jenisTourItem: JenisTourItem, listener: (JenisTourItem) -> Unit, context: Context) {
            Glide.with(context).load(jenisTourItem.image).into(itemView.iv_image)
            itemView.tv_jenis_wisata.text = jenisTourItem.jenisWisata

            itemView.setOnClickListener {
                listener(jenisTourItem)
            }
        }
    }
}