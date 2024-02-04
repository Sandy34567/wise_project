package com.example.wise_project.ViewModel

import androidx.lifecycle.ViewModel
import com.example.wise_project.Model.Drink
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor() : ViewModel() {
    private val _selectedDrink = MutableStateFlow<Drink?>(null)
    val selectedDrink: StateFlow<Drink?> get() = _selectedDrink

    fun setSelectedDrink(drink: Drink) {
        _selectedDrink.value = drink
    }
}