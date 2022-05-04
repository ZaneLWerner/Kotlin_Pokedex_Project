package com.example.learnkotlin

import com.google.gson.annotations.SerializedName

data class Poke(
    @SerializedName ("name") val name: String,
    @SerializedName ("order")  val order: Int,

)


