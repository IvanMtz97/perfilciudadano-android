package com.example.perfilciudadano.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.TextView
import com.example.perfilciudadano.R
import com.example.perfilciudadano.models.Option

class OptionsAdapter(context: Context, var resources: Int, options: List<Option>) : ArrayAdapter<Option>(context, resources, options) {
  private var allOptions: List<Option> = options
  override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
    val layoutInflater = LayoutInflater.from(context)
    val view = layoutInflater.inflate(resources, null)
    val label = view.findViewById<TextView>(R.id.optionLabel)
    label.text = allOptions[position].value
    return view
  }

  override fun getItem(position: Int): Option? {
    return allOptions.get(position)
  }

  override fun getItemId(position: Int): Long {
    return allOptions.get(position).id.toLong()
  }

  override fun getCount(): Int {
    return allOptions.size
  }

  override fun getFilter(): Filter {
    return object : Filter() {
      override fun publishResults(charSequence: CharSequence?, filterResults: FilterResults?) {
        allOptions = filterResults?.values as List<Option>
        notifyDataSetChanged()
      }

      override fun performFiltering(charSequence: CharSequence?): FilterResults {
        val queryString = charSequence.toString().lowercase()
        val filterResults = FilterResults()
        filterResults.values = if (queryString == null || queryString.isEmpty())
          allOptions
        else
          allOptions.filter {
            it.value.lowercase().contains(queryString)
          }

        return filterResults
      }
    }
  }
}