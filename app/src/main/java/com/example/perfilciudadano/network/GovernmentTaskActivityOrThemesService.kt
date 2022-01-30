package com.example.perfilciudadano.network

import com.example.perfilciudadano.models.Option
import com.example.perfilciudadano.providers.GovernmentTaskActivityOrThemesProvider
import com.example.perfilciudadano.services.RetrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GovernmentTaskActivityOrThemesService {
  private val retrofit = RetrofitService.build()

  suspend fun getAllOptions(): List<Option> {
    return withContext(Dispatchers.IO) {
      val response = retrofit.create(GovernmentTaskActivityOrThemesApi::class.java).getAllGovernmentTaskActivityOrThemes()
      val options = response.body() ?: emptyList()
      GovernmentTaskActivityOrThemesProvider.governmentTaskActivitiyOrThemes = options
      options
    }
  }
}