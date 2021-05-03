package com.irfan.tourismapp.entity.domain.usecase

import androidx.lifecycle.LiveData
import com.irfan.tourismapp.entity.data.Resource
import com.irfan.tourismapp.entity.data.model.Tourism

interface TourismUseCase {
    fun getAllTourism(): LiveData<Resource<List<Tourism>>>
    fun getFavTourism(): LiveData<List<Tourism>>
    fun setFavourite(tourism: Tourism, state: Boolean)
}