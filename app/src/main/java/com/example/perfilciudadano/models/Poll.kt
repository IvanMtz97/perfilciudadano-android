package com.example.perfilciudadano.models

data class Poll(
  var ElectorKey: String = "",
  var Curp: String = "",
  var Name: String = "",
  var SurName: String = "",
  var SecondSurName: String = "",
  var BirthDate: String = "",
  var BirthPlace: String = "",
  var IneExpirationYear: Number = -1,
  var Gender: String = "M",
  var ZipCode: String = "",
  var Section: Int = -1,
  var Colony: Number = -1,
  var Street: String = "",
  var ExteriorNumber: String = "",
  var InteriorNumber: String = "",
  var IneMatchesLivingAddress: Boolean = true,
  var IneLivingAddress: String = "",
  var MaritalStatus: Int = -1,
  var FamilyPosition: Number = -1,
  var OtherFamilyPosition: String = "",
  var CellPhoneNumber: Long = -1,
  var PhoneNumber: Long = -1,
  var FamilyIntegrantsNumber: Number = -1,
  var HouseHasInternet: Boolean = true,
  var StudyDegree: Number = -1,
  var Occupation: Number = -1,
  var OtherOccupation: String = "",
  var MobilityMethod: Number = -1,
  var OtherMobilityMethod: String = "",
  var Diseases: List<Int> = emptyList(),
  var OtherDiseases: String = "",
  var FederalSupports: List<Number> = emptyList(),
  var OtherFederalSupport: String = "",
  var StateSupports: List<Number> = emptyList(),
  var OtherStateSupport: String = "",
  var MunicipalSupports: List<Number> = emptyList(),
  var OtherMunicipalSupport: String = "",
  var Hobbies: List<Number> = emptyList(),
  var OtherHobbies: String = "",
  var Religion: Number = -1,
  var OtherReligion: String = "",
  var Sports: List<Number> = emptyList(),
  var OtherSport: String = "",
  var SoccerTeam: Number = -1,
  var LikesPets: Boolean = true,
  var HasPets: Boolean = true,
  var PetTypes: List<Number> = emptyList(),
  var OtherPetType: String = "",
  var GovernmentInvitationActivityOrThemes: List<Number> = emptyList(),
  var OtherGovernmentInvitationActivityOrTheme: String = "",
  var GovernmentTaskActivityOrThemes: List<Number> = emptyList(),
  var PollutionCause: Number = -1,
  var PrincipalSectorProblematic: String = "",
  var FutureFiveYears: String = "",
  var Comments: String = "",
  var CreatedByFolium: Number = -1,
  var LeaderFolium: Number = -1,
)
