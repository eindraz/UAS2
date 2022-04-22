package com.wahyuindra.uas2.presenter

import android.util.Log
import com.wahyuindra.uas2.MainActivity
import com.wahyuindra.uas2.model.ResultMobil
import com.wahyuindra.uas2.model.ResultStatus
import com.wahyuindra.uas2.network.NetworkConfig
import retrofit2.Call
import retrofit2.Response

class Presenter(val crudView: MainActivity) {

    //Fungsi GetData
    fun getData(){
        NetworkConfig.getService().getDataMobil()
            .enqueue(object : retrofit2.Callback<ResultMobil>{
                override fun onFailure(call: Call<ResultMobil>, t: Throwable) {
                    crudView.onFailedGet(t.localizedMessage)
                    Log.d("Error", "Error Data")
                }
                override fun onResponse(call: Call<ResultMobil>, response: Response<ResultMobil>){
                    if(response.isSuccessful){
                        val status = response.body()?.status
                        if (status == 200){
                            val data = response.body()?.mobil
                            crudView.onSuccessGet(data)
                        } else{
                            crudView.onFailedGet("Error $status")
                        }
                    }
                }
            })
    }

    //Hapus Data
    fun hapusData(id: String?){
        NetworkConfig.getService()
            .deleteMobil(id)
            .enqueue(object : retrofit2.Callback<ResultStatus>{
                override fun onFailure(call: Call<ResultStatus>, t: Throwable) {
                    crudView.onErrorDelete(t.localizedMessage)
                }
                override fun onResponse(call: Call<ResultStatus>, response: Response<ResultStatus>){
                    if (response.isSuccessful && response.body()?.status == 200){
                        crudView.onSuccessDelete(response.body()?.pesan ?: "")
                    } else {
                        crudView.onErrorDelete(response.body()?.pesan ?: "")
                    }
                }
            })
    }
}