package com.example.perfilciudadano.network

import com.example.perfilciudadano.models.Option
import com.example.perfilciudadano.providers.FamilyPositionsProvider
import com.example.perfilciudadano.services.RetrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FamilyPositionsService {
  private val retrofit = RetrofitService.build()

  suspend fun getAllFamilyPositions(): List<Option> {
    return withContext(Dispatchers.IO) {
      val response = retrofit.create(FamilyPositionsApi::class.java).getAllFamilyPositions()
      val familyPositions: List<Option> = response.body() ?: emptyList()
      FamilyPositionsProvider.familyPositions = familyPositions
      familyPositions
    }
  }
}