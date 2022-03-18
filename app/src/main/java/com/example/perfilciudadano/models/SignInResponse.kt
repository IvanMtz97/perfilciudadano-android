package com.example.perfilciudadano.models

data class SignInResponse(
  val success: Boolean,
  val id: Int?,
  val folium: String? = null,
  val type: String? = null,
  val name: String?,
  val surname: String?,
)
