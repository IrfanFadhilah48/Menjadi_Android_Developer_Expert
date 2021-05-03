package com.irfan.tourismapp.ui.home

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.irfan.tourismapp.entity.domain.usecase.TourismUseCase

class HomeViewModel(useCase: TourismUseCase) : ViewModel() {

    val tourism =
        LiveDataReactiveStreams.fromPublisher(useCase.getAllTourism())
}