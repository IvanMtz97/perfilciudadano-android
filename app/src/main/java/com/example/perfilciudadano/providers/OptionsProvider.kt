package com.example.perfilciudadano.providers

import com.example.perfilciudadano.models.Option

class OptionsProvider {
  companion object {
    var options: List<Option> = emptyList()
    var colonies: List<Option> = emptyList()
    var sections: List<Option> = emptyList()
  }
}