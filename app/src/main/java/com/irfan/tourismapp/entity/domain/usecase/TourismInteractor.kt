package com.irfan.tourismapp.entity.domain.usecase

import com.irfan.tourismapp.entity.data.Resource
import com.irfan.tourismapp.entity.data.model.Tourism
import com.irfan.tourismapp.entity.domain.repository.ITourismRepository
import io.reactivex.Flowable

class TourismInteractor(private val tourismRepository: ITourismRepository):
    TourismUseCase {
    override fun getAllTourism(): Flowable<Resource<List<Tourism>>> {
        return tourismRepository.getAllTourism()
    }

    override fun getFavTourism(): Flowable<List<Tourism>> {
        return tourismRepository.getFavoriteTourism()
    }

    override fun setFavourite(tourism: Tourism, state: Boolean) {
        return tourismRepository.setFavoriteTourism(tourism, state)
    }

}