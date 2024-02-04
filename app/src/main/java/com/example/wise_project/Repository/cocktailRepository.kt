package com.example.wise_project.Repository

import com.example.wise_project.Interface.CocktailApi
import com.example.wise_project.Model.Drink
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class cocktailRepository @Inject constructor(private val cocktailApi: CocktailApi){

    private val _drink = MutableStateFlow<List<Drink>>(emptyList())
    val drinkList: StateFlow<List<Drink>>
        get() = _drink

    suspend fun getDrinklist(search: String) {
        val response = cocktailApi.searchCocktails(search)
        if (response.isSuccessful && response.body() != null) {
            _drink.emit(response.body()!!.drinks)
        }
    }
}