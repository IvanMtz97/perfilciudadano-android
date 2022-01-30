package com.example.perfilciudadano.network

import com.example.perfilciudadano.models.Option
import com.example.perfilciudadano.providers.DiseasesProvider
import com.example.perfilciudadano.services.RetrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DiseasesService {
  private val retrofit = RetrofitService.build()

  suspend fun getAllDiseases(): List<Option> {
    return withContext(Dispatchers.IO) {
      val response = retrofit.create(DiseasesApi::class.java).getAllDiseases()
      val diseases = response.body() ?: emptyList()
      DiseasesProvider.diseases = diseases
      diseases
    }
  }
}