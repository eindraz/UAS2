package com.wahyuindra.uas2.model

import com.google.gson.annotations.SerializedName

data class ResultMobil (
    @field:SerializedName("pesan")
    val pesan: String? = null,
    @field:SerializedName("mobil")
    val mobil: List<DataItem>? = null,
    @field:SerializedName("status")
    val status: Int? = null
)