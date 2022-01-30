package com.example.perfilciudadano.network

import com.example.perfilciudadano.models.Option
import retrofit2.Response
import retrofit2.http.GET

interface OccupationsApi {
  @GET("/occupations")
  suspend fun getAllOccupations(): Response<List<Option>>
}