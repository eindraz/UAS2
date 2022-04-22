package com.wahyuindra.uas2.network

import com.wahyuindra.uas2.model.ResultMobil
import com.wahyuindra.uas2.model.ResultStatus
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

object NetworkConfig {

    fun getInterceptor() : OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        return okHttpClient
    }

    //Retrofit
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2/ci3_api/index.php/MobilApi/")
            .client(getInterceptor())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getService() = getRetrofit().create(MobilService::class.java)
}

interface MobilService{

    //Fungsi Create Data
    @FormUrlEncoded
    @POST("add")
    fun addMobil(@Field("nama") name : String,
                 @Field("tipe") tipe : String,
                 @Field("spesifikasi") spesifikasi : String,
                 @Field("harga") harga : String) : Call<ResultStatus>

    //Fungsi Get Data
    @GET("getData")
    fun getDataMobil() : Call<ResultMobil>

    //Fungsi Delete Data
    @FormUrlEncoded
    @POST("delete")
    fun deleteMobil(@Field("id") id: String?) : Call<ResultStatus>

    //Fungsi Update Data
    @FormUrlEncoded
    @POST("update")
    fun updateMobil(@Field("id") id: String,
                    @Field("nama") name: String,
                    @Field("tipe") tipe : String,
                    @Field("spesifikasi") spesifikasi : String,
                    @Field("harga") harga : String) : Call<ResultStatus>
}