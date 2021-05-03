package com.irfan.tourismapp.entity.di

import android.content.Context
import com.irfan.tourismapp.entity.data.model.TourismResponse
import com.irfan.tourismapp.entity.data.repository.TourismRepository
import com.irfan.tourismapp.entity.data.source.local.LocalDataSource
import com.irfan.tourismapp.entity.data.source.local.db.TourismDatabase
import com.irfan.tourismapp.entity.data.source.remote.RemoteDataSource
import com.irfan.tourismapp.entity.data.source.remote.network.ApiConfig
import com.irfan.tourismapp.entity.domain.repository.ITourismRepository
import com.irfan.tourismapp.entity.domain.usecase.TourismInteractor
import com.irfan.tourismapp.entity.domain.usecase.TourismUseCase
import com.irfan.tourismapp.utils.AppExecutors

object Injection {
    private fun provideRepository(context: Context): ITourismRepository{
        val database = TourismDatabase.getInstance(context)

        val localDataSource = LocalDataSource.getInstance(database.tourismDao())
        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.provideApiService())
        val appExecutors = AppExecutors()

        return TourismRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }

    fun provideTourismUseCase(context: Context): TourismUseCase{
        val repository = provideRepository(context)
        return TourismInteractor(repository)
    }
}