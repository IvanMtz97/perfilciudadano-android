package com.example.perfilciudadano.network

import com.example.perfilciudadano.models.Option
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface OptionsApi {
  @GET("/options")
  suspend fun getAllOptions(): Response<List<Option>>

  @GET("/colonies/{zipCode}")
  suspend fun getColoniesByZipCode(@Path("zipCode") zipCode: String): Response<List<Option>>
}