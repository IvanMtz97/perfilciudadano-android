package com.example.perfilciudadano.network

import com.example.perfilciudadano.models.Option
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface OptionsApi {
  @GET("/options")
  suspend fun getAllOptions(): Response<List<Option>>

  @GET("/coloniesByZipCode")
  suspend fun getColoniesByZipCode(@Query("zipCode") zipCode: String): Response<List<Option>>

  @GET("/sectionsByColony")
  suspend fun getSectionsByColony(@Query("colony") colony: String): Response<List<Option>>
}