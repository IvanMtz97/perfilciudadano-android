package com.example.perfilciudadano.models

data class SignInResponse(
  val success: Boolean,
  val folium: String? = null,
  val role: Roles? = null,
)
