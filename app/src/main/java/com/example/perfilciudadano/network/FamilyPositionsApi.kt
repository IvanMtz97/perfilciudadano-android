package com.example.perfilciudadano.network

import com.example.perfilciudadano.models.Option
import retrofit2.Response
import retrofit2.http.GET

interface FamilyPositionsApi {
  @GET("/familyPositions")
  suspend fun getAllFamilyPositions(): Response<List<Option>>
}