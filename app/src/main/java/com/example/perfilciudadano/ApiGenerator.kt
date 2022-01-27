package com.example.perfilciudadano

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiGenerator {
  private val client = OkHttpClient.Builder().build()
  private val retrofit = Retrofit.Builder()
    .baseUrl("http://192.168.0.9:3000")
    .addConverterFactory(GsonConverterFactory.create())
    .client(client)
    .build()

  fun <T> buildService(service: Class<T>): T {
    return retrofit.create(service)
  }
}