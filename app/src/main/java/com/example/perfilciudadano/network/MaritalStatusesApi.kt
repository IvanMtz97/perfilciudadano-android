package com.example.perfilciudadano.network

import com.example.perfilciudadano.models.Option
import retrofit2.Response
import retrofit2.http.GET
import kotlin.collections.List

interface MaritalStatusesApi {
  @GET("/maritalStatuses")
  suspend fun getAllMaritalStatuses(): Response<List<Option>>
}