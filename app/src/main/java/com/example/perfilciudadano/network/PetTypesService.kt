package com.example.perfilciudadano.network

import com.example.perfilciudadano.models.Option
import com.example.perfilciudadano.providers.PetTypesProvider
import com.example.perfilciudadano.services.RetrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PetTypesService {
  private val retrofit = RetrofitService.build()

  suspend fun getAllPetTypes(): List<Option> {
    return withContext(Dispatchers.IO) {
      val response = retrofit.create(PetTypesApi::class.java).getAllPetTypes()
      val petTypes = response.body() ?: emptyList()
      PetTypesProvider.petTypes = petTypes
      petTypes
    }
  }
}