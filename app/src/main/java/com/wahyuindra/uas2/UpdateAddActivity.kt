package com.wahyuindra.uas2

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wahyuindra.uas2.model.DataItem
import com.wahyuindra.uas2.presenter.CrudView
import com.wahyuindra.uas2.presenter.Presenter2
import kotlinx.android.synthetic.main.activity_update_add.*

@Suppress("SENSELESS_COMPARISON")

class UpdateAddActivity : AppCompatActivity(), CrudView {

    private lateinit var presenter: Presenter2
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_add)
        presenter = Presenter2(this)
        val itemDataItem = intent.getSerializableExtra("dataItem")
        if (itemDataItem == null){
            btnAction.text = "Tambah"
            btnAction.setOnClickListener() {
                presenter.addData(
                    etNama.text.toString(),
                    etTipe.text.toString(),
                    etSpesifikasi.text.toString(),
                    etHarga.text.toString())
            }
        }else if (itemDataItem != null){
            btnAction.text = "Update"
            val item = itemDataItem as DataItem?
            etNama.setText(item?.vNama.toString())
            etTipe.setText(item?.vTipe.toString())
            etSpesifikasi.setText(item?.vSpesifikasi.toString())
            etHarga.setText(item?.vHarga.toString())
            btnAction.setOnClickListener() {
                presenter.updateData(
                    item?.vId ?: "",
                    etNama.text.toString(),
                    etTipe.text.toString(),
                    etSpesifikasi.text.toString(),
                    etHarga.text.toString())
                finish()
            }
        }
    }
    override fun successAdd(msg: String) {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
    override fun errorAdd(msg: String) {}
    override fun onSuccessUpdate(msg: String) {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
    override fun onErrorUpdate(msg: String) {}
    override fun onSuccessGet(data: List<DataItem>?) {}
    override fun onFailedGet(msg: String) {}
    override fun onSuccessDelete(msg: String) {}
    override fun onErrorDelete(msg: String) {}
}