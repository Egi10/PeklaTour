package id.co.egifcb.peklatour.peklatour.ui.pesantour

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.view.KeyEvent
import android.view.MenuItem
import android.view.View
import android.widget.Button
import com.google.android.material.bottomsheet.BottomSheetDialog
import id.co.egifcb.peklatour.peklatour.R
import id.co.egifcb.peklatour.peklatour.base.BaseActivity
import id.co.egifcb.peklatour.peklatour.model.DaftartourItem
import id.co.egifcb.peklatour.peklatour.data.source.local.PreferencesUser
import id.co.egifcb.peklatour.peklatour.until.*
import kotlinx.android.synthetic.main.activity_pesan_tour.*
import java.text.SimpleDateFormat
import java.util.*

class PesanTourActivity : BaseActivity(), View.OnClickListener, PesanTourView {

    private lateinit var preferencesUser: PreferencesUser
    private lateinit var pesanTourPresenter: PesanTourPresenter
    private lateinit var items: DaftartourItem
    private lateinit var alertDialog: AlertDialog
    private lateinit var biayaTour: String

    override fun contentView(): Int {
        return R.layout.activity_pesan_tour
    }

    override fun onCreated() {
        title = "Pesan Tour"
        items = intent.getParcelableExtra("items")!!

        tv_tujuan_tour.text = items.namaTempat
        tv_durasi_tour.text = items.durasiTour
        biayaTour = items.harga.toString()
        tv_biaya_tour.text = biayaTour.formatRupiah()
        tv_rute_perjalanan.text = items.rutePerjalanan?.formatHtml()
        tv_termasuk_dalam_tour.text = items.include?.formatHtml()
        tv_tidak_termasuk_dalam_tour.text = items.exclute?.formatHtml()

        preferencesUser = PreferencesUser(this)
        pesanTourPresenter = PesanTourPresenter(this)
        alertDialog = DialogLoading.getDialog(this)

        ll_penumpang.setOnClickListener(this)
        ll_tanggal_berangkat.setOnClickListener(this)
        btn_pesan.setOnClickListener(this)
    }

    override fun onSuccess(message: String?) {
//        alert(message.toString()) {
//            yesButton {
//                startActivity<MainActivity>()
//                finish()
//            }
//        }.apply {
//            isCancelable = false
//        }.show()
    }

    override fun showLoading() {
        alertDialog.apply {
            show()
        }
    }

    override fun hideLoading() {
        alertDialog.apply {
            dismiss()
        }
    }

    override fun onFailed(message: String?) {
        toast(message.toString())
    }

    override fun onError(message: String?) {
        toast(message.toString())
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.ll_penumpang -> {
                val buttomSheetDialog =
                    BottomSheetDialog(
                        this
                    )
                val sheetView = layoutInflater.inflate(R.layout.layout_penumpang, null)
                var ambilNilai = ""
//                val numberPicker = sheetView.findViewById<WheelPicker>(R.id.numberPicker)
                val pilih = sheetView.findViewById<Button>(R.id.btn_pilih)
                val batal = sheetView.findViewById<Button>(R.id.btn_batal)

//                numberPicker.setSelectorRoundedWrapPreferred(true)
//                numberPicker.setWheelItemCount(5)
//                numberPicker.setMin(2)
//                numberPicker.setMax(7)
//                numberPicker.setSelectedTextColor(R.color.color_4_blue)
//                numberPicker.setUnselectedTextColor(R.color.color_3_dark_blue)
//                numberPicker.setOnValueChangeListener(object : OnValueChangeListener {
//                    override fun onValueChange(picker: WheelPicker, oldVal: String, newVal: String) {
//                        ambilNilai = newVal
//                    }
//                })

                pilih.setOnClickListener {
                    val format = String.format("%s Orang", ambilNilai)

                    tv_penumpang.text = format

                    buttomSheetDialog.dismiss()
                }

                batal.setOnClickListener {
                    buttomSheetDialog.dismiss()
                }

                buttomSheetDialog.setContentView(sheetView)
                buttomSheetDialog.show()
            }

            R.id.ll_tanggal_berangkat -> {
                val calendar = Calendar.getInstance()
                val year = calendar.get(Calendar.YEAR)
                val month = calendar.get(Calendar.MONTH)
                val day = calendar.get(Calendar.DAY_OF_MONTH)

                val datePickerDialog = DatePickerDialog(
                    this,
                    DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                        val date = Calendar.getInstance()
                        date.set(Calendar.YEAR, year)
                        date.set(Calendar.MONTH, monthOfYear)
                        date.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                        val datee = date.time
                        val simpleDateFormat = SimpleDateFormat("dd/MM//yyyy", Locale.getDefault())
                        val dateString = simpleDateFormat.format(datee)

                        tv_tanggal_berangkat.text = dateString
                    },
                    year,
                    month,
                    day
                )
                calendar.add(Calendar.DAY_OF_MONTH, 2)
                datePickerDialog.datePicker.minDate = calendar.timeInMillis
                calendar.add(Calendar.MONTH, 1)
                calendar.add(Calendar.DAY_OF_MONTH, -2)
                datePickerDialog.datePicker.maxDate = calendar.timeInMillis
                datePickerDialog.show()
            }

            R.id.btn_pesan -> {
                if (!preferencesUser.isLoggedIn()) {
//                    alert("Anda belum masuk untuk melakukan pemesanan. Apakah Anda ingin masuk ?") {
//                        yesButton {
//                            startActivity<MasukActivity>()
//                            finish()
//                        }
//
//                        noButton {
//                            it.dismiss()
//                        }
//                    }.show()
                } else {
                    val user = preferencesUser.getUserDetail()
                    val id = user[PreferencesUser.NO]

                    val tanggal = tv_tanggal_berangkat.text.toString()
                    val penumpang = tv_penumpang.text.toString()

                    when {
                        tanggal == "Pilih Tanggal Keberangkatan" -> {
                            toast("Tanggal Mohon Diisi")
                        }

                        penumpang == "Pilih Banyak Penumpang" -> {
                            toast("Penumpang Tolong Diisi")
                        }

                        else -> {
                            pesanTourPresenter.pesan(
                                items.no,
                                tv_tanggal_berangkat.text.toString(),
                                tv_penumpang.text.toString(),
                                id,
                                tv_tujuan_tour.text.toString(),
                                tv_durasi_tour.text.toString(),
                                biayaTour
                            )
                        }
                    }
                }
            }
        }
    }

}
