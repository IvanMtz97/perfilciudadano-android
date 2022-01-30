package com.example.perfilciudadano.network

import com.example.perfilciudadano.models.Option
import com.example.perfilciudadano.providers.GovernmentInvitationActivityOrThemesProvider
import com.example.perfilciudadano.services.RetrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GovernmentInvitationActivityOrThemesService {
  private val retrofit = RetrofitService.build()

  suspend fun getAllGovernmentInvitationActivityOrThemes(): List<Option> {
    return withContext(Dispatchers.IO) {
      val response = retrofit.create(GovernmentInvitationActivityOrThemesApi::class.java).getAllGovernmentInvitationActivityOrThemes()
      val data = response.body() ?: emptyList()
      GovernmentInvitationActivityOrThemesProvider.governmentInvitationActivityOrThemes = data
      data
    }
  }
}