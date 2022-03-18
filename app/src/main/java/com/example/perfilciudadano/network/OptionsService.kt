package com.example.perfilciudadano.network

import android.util.Log
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
      Log.v("OPTIONS", options.toString())
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

  suspend fun getSectionsByColony(colony: String): List<Option> {
    return withContext(Dispatchers.IO) {
      val response = retrofit.create(OptionsApi::class.java).getSectionsByColony(colony)
      val sections = response.body() ?: emptyList()
      OptionsProvider.sections = sections
      sections
    }
  }
}