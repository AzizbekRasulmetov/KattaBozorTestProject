package com.azizbek.testproject.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azizbek.testproject.api.NetworkResult
import com.azizbek.testproject.api.WebService
import com.azizbek.testproject.models.PhoneOffer
import com.azizbek.testproject.models.toPhoneOffers
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel
class MyViewModel @Inject constructor(
    private val webService: WebService
) : ViewModel() {

    private val _state = MutableStateFlow<NetworkResult<List<PhoneOffer>?>>(NetworkResult.Loading())
    val state = _state.asStateFlow()

    init {
        getOffersFromApi()
    }

    private fun getOffersFromApi() {
        _state.value = NetworkResult.Loading()
        viewModelScope.launch {
            try {
                var offers: List<PhoneOffer>?
                withContext(IO) {
                    val response = webService.getOffers()
                    offers = response.offers?.filterNotNull()?.toPhoneOffers()
                }
                _state.value = NetworkResult.Success(offers)
            } catch (error: Exception) {
                _state.value = NetworkResult.Error(error.message)
            }
        }
    }

}