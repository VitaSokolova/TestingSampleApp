package com.example.testingsampleapp.presentation.overview

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testingsampleapp.domain.GetDailyForecastUseCase
import com.example.testingsampleapp.presentation.overview.models.ForecastState
import com.example.testingsampleapp.utils.dispatchers.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class WeatherOverviewViewModel @Inject constructor(
    private val getDailyForecastUseCase: GetDailyForecastUseCase,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {

    private val _forecastState = MutableLiveData<ForecastState>()
    val forecastState: LiveData<ForecastState> = _forecastState

    fun loadForecast() {
        viewModelScope.launch() {
            _forecastState.value = ForecastState.Loading
            try {
                _forecastState.value = ForecastState.Data(
                    forecast = withContext(dispatcherProvider.io()) {
                        getDailyForecastUseCase(LONDON_ID)
                    }
                )
            } catch (e: Exception) {
                _forecastState.value = ForecastState.Error
                Log.e("WeatherOverview", e.toString())
            }
        }
    }

    fun onRetryButtonClick() {
        loadForecast()
    }
}

const val LONDON_ID = "44418"