package com.example.perfilciudadano.network

import com.example.perfilciudadano.models.Option
import com.example.perfilciudadano.providers.ReligionsProvider
import com.example.perfilciudadano.services.RetrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ReligionsService {
  private val retrofit = RetrofitService.build()

  suspend fun getAllReligions(): List<Option> {
    return withContext(Dispatchers.IO) {
      val response = retrofit.create(ReligionsApi::class.java).getAllReligions()
      val religions = response.body() ?: emptyList()
      ReligionsProvider.religions = religions
      religions
    }
  }
}