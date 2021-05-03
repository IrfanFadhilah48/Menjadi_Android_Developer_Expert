package com.irfan.tourismapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.irfan.tourismapp.entity.data.model.Tourism

class MainViewModel : ViewModel(){

    private var _dataFavorite = MutableLiveData<Tourism>()
    val dataFavorite
        get() = _dataFavorite

    fun setDataFavorites(data: Tourism){
        _dataFavorite.value = data
    }
}