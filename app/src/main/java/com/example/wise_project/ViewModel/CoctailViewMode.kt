package com.example.wise_project.ViewModel

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wise_project.Model.Drink
import com.example.wise_project.Repository.cocktailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class CoctailViewMode @Inject constructor(private val repository: cocktailRepository) : ViewModel() {
    private val _searchText = MutableStateFlow("")
    val searchText: StateFlow<String>
        get() = _searchText

    val drinklist: StateFlow<List<Drink>>
        get() = repository.drinkList

    fun setSearchText(newText: String) {
        _searchText.value = newText
        // Call the repository method with the new search text
        viewModelScope.launch {
            repository.getDrinklist(newText)
        }
    }
}