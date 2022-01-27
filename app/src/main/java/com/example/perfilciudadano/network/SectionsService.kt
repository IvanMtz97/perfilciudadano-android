package com.example.perfilciudadano.network

import com.example.perfilciudadano.models.Option
import com.example.perfilciudadano.providers.SectionsProvider
import com.example.perfilciudadano.services.RetrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SectionsService {
  private val retrofit = RetrofitService.build()

  suspend fun getAllSections(): List<Option> {
    return withContext(Dispatchers.IO) {
      val response = retrofit.create(SectionsApi::class.java).getAllSections()
      val sections = response.body() ?: emptyList()
      SectionsProvider.sections = sections
      sections
    }
  }
}