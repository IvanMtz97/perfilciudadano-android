package com.example.perfilciudadano.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import com.example.perfilciudadano.R
import com.example.perfilciudadano.adapters.LeaderPollListAdapter
import com.example.perfilciudadano.models.LeaderPolls
import com.example.perfilciudadano.providers.LeaderPollsProvider

class LeaderPollsActivity : AppCompatActivity() {
  private lateinit var Polls: List<LeaderPolls>
  private lateinit var PollsList: ListView

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_leader_polls)
    this.supportActionBar?.hide()
    PollsList = findViewById(R.id.rvLeaderPolls)
    val listTitle = findViewById<TextView>(R.id.txtListTitle)
    val btnCreateStruct = findViewById<Button>(R.id.btnCreateStruct)
    val listHeader = findViewById<LinearLayout>(R.id.listHeader)
    val txtNoData = findViewById<TextView>(R.id.txtNoData)

    val foliumId = intent.getIntExtra("foliumId", 0)
    val fullName = intent.getStringExtra("foliumFullName")
    val isViewerLeader = intent.getBooleanExtra("isViewerLeader", false)
    if (isViewerLeader) {
      btnCreateStruct.visibility = View.VISIBLE
    }
    listTitle.text = "Encuestados de $fullName"
    Polls = LeaderPollsProvider.getPolls(foliumId)

    if (Polls.isNullOrEmpty()) {
      txtNoData.visibility = View.VISIBLE
      listHeader.visibility = View.GONE
    }
    val adapter = LeaderPollListAdapter(this, Polls)
    PollsList.adapter = adapter
    btnCreateStruct.setOnClickListener {
      val intent = Intent(this, NewPollActivity::class.java)
      intent.putExtra("isViewerLeader", true)
      intent.putExtra("foliumId", foliumId)
      startActivity(intent)
    }
  }
}