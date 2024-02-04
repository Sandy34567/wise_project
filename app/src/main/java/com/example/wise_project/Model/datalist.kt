package com.example.wise_project.Model

import com.google.gson.annotations.SerializedName

data class datalist(
    @SerializedName("drinks")
    val drinks: List<Drink>
)