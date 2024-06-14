package com.example.sakhcast.ui.profile_screen

//
//@HiltViewModel
//class ProfileScreenViewModel @Inject constructor(
//    private val dataStore: DataStore<Preferences>,
//    private val sakhCastRepository: SakhCastRepository
//) : ViewModel() {
//
//    private var _profileScreenState = MutableLiveData(ProfileScreenState())
//    val profileScreenState: LiveData<ProfileScreenState> = _profileScreenState
//
//    init {
//        viewModelScope.launch {
//            _profileScreenState.value =
//                profileScreenState.value?.copy(
//                    currentUser = UserSample.getUserInfo(),
//                    isLogged = getIsLoggedInDataStore()
//                )
//        }
//    }
//
//    data class ProfileScreenState(
//        var currentUser: CurentUser? = null,
//        var isLogged: Boolean? = null
//    )
//
//    fun onLogoutButtonPushed() {
//        viewModelScope.launch {
//            sakhCastRepository.userLogout()
//            saveIsLoggedInDataStore(false)
//            _profileScreenState.value = profileScreenState.value?.copy(
//                isLogged = false
//            )
//        }
//    }
//
//    private suspend fun saveIsLoggedInDataStore(isLogged: Boolean) {
//        dataStore.edit { preferences ->
//            preferences[booleanPreferencesKey(IS_LOGGED_IN_KEY)] = isLogged
//        }
//    }
//
//    private suspend fun getIsLoggedInDataStore(): Boolean {
//        val preferences = dataStore.data.map { preferences ->
//            preferences[booleanPreferencesKey(IS_LOGGED_IN_KEY)] ?: false
//        }.first()
//        _profileScreenState.value = profileScreenState.value?.copy(isLogged = preferences)
//        return preferences
//    }
//
//    fun checkDataStoreInitialization(): Boolean {
//        var isInitialized = false
//        viewModelScope.launch {
//            isInitialized = dataStore.data.map { preferences ->
//                preferences.contains(booleanPreferencesKey(IS_LOGGED_IN_KEY))
//            }.first()
//        }
//        return isInitialized
//    }
//
//}