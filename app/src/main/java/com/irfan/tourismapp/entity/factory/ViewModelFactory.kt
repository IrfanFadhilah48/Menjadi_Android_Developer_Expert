package com.irfan.tourismapp.entity.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.irfan.tourismapp.entity.di.Injection
import com.irfan.tourismapp.entity.domain.usecase.TourismUseCase
import com.irfan.tourismapp.ui.MainViewModel
import com.irfan.tourismapp.ui.detail.DetailTourismViewModel
import com.irfan.tourismapp.ui.favorite.FavoriteViewModel
import com.irfan.tourismapp.ui.home.HomeViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory constructor(
    private val tourismUseCase: TourismUseCase
) : ViewModelProvider.Factory{

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this){
                instance ?: ViewModelFactory(
                    Injection.provideTourismUseCase(context)
                )
            }

    }
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) ->{
                HomeViewModel(tourismUseCase) as T
            }
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) ->{
                FavoriteViewModel(tourismUseCase) as T
            }
            modelClass.isAssignableFrom(DetailTourismViewModel::class.java) ->{
                DetailTourismViewModel(tourismUseCase) as T
            }
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel() as T
            }
            else -> throw Throwable("Unknown ViewModel Class : ${modelClass.name}")
        }
    }

}