package com.example.perfilciudadano.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.perfilciudadano.R
import com.example.perfilciudadano.models.OperatorPoll

class OperatorPollListAdapter(context: Context, polls: List<OperatorPoll>) : ArrayAdapter<OperatorPoll>(context, 0, polls) {
  private var allPolls: List<OperatorPoll> = polls

  override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
    val layoutInflater = LayoutInflater.from(context)
    var view = layoutInflater.inflate(R.layout.operator_poll_list_row, null)
    val txtDate = view.findViewById<TextView>(R.id.txtDate)
    val txtName = view.findViewById<TextView>(R.id.txtName)
    val txtFolium = view.findViewById<TextView>(R.id.txtFolium)
    val txtStructs = view.findViewById<TextView>(R.id.txtStructs)
    txtDate.text = allPolls[position].createdat
    txtName.text = allPolls[position].name
    txtFolium.text = allPolls[position].generatedfolium.toString()
    txtStructs.text = allPolls[position].structs.toString()
    return view
  }

  override fun getItem(position: Int): OperatorPoll {
    return allPolls[position]
  }

  override fun getItemId(position: Int): Long {
    return allPolls[position].id.toLong()
  }

  override fun getCount(): Int {
    return allPolls.size
  }
}