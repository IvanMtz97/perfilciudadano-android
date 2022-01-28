package com.example.perfilciudadano.network

import com.example.perfilciudadano.models.Option
import com.example.perfilciudadano.providers.ColoniesProvider
import com.example.perfilciudadano.services.RetrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ColoniesService {
  private val retrofit = RetrofitService.build()

  suspend fun getColoniesByZipCode(zipCode: String): List<Option> {
    return withContext(Dispatchers.IO) {
      val response = retrofit.create(ColoniesApi::class.java).getColoniesByZipCode(zipCode)
      val colonies = response.body() ?: emptyList()
      ColoniesProvider.colonies = colonies
      colonies
    }
  }
}