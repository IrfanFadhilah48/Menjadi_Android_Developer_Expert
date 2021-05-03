package com.irfan.tourismapp.entity.domain.usecase

import com.irfan.tourismapp.entity.data.Resource
import com.irfan.tourismapp.entity.data.model.Tourism
import io.reactivex.Flowable

interface TourismUseCase {
    fun getAllTourism(): Flowable<Resource<List<Tourism>>>
    fun getFavTourism(): Flowable<List<Tourism>>
    fun setFavourite(tourism: Tourism, state: Boolean)
}