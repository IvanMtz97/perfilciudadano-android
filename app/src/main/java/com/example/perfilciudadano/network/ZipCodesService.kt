package com.example.perfilciudadano.network

import com.example.perfilciudadano.models.Option
import com.example.perfilciudadano.providers.ZipCodesProvider
import com.example.perfilciudadano.services.RetrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ZipCodesService {
  private val retrofit = RetrofitService.build()

  suspend fun getAllZipCodes(): List<Option> {
    return withContext(Dispatchers.IO) {
      val response = retrofit.create(ZipCodesApi::class.java).getAllZipCodes()
      val zipCodes = response.body() ?: emptyList()
      ZipCodesProvider.zipCodes = zipCodes
      zipCodes
    }
  }
}