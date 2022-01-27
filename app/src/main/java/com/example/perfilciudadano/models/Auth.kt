package com.example.perfilciudadano.models

import com.google.gson.annotations.SerializedName

data class SignInBodyModel(
  @SerializedName("folium") val Folium: String,
)

data class SignInResponseModel(
  val success: Boolean,
  val folium: String? = null,
  val role: String? = null,
)
