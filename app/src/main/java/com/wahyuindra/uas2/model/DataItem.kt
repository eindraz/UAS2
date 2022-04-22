package com.wahyuindra.uas2.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class DataItem : Serializable{

    @field:SerializedName("nama")
    val vNama: String? = null

    @field:SerializedName("id")
    val vId: String? = null

    @field:SerializedName("tipe")
    val vTipe: String? = null

    @field:SerializedName("spesifikasi")
    val vSpesifikasi: String? = null

    @field:SerializedName("harga")
    val vHarga: String? = null
}