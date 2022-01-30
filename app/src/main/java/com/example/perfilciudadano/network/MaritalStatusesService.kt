package com.example.perfilciudadano.network

import com.example.perfilciudadano.models.Option
import com.example.perfilciudadano.providers.MaritalStatusesProvider
import com.example.perfilciudadano.services.RetrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MaritalStatusesService {
  private val retrofit = RetrofitService.build()

  suspend fun getAllMaritalStatuses(): List<Option> {
    return withContext(Dispatchers.IO) {
      val response = retrofit.create(MaritalStatusesApi::class.java).getAllMaritalStatuses()
      val maritalStatuses = response.body() ?: emptyList()
      MaritalStatusesProvider.maritalStatuses = maritalStatuses
      maritalStatuses
    }
  }
}