package com.irfan.tourismapp.entity.data.source.remote.network

import com.irfan.tourismapp.entity.data.model.TourismResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("list")
    fun getListTourism(): Call<TourismResponse>
}