package com.example.perfilciudadano.network

import com.example.perfilciudadano.models.Option
import com.example.perfilciudadano.providers.OptionsProvider
import com.example.perfilciudadano.services.RetrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class OptionsService {
  private val retrofit = RetrofitService.build()

  suspend fun getAllOptions(): List<Option> {
    return withContext(Dispatchers.IO) {
      val response = retrofit.create(OptionsApi::class.java).getAllOptions()
      val options = response.body() ?: emptyList()
      OptionsProvider.options = options
      options
    }
  }

  suspend fun getColoniesByZipCode(zipCode: String): List<Option> {
    return withContext(Dispatchers.IO) {
      val response = retrofit.create(OptionsApi::class.java).getColoniesByZipCode(zipCode)
      val colonies = response.body() ?: emptyList()
      OptionsProvider.colonies = colonies
      colonies
    }
  }
}