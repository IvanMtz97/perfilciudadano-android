package com.example.perfilciudadano.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.perfilciudadano.R
import com.example.perfilciudadano.viewmodel.SectionsViewModel
import androidx.lifecycle.Observer

class NewPollActivity : AppCompatActivity() {
  private val sectionsViewModel: SectionsViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_new_poll)
    this.supportActionBar?.hide()
    sectionsViewModel.getAllSections()
    sectionsViewModel.selectedSection.observe(this, Observer { item ->
      Log.e("SELECTED SECTION", "{ id: ${item.id}, value: ${item.value} }")
    })
  }
}
