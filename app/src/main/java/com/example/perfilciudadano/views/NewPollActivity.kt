package com.example.perfilciudadano.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.perfilciudadano.R
import com.example.perfilciudadano.viewmodel.SectionsViewModel
import androidx.lifecycle.Observer
import com.example.perfilciudadano.viewmodel.ColoniesViewModel
import com.example.perfilciudadano.viewmodel.ZipCodesViewModel

class NewPollActivity : AppCompatActivity() {
  private val sectionsViewModel: SectionsViewModel by viewModels()
  private val zipCodesViewModel: ZipCodesViewModel by viewModels()
  private val coloniesViewModel: ColoniesViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_new_poll)
    this.supportActionBar?.hide()
    sectionsViewModel.getAllSections()
    zipCodesViewModel.getAllZipCodes()
    sectionsViewModel.selectedSection.observe(this, Observer { item ->
      Log.e("SELECTED SECTION", "{ id: ${item.id}, value: ${item.value} }")
    })
    zipCodesViewModel.selectedZipCode.observe(this, Observer { item ->
      Log.e("SELECTED ZIP CODE", "{ id: ${item.id}, value: ${item.value} }")
      coloniesViewModel.clear()
      coloniesViewModel.getColoniesByZipCode(item.value)
    })
    coloniesViewModel.selectedColony.observe(this, Observer { item ->
      Log.e("SELECTED COLONY", "{ id: ${item.id}, value: ${item.value} }")
    })
  }
}
