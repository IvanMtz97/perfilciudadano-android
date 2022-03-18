package com.example.perfilciudadano.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import com.example.perfilciudadano.R
import com.example.perfilciudadano.adapters.OperatorPollListAdapter
import com.example.perfilciudadano.models.OperatorPoll
import com.example.perfilciudadano.providers.OperatorPollsProvider

class OperatorPollsActivity : AppCompatActivity() {
  private lateinit var Polls: List<OperatorPoll>
  private lateinit var PollsList: ListView

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_operator_polls)
    this.supportActionBar?.hide()
    PollsList = findViewById(R.id.lvOperatorPolls)
    val listHeader = findViewById<LinearLayout>(R.id.listHeader)
    val txtNoData = findViewById<TextView>(R.id.txtNoData)
    Polls = OperatorPollsProvider.getPolls(this)

    if (Polls.isNullOrEmpty()) {
      listHeader.visibility = View.GONE
      txtNoData.visibility = View.VISIBLE
    }
    val adapter = OperatorPollListAdapter(this, Polls)
    PollsList.adapter = adapter
    PollsList.setOnItemClickListener{ adapterView, view, i, l ->
      val item = adapter.getItem(i)
      item?.let {
        val intent = Intent(this, LeaderPollsActivity::class.java)
        intent.putExtra("foliumId", item.generatedfoliumid)
        intent.putExtra("foliumFullName", item.name)
        intent.putExtra("isViewerLeader", true)
        startActivity(intent)
      }
    }
  }
}