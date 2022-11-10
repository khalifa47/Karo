package com.example.karo.components

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.karo.Routes

class MainViewModel : ViewModel() {
    private val _currentScreen = MutableLiveData<Routes>(Routes.Home)
    val currentScreen: LiveData<Routes> = _currentScreen

    fun setCurrentScreen(route: Routes) {
        _currentScreen.value = route
    }
}