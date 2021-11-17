package com.example.stars.model

import com.google.gson.annotations.SerializedName

data class Sign(
    @SerializedName("current_Date")
    val current_date: String,
    @SerializedName("compatibility")
    val compatibility: String,
    @SerializedName("lucky_time")
    val lucky_time: String,
    @SerializedName("lucky_number")
    val lucky_number: String,
    @SerializedName("color")
    val color: String,
    @SerializedName("date_range")
    val date_range: String,
    @SerializedName("mood")
    val mood: String,
    @SerializedName("description")
    val description: String
)