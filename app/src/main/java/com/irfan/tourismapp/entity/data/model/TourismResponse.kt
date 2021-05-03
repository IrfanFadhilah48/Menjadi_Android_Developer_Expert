package com.irfan.tourismapp.entity.data.model
import com.google.gson.annotations.SerializedName

data class TourismResponse(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("error")
    val error: Boolean?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("places")
    val places: List<Place>?
)

data class Place(
    @SerializedName("address")
    val address: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("like")
    val like: Int,
    @SerializedName("longitude")
    val longitude: Double,
    @SerializedName("name")
    val name: String
)

