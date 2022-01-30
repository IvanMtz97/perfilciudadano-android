package com.example.perfilciudadano.network

import com.example.perfilciudadano.models.Option
import com.example.perfilciudadano.providers.StateSupportsProvider
import com.example.perfilciudadano.services.RetrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class StateSupportsService {
  private val retrofit = RetrofitService.build()

  suspend fun getAllStateSupports(): List<Option> {
    return withContext(Dispatchers.IO) {
      val response = retrofit.create(StateSupportsApi::class.java).getAllStateSupports()
      val stateSupports = response.body() ?: emptyList()
      StateSupportsProvider.stateSupports = stateSupports
      stateSupports
    }
  }
}