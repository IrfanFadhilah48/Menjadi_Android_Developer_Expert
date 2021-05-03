package com.irfan.tourismapp.entity.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
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

class TourismRepository constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val executor: AppExecutors
): ITourismRepository {

    companion object{
        @Volatile
        private var instance: TourismRepository? = null

        fun getInstance(
            remoteDataSource: RemoteDataSource,
            localDataSource: LocalDataSource,
            appExecutors: AppExecutors
        ): TourismRepository =
            instance?: synchronized(this){
                instance ?: TourismRepository(remoteDataSource, localDataSource, appExecutors)
            }
    }
    override fun getAllTourism(): LiveData<Resource<List<Tourism>>> =
        object : NetworkBoundResource<List<Tourism>, List<Place>>(executor){
            override fun loadFromDB(): LiveData<List<Tourism>> {
                return Transformations.map(localDataSource.getAllTourism()){
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Tourism>): Boolean =
//                true
                data == null || data.isEmpty()


            override fun createCall(): LiveData<ApiResponse<List<Place>>> =
                remoteDataSource.getAllTourism()

            override fun saveCallResult(data: List<Place>) {
                val tourism = DataMapper.mapResponseToEntities(data)
                executor.diskIO().execute {
                    localDataSource.insertTourism(tourism)
                }
            }

        }.asLiveData()

    override fun getFavoriteTourism(): LiveData<List<Tourism>> {
        return Transformations.map(localDataSource.getFavoriteTourism()){
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