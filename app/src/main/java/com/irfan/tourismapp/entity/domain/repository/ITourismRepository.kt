package com.irfan.tourismapp.entity.domain.repository

import androidx.lifecycle.LiveData
import com.irfan.tourismapp.entity.data.Resource
import com.irfan.tourismapp.entity.data.model.Tourism

interface ITourismRepository {
    fun getAllTourism(): LiveData<Resource<List<Tourism>>>
    fun getFavoriteTourism(): LiveData<List<Tourism>>
    fun setFavoriteTourism(tourism: Tourism, state: Boolean)
}