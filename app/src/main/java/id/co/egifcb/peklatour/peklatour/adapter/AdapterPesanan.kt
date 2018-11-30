package id.co.egifcb.peklatour.peklatour.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.co.egifcb.peklatour.peklatour.R
import id.co.egifcb.peklatour.peklatour.model.DaftarpesananItem
import id.co.egifcb.peklatour.peklatour.until.formatDate
import kotlinx.android.synthetic.main.layout_list_pesanan.view.*

class AdapterPesanan(private val context: Context, private val list: List<DaftarpesananItem>, private val listener: (DaftarpesananItem) -> Unit)
    : RecyclerView.Adapter<AdapterPesanan.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=
            AdapterPesanan.ViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_list_pesanan, parent, false))

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(list[position], listener)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(daftarpesananItem: DaftarpesananItem, listener: (DaftarpesananItem) -> Unit) {
            itemView.tv_tanggal_berangkat.text = daftarpesananItem.tanggalBerangkat?.formatDate()
            itemView.tv_jumlah_penumpang.text = daftarpesananItem.jumlahPenumpang
            itemView.tv_status_pesanan.text = daftarpesananItem.statusPesanan

            itemView.setOnClickListener {
                listener(daftarpesananItem)
            }
        }
    }
}