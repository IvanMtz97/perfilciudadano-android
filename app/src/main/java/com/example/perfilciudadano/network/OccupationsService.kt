package com.example.perfilciudadano.network

import com.example.perfilciudadano.models.Option
import com.example.perfilciudadano.providers.OccupationsProvider
import com.example.perfilciudadano.services.RetrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class OccupationsService {
  private val retrofit = RetrofitService.build()

  suspend fun getAllOccupations(): List<Option> {
    return withContext(Dispatchers.IO) {
      val response = retrofit.create(OccupationsApi::class.java).getAllOccupations()
      val occupations = response.body() ?: emptyList()
      OccupationsProvider.occupations = occupations
      occupations
    }
  }
}