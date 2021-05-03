package com.irfan.tourismapp.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.irfan.tourismapp.entity.domain.usecase.TourismUseCase

class FavoriteViewModel(useCase: TourismUseCase) : ViewModel() {

    val favoriteTourism = LiveDataReactiveStreams.fromPublisher(useCase.getFavTourism())
}