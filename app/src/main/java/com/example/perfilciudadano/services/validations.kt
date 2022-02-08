package com.example.perfilciudadano.services

import android.util.Log
import com.example.perfilciudadano.models.Poll
import com.example.perfilciudadano.viewmodel.PollViewModel
import com.google.android.material.textfield.TextInputLayout

class validations {
  companion object {
    private val PENDING: String = "PENDIENTE"
    private val ELECTOR_KEY_LENGTH = 18
    private val CURP_LENGTH = 18
    private val PHONE_LENGTH = 10
    private val PRISTINE_VALUE = -1

    fun validateSelectionInput(fieldValue: Number, layout: TextInputLayout, pollViewModel: PollViewModel, fieldId: String) {
      layout.error = null
      if (fieldValue == PRISTINE_VALUE) {
        layout.error = "Selecciona una opcion"
      }

      if (layout.error.isNullOrEmpty()) {
        pollViewModel.removePollError(fieldId)
      } else {
        pollViewModel.addPollError(fieldId)
      }
    }

    fun validateSelectionWithOtherInput(fieldValue: Number, layout: TextInputLayout, pollViewModel: PollViewModel, fieldId: String) {
      layout.error = null

      if (layout.error.isNullOrEmpty()) {
        pollViewModel.removePollError(fieldId)
      } else {
        pollViewModel.addPollError(fieldId)
      }
    }

    fun validateElectorKeyInput(text: String, layout: TextInputLayout, pollViewModel: PollViewModel, fieldId: String) {
      layout.error = null
      if (text != PENDING && text.length != ELECTOR_KEY_LENGTH) {
        layout.error = "Ingresa una clave de elector valida de ${ELECTOR_KEY_LENGTH} caracteres"
      }

      if (layout.error.isNullOrEmpty()) {
        pollViewModel.removePollError(fieldId)
      } else {
        pollViewModel.addPollError(fieldId)
      }
    }

    fun validateCurpInput(text: String, layout: TextInputLayout, pollViewModel: PollViewModel, fieldId: String) {
      layout.error = null
      if (text != PENDING && text.length != CURP_LENGTH) {
        layout.error = "Ingresa un CURP valido de ${CURP_LENGTH} caracteres"
      }

      if (layout.error.isNullOrEmpty()) {
        pollViewModel.removePollError(fieldId)
      } else {
        pollViewModel.addPollError(fieldId)
      }
    }

    fun validateName(text: String, layout: TextInputLayout, pollViewModel: PollViewModel, fieldId: String) {
      layout.error = null
      if (text.length < 2) {
        layout.error = "Ingresa un nombre valido"
      }

      if (layout.error.isNullOrEmpty()) {
        pollViewModel.removePollError(fieldId)
      } else {
        pollViewModel.addPollError(fieldId)
      }
    }

    fun validateSurName(text: String, layout: TextInputLayout, pollViewModel: PollViewModel, fieldId: String) {
      layout.error = null
      if (text.length < 2) {
        layout.error = "Ingresa un apellido paterno valido"
      }

      if (layout.error.isNullOrEmpty()) {
        pollViewModel.removePollError(fieldId)
      } else {
        pollViewModel.addPollError(fieldId)
      }
    }

    fun validateSecondSurName(text: String, layout: TextInputLayout, pollViewModel: PollViewModel, fieldId: String) {
      layout.error = null
      if (text.length < 2) {
        layout.error = "Ingresa un apellido materno valido"
      }

      if (layout.error.isNullOrEmpty()) {
        pollViewModel.removePollError(fieldId)
      } else {
        pollViewModel.addPollError(fieldId)
      }
    }

    fun validateBirthPlace(text: String, layout: TextInputLayout, pollViewModel: PollViewModel, fieldId: String) {
      layout.error = null
      if (text.isNullOrBlank() && text.length != 4) {
        layout.error = "Ingresa un lugar de nacimiento valido"
      }

      if (layout.error.isNullOrEmpty()) {
        pollViewModel.removePollError(fieldId)
      } else {
        pollViewModel.addPollError(fieldId)
      }
    }

    fun validateIneExpirationYear(text: String, layout: TextInputLayout, pollViewModel: PollViewModel, fieldId: String) {
      layout.error = null
      if (text.isNullOrEmpty() || text.length != 4) {
        layout.error = "Ingresa una fecha de expiracion valida"
      }

      if (layout.error.isNullOrEmpty()) {
        pollViewModel.removePollError(fieldId)
      } else {
        pollViewModel.addPollError(fieldId)
      }
    }

    fun validateZipCodeInput(text: String, layout: TextInputLayout, pollViewModel: PollViewModel, fieldId: String) {
      layout.error = null
      if (text.length != 5) {
        layout.error = "Ingresa codigo postal valido"
      }

      if (layout.error.isNullOrEmpty()) {
        pollViewModel.removePollError(fieldId)
      } else {
        pollViewModel.addPollError(fieldId)
      }
    }

    fun validateStreet(text: String, layout: TextInputLayout, pollViewModel: PollViewModel, fieldId: String) {
      layout.error = null
      if (text.isNullOrEmpty() || text.length < 3) {
        layout.error = "Ingresa un nombre de calle valido"
      }

      if (layout.error.isNullOrEmpty()) {
        pollViewModel.removePollError(fieldId)
      } else {
        pollViewModel.addPollError(fieldId)
      }
    }

    fun validateExteriorNumberInput(text: String, layout: TextInputLayout, pollViewModel: PollViewModel, fieldId: String) {
      layout.error = null
      if (text.isNullOrEmpty()) {
        layout.error = "Ingresa un numero exterior valido"
      }

      if (layout.error.isNullOrEmpty()) {
        pollViewModel.removePollError(fieldId)
      } else {
        pollViewModel.addPollError(fieldId)
      }
    }

    fun validateCellPhoneNumberInput(text: String, layout: TextInputLayout, pollViewModel: PollViewModel, fieldId: String) {
      layout.error = null
      if (text.length != PHONE_LENGTH) {
        layout.error = "Ingresa un numero de celular valido"
      }

      if (layout.error.isNullOrEmpty()) {
        pollViewModel.removePollError(fieldId)
      } else {
        pollViewModel.addPollError(fieldId)
      }
    }

    fun validatePhoneNumberInput(text: String, layout: TextInputLayout, pollViewModel: PollViewModel, fieldId: String) {
      layout.error = null
      if (text.length != PHONE_LENGTH) {
        layout.error = "Ingresa un numero de telefono valido"
      }

      if (layout.error.isNullOrEmpty()) {
        pollViewModel.removePollError(fieldId)
      } else {
        pollViewModel.addPollError(fieldId)
      }
    }

    fun validateFamilyIntegrantsNumberInput(text: String, layout: TextInputLayout, pollViewModel: PollViewModel, fieldId: String) {
      layout.error = null
      if (text.isNullOrEmpty()) {
        layout.error = "Ingresa un numero de integrantes valido"
      }

      if (layout.error.isNullOrEmpty()) {
        pollViewModel.removePollError(fieldId)
      } else {
        pollViewModel.addPollError(fieldId)
      }
    }
  }
}