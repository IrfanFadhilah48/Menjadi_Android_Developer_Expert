package com.irfan.tourismapp.entity.data.source.local.db

import androidx.room.*
import com.irfan.tourismapp.entity.data.source.local.entity.TourismEntity
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface TourismDao {

    @Query("SELECT * FROM tourism")
    fun getAllTourism(): Flowable<List<TourismEntity>>

    @Query("SELECT * FROM tourism WHERE isFavorite = 1")
    fun getFavoriteTourism(): Flowable<List<TourismEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTourism(tourism: List<TourismEntity>): Completable

    @Update
    fun updateFavoriteTourism(tourism: TourismEntity)
}