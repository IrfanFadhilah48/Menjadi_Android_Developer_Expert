package com.irfan.tourismapp.entity.data.source.local

import com.irfan.tourismapp.entity.data.source.local.db.TourismDao
import com.irfan.tourismapp.entity.data.source.local.entity.TourismEntity
import io.reactivex.Flowable

class LocalDataSource constructor(private val tourismDao: TourismDao) {
    companion object{
        private var instance: LocalDataSource? = null

        fun getInstance(tourismDao: TourismDao): LocalDataSource =
            instance?: synchronized(this){
                instance?: LocalDataSource(tourismDao)
            }
    }

    fun getAllTourism(): Flowable<List<TourismEntity>> = tourismDao.getAllTourism()
    fun getFavoriteTourism(): Flowable<List<TourismEntity>> = tourismDao.getFavoriteTourism()
    fun insertTourism(tourismList: List<TourismEntity>) = tourismDao.insertTourism(tourismList)
    fun setFavoriteTourism(tourism: TourismEntity, newState: Boolean){
        tourism.isFavorite = newState
        tourismDao.updateFavoriteTourism(tourism)
    }
}