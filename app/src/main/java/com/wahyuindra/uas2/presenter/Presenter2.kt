package com.wahyuindra.uas2.presenter

import com.wahyuindra.uas2.UpdateAddActivity
import com.wahyuindra.uas2.model.ResultStatus
import com.wahyuindra.uas2.network.NetworkConfig
import retrofit2.Call
import retrofit2.Response

class Presenter2 (val crudView: UpdateAddActivity) {

    //Add data
    fun addData(nama : String, tipe : String, spesifikasi : String, harga : String){
        NetworkConfig.getService()
            .addMobil(nama, tipe, spesifikasi, harga)
            .enqueue(object : retrofit2.Callback<ResultStatus>{
                override fun onFailure(call: Call<ResultStatus>, t: Throwable) {
                    crudView.errorAdd(t.localizedMessage)
                }
                override fun onResponse(call: Call<ResultStatus>, response: Response<ResultStatus>){
                    if (response.isSuccessful && response.body()?.status == 200) {
                        crudView.successAdd(response.body()?.pesan ?: "")
                    } else {
                        crudView.errorAdd(response.body()?.pesan ?: "")
                    }
                }
            })
    }

    //Update Data
    fun updateData(id: String, nama: String, tipe: String, spesifikasi: String, harga : String){
        NetworkConfig.getService()
            .updateMobil(id, nama, tipe, spesifikasi, harga)
            .enqueue(object : retrofit2.Callback<ResultStatus>{
                override fun onFailure(call: Call<ResultStatus>, t: Throwable) {
                    crudView.onErrorUpdate(t.localizedMessage)
                }
                override fun onResponse(call: Call<ResultStatus>, response: Response<ResultStatus>){
                    if (response.isSuccessful && response.body()?.status == 200){
                        crudView.onSuccessUpdate(response.body()?.pesan ?: "")
                    }else{
                        crudView.onErrorUpdate(response.body()?.pesan ?: "")
                    }
                }
            })
    }
}