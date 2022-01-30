package com.example.perfilciudadano.network

import com.example.perfilciudadano.models.Option
import com.example.perfilciudadano.providers.PollutionCausesProvider
import com.example.perfilciudadano.services.RetrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PollutionCausesService {
  private val retrofit = RetrofitService.build()

  suspend fun getAllPolutionCauses(): List<Option> {
    return withContext(Dispatchers.IO) {
      val response = retrofit.create(PollutionCausesApi::class.java).getAllPolutionCauses()
      val data = response.body() ?: emptyList()
      PollutionCausesProvider.pollutionCauses = data
      data
    }
  }
}