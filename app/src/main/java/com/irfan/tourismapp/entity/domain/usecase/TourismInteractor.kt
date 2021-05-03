package com.irfan.tourismapp.entity.domain.usecase

import androidx.lifecycle.LiveData
import com.irfan.tourismapp.entity.data.Resource
import com.irfan.tourismapp.entity.data.model.Tourism
import com.irfan.tourismapp.entity.domain.repository.ITourismRepository

class TourismInteractor(private val tourismRepository: ITourismRepository):
    TourismUseCase {
    override fun getAllTourism(): LiveData<Resource<List<Tourism>>> {
        return tourismRepository.getAllTourism()
    }

    override fun getFavTourism(): LiveData<List<Tourism>> {
        return tourismRepository.getFavoriteTourism()
    }

    override fun setFavourite(tourism: Tourism, state: Boolean) {
        return tourismRepository.setFavoriteTourism(tourism, state)
    }

}