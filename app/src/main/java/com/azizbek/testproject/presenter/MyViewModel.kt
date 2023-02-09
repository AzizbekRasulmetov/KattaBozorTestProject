package com.azizbek.testproject.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azizbek.testproject.data.network.NetworkResult
import com.azizbek.testproject.domain.models.PhoneOffer
import com.azizbek.testproject.domain.use_case.GetMobileOffersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class MyViewModel @Inject constructor(
    private val getMobileOffersUseCase: GetMobileOffersUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<NetworkResult<List<PhoneOffer>>>(NetworkResult.Loading())
    val state = _state.asStateFlow()

    init {
        getOffersFromApi()
    }

    private fun getOffersFromApi() {
        _state.value = NetworkResult.Loading()
        viewModelScope.launch {
            _state.value = getMobileOffersUseCase()
        }
    }

}