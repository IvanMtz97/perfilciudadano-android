package com.example.perfilciudadano.network

import com.example.perfilciudadano.models.Option
import com.example.perfilciudadano.providers.StudyDegreesProvider
import com.example.perfilciudadano.services.RetrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class StudyDegreesService {
  private val retrofit = RetrofitService.build()

  suspend fun getAllStudyDegrees(): List<Option> {
    return withContext(Dispatchers.IO) {
      val response = retrofit.create(StudyDegreesApi::class.java).getAllStudyDegrees()
      val studyDegrees = response.body() ?: emptyList()
      StudyDegreesProvider.studyDegrees = studyDegrees
      studyDegrees
    }
  }
}