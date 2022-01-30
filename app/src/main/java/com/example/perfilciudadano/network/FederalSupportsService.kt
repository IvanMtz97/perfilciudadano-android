package com.example.perfilciudadano.network

import com.example.perfilciudadano.models.Option
import com.example.perfilciudadano.providers.FederalSupportsProvider
import com.example.perfilciudadano.services.RetrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FederalSupportsService {
  private val retrofit = RetrofitService.build()

  suspend fun getAllFederalSupports(): List<Option> {
    return withContext(Dispatchers.IO) {
      val response = retrofit.create(FederalSupportsApi::class.java).getAllFederalSupports()
      val federalSupports = response.body() ?: emptyList()
      FederalSupportsProvider.federalSupports = federalSupports
      federalSupports
    }
  }
}