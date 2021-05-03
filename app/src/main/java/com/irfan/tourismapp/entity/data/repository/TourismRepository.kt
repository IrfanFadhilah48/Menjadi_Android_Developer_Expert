package com.irfan.tourismapp.entity.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.map
import com.irfan.tourismapp.entity.data.NetworkBoundResource
import com.irfan.tourismapp.entity.data.Resource
import com.irfan.tourismapp.entity.data.model.Place
import com.irfan.tourismapp.entity.data.model.Tourism
import com.irfan.tourismapp.entity.data.source.local.LocalDataSource
import com.irfan.tourismapp.entity.data.source.remote.network.ApiResponse
import com.irfan.tourismapp.entity.data.source.remote.RemoteDataSource
import com.irfan.tourismapp.entity.domain.repository.ITourismRepository
import com.irfan.tourismapp.utils.AppExecutors
import com.irfan.tourismapp.utils.DataMapper
import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TourismRepository constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val executor: AppExecutors
) : ITourismRepository {

    companion object {
        @Volatile
        private var instance: TourismRepository? = null

        fun getInstance(
            remoteDataSource: RemoteDataSource,
            localDataSource: LocalDataSource,
            appExecutors: AppExecutors
        ): TourismRepository =
            instance ?: synchronized(this) {
                instance ?: TourismRepository(remoteDataSource, localDataSource, appExecutors)
            }
    }

    override fun getAllTourism(): Flowable<Resource<List<Tourism>>> =
        object : NetworkBoundResource<List<Tourism>, List<Place>>() {
            override fun loadFromDB(): Flowable<List<Tourism>> {
                return localDataSource.getAllTourism().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Tourism>): Boolean =
                data == null || data.isEmpty()
//                true


            override fun createCall(): Flowable<ApiResponse<List<Place>>> =
                remoteDataSource.getAllTourism()

            override fun saveCallResult(data: List<Place>) {
                val tourismList = DataMapper.mapResponseToEntities(data)
                localDataSource.insertTourism(tourismList)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            }
        }.asFlowable()

    override fun getFavoriteTourism(): Flowable<List<Tourism>> {
        return localDataSource.getFavoriteTourism().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteTourism(tourism: Tourism, state: Boolean) {
        val tourismEntity = DataMapper.mapDomainToEntities(tourism)
        executor.diskIO().execute {
            localDataSource.setFavoriteTourism(tourismEntity, state)
        }
    }
}