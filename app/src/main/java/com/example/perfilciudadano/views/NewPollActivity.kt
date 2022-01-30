package com.example.perfilciudadano.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.perfilciudadano.R
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.perfilciudadano.models.Option
import com.example.perfilciudadano.models.Poll
import com.example.perfilciudadano.viewmodel.*

class NewPollActivity : AppCompatActivity() {
  private var poll: Poll = Poll()
  private val pollViewModel: PollViewModel by viewModels()
  private val sectionsViewModel: SectionsViewModel by viewModels()
  private val zipCodesViewModel: ZipCodesViewModel by viewModels()
  private val coloniesViewModel: ColoniesViewModel by viewModels()
  private val maritalStatusesViewModel: MaritalStatusesViewModel by viewModels()
  private val familyPositionsViewModel: FamilyPositionsViewModel by viewModels()
  private val studyDegreesViewModel: StudyDegreesViewModel by viewModels()
  private val occupationsViewModel: OccupationsViewModel by viewModels()
  private val mobilityMethodsViewModel: MobilityMethodsViewModel by viewModels()
  private val diseasesViewModel: DiseasesViewModel by viewModels()
  private val federalSupportsViewModel: FederalSupportsViewModel by viewModels()
  private val stateSupportsViewModel: StateSupportsViewModel by viewModels()
  private val municipalSupportsViewModel: MunicipalSupportsViewModel by viewModels()
  private val hobbiesViewModel: HobbiesViewModel by viewModels()
  private val religionsViewModel: ReligionsViewModel by viewModels()
  private val sportsViewModel: SportsViewModel by viewModels()
  private val soccerTeamsViewModel: SoccerTeamsViewModel by viewModels()
  private val petTypesViewModel: PetTypesViewModel by viewModels()
  private val governmentInvitationActivityOrThemesViewModel: GovernmentInvitationActivityOrThemesViewModel by viewModels()
  private val governmentTaskActivityOrThemesViewModel: GovernmentTaskActivityOrThemesViewModel by viewModels()
  private val pollutionCausesViewModel: PollutionCausesViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_new_poll)
    this.supportActionBar?.hide()
    sectionsViewModel.getAllSections()
    zipCodesViewModel.getAllZipCodes()
    maritalStatusesViewModel.getAllMaritalStatuses()
    familyPositionsViewModel.getAllFamilyPositions()
    studyDegreesViewModel.getAllStudyDegrees()
    occupationsViewModel.getAllOccupations()
    mobilityMethodsViewModel.getAllMobilityMethods()
    diseasesViewModel.getAllDiseases()
    federalSupportsViewModel.getAllFederalSupports()
    stateSupportsViewModel.getAllStateSupports()
    municipalSupportsViewModel.getAllMunicipalSupports()
    hobbiesViewModel.getAllHobbies()
    religionsViewModel.getAllReligions()
    sportsViewModel.getAllSports()
    soccerTeamsViewModel.getAllSoccerTeams()
    petTypesViewModel.getAllPetTypes()
    governmentInvitationActivityOrThemesViewModel.getAllOptions()
    governmentTaskActivityOrThemesViewModel.getAllOptions()
    pollutionCausesViewModel.getAllOptions()

    fun logSelectedData(tag: String, item: Option) {
      Log.d("SELECTED ${tag.uppercase()}", "{ id: ${item.id}, value: ${item.name} }")
    }

    sectionsViewModel.selectedSection.observe(this, Observer { item ->
      poll.Section = item.id
      pollViewModel.updatePoll(poll)
      logSelectedData("SECTION", item)
    })
    zipCodesViewModel.selectedZipCode.observe(this, Observer { item ->
      poll.ZipCode = item.id
      pollViewModel.updatePoll(poll)
      coloniesViewModel.clear()
      coloniesViewModel.getColoniesByZipCode(item.name)
      logSelectedData("ZIP CODE", item)
    })
    coloniesViewModel.selectedColony.observe(this, Observer { item ->
      poll.Colony = item.id
      pollViewModel.updatePoll(poll)
      logSelectedData("COLONY", item)
    })
    maritalStatusesViewModel.selectedMaritalStatus.observe(this, Observer { item ->
      poll.MaritalStatus = item.id
      pollViewModel.updatePoll(poll)
      logSelectedData("MARITAL STATUS", item)
    })
    familyPositionsViewModel.selectedFamilyPosition.observe(this, Observer { item ->
      poll.FamilyPosition = item.id
      pollViewModel.updatePoll(poll)
      logSelectedData("FAMILY POS", item)
    })
    studyDegreesViewModel.selectedStudyDegree.observe(this, Observer { item ->
      poll.StudyDegree = item.id
      pollViewModel.updatePoll(poll)
      logSelectedData("STUDY DEGREE", item)
    })
    occupationsViewModel.selectedOccupation.observe(this, Observer { item ->
      poll.Occupation = item.id
      pollViewModel.updatePoll(poll)
      logSelectedData("OCCUPATION", item)
    })
    mobilityMethodsViewModel.selectedMobilityMethod.observe(this, Observer { item ->
      poll.MobilityMethod = item.id
      pollViewModel.updatePoll(poll)
      logSelectedData("MOBILITY METHOD", item)
    })
    diseasesViewModel.selectedDiseases.observe(this, Observer { items ->
      poll.Diseases = items.map { it -> it.id }
      pollViewModel.updatePoll(poll)
    })
    federalSupportsViewModel.selectedFederalSupports.observe(this, Observer { items ->
      poll.FederalSupports = items.map { it -> it.id }
      pollViewModel.updatePoll(poll)
    })
    stateSupportsViewModel.selectedStateSupports.observe(this, Observer { items ->
      poll.StateSupports = items.map { it -> it.id }
      pollViewModel.updatePoll(poll)
    })
    municipalSupportsViewModel.selectedMunicipalSupports.observe(this, Observer { items ->
      poll.MunicipalSupports = items.map { it -> it.id }
      pollViewModel.updatePoll(poll)
    })
    hobbiesViewModel.selectedHobbies.observe(this, Observer { items ->
      poll.Hobbies = items.map { it -> it.id }
      pollViewModel.updatePoll(poll)
    })
    religionsViewModel.selectedReligion.observe(this, Observer { item ->
      poll.Religion = item.id
      pollViewModel.updatePoll(poll)
    })
    sportsViewModel.selectedSports.observe(this, Observer { items ->
      poll.Sports = items.map { it -> it.id }
      pollViewModel.updatePoll(poll)
    })
    soccerTeamsViewModel.selectedSoccerTeam.observe(this, Observer { item ->
      poll.SoccerTeam = item.id
      pollViewModel.updatePoll(poll)
    })
    petTypesViewModel.selectedPetTypes.observe(this, Observer { items ->
      poll.PetTypes = items.map { it -> it.id }
      pollViewModel.updatePoll(poll)
    })
    governmentInvitationActivityOrThemesViewModel.selectedOptions.observe(this, Observer { items ->
      poll.GovernmentInvitationActivityOrThemes = items.map { it -> it.id }
      pollViewModel.updatePoll(poll)
    })
    governmentTaskActivityOrThemesViewModel.selectedOptions.observe(this, Observer { items ->
      poll.GovernmentTaskActivityOrThemes = items.map { it -> it.id }
      pollViewModel.updatePoll(poll)
    })
    pollutionCausesViewModel.selectedOption.observe(this, Observer { item ->
      poll.PollutionCause = item.id
      pollViewModel.updatePoll(poll)
    })
    pollViewModel.poll.observe(this, Observer { item ->
      Log.d("UPDATED POLL", item.toString())
    })
  }
}
