package com.example.perfilciudadano.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.perfilciudadano.models.Option
import com.example.perfilciudadano.network.SoccerTeamsService
import kotlinx.coroutines.launch

class SoccerTeamsViewModel : ViewModel() {
  private val mutableSoccerTeams = MutableLiveData<List<Option>>()
  private val mutableSelectedSoccerTeam = MutableLiveData<Option>()
  val soccerTeams: LiveData<List<Option>> get() = mutableSoccerTeams
  val selectedSoccerTeam: LiveData<Option> get() = mutableSelectedSoccerTeam

  fun selectSoccerTeam(option: Option) {
    mutableSelectedSoccerTeam.value = option
  }

  fun getAllSoccerTeams() {
    viewModelScope.launch {
      val soccerTeams = SoccerTeamsService().getAllSoccerTeams()

      if (!soccerTeams.isNullOrEmpty()) {
        mutableSoccerTeams.postValue(soccerTeams)
      }
    }
  }
}