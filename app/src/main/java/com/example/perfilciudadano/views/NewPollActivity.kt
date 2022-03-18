package com.example.perfilciudadano.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.perfilciudadano.R
import com.example.perfilciudadano.fragments.NewPollFormFragment
import com.example.perfilciudadano.providers.PollProvider
import com.example.perfilciudadano.viewmodel.*

class NewPollActivity : AppCompatActivity() {
  private val pollViewModel: PollViewModel by viewModels()
  private val optionsViewModel: OptionsViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_new_poll)
    this.supportActionBar?.hide()
    val foliumId = intent.getIntExtra("foliumId", 0)
    Log.v("VIEWERFOLIUM2", foliumId.toString())
    val transaction = supportFragmentManager.beginTransaction()
    val fragment = NewPollFormFragment.newInstance(foliumId)
    transaction.replace(R.id.newPollFragmentContainer, fragment)
    transaction.commit()
    NewPollFormFragment.newInstance(foliumId)
    optionsViewModel.getAllOptions()
    pollViewModel.poll.observe(this, { item ->
      Log.d("UPDATED POLL", item.toString())
    })
    pollViewModel.pollErrors.observe(this, { item ->
      Log.d("UPDATED ERRORS", item.toString())
    })
  }
}
