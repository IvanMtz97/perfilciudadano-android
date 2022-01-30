package com.example.perfilciudadano.network

import com.example.perfilciudadano.models.Option
import com.example.perfilciudadano.providers.MunicipalSupportsProvider
import com.example.perfilciudadano.services.RetrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MunicipalSupportsService {
  private val retrofit = RetrofitService.build()

  suspend fun getAllMunicipalSupports(): List<Option> {
    return withContext(Dispatchers.IO) {
      val response = retrofit.create(MunicipalSupportsApi::class.java).getAllMunicipalSupports()
      val municipalSupports = response.body() ?: emptyList()
      MunicipalSupportsProvider.municipalSupports = municipalSupports
      municipalSupports
    }
  }
}