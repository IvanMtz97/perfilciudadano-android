package com.example.perfilciudadano.network

import com.example.perfilciudadano.models.Option
import com.example.perfilciudadano.providers.SportsProvider
import com.example.perfilciudadano.services.RetrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SportsService {
  private val retrofit = RetrofitService.build()

  suspend fun getAllSports(): List<Option> {
    return withContext(Dispatchers.IO) {
      val response = retrofit.create(SportsApi::class.java).getAllSports()
      val sports = response.body() ?: emptyList()
      SportsProvider.sports = sports
      sports
    }
  }
}