package com.example.perfilciudadano.network

import com.example.perfilciudadano.models.Option
import com.example.perfilciudadano.providers.MobilityMethodsProvider
import com.example.perfilciudadano.services.RetrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MobilityMethodsService {
  private val retrofit = RetrofitService.build()

  suspend fun getAllMobilityMethods(): List<Option> {
    return withContext(Dispatchers.IO) {
      val response = retrofit.create(MobilityMethodsApi::class.java).getAllMobilityMethods()
      val mobilityMethods = response.body() ?: emptyList()
      MobilityMethodsProvider.mobilityMethods = mobilityMethods
      mobilityMethods
    }
  }
}