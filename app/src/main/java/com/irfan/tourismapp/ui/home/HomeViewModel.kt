package com.irfan.tourismapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.irfan.tourismapp.entity.domain.usecase.TourismUseCase

class HomeViewModel(useCase: TourismUseCase) : ViewModel() {

    val tourism = useCase.getAllTourism()
}