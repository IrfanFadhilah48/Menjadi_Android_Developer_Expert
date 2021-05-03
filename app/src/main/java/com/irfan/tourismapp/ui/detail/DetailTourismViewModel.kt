package com.irfan.tourismapp.ui.detail

import androidx.lifecycle.ViewModel
import com.irfan.tourismapp.entity.data.model.Tourism
import com.irfan.tourismapp.entity.domain.usecase.TourismUseCase

class DetailTourismViewModel(private val useCase: TourismUseCase) : ViewModel() {
    // TODO: Implement the ViewModel
    fun setFavoriteTourism(tourism: Tourism, status: Boolean) =
        useCase.setFavourite(tourism, status)
}