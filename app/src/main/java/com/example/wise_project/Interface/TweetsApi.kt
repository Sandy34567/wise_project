package com.example.wise_project.Interface

import com.example.wise_project.Model.Drink
import com.example.wise_project.Model.datalist
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CocktailApi {
    @GET("/api/json/v1/1/search.php")
    suspend fun searchCocktails(@Query("s") search: String): Response<datalist>
}