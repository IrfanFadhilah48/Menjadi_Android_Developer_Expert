package com.irfan.tourismapp.entity.data.source.local.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.irfan.tourismapp.entity.data.source.local.entity.TourismEntity

@Dao
interface TourismDao {

    @Query("SELECT * FROM tourism")
    fun getAllTourism(): LiveData<List<TourismEntity>>

    @Query("SELECT * FROM tourism WHERE isFavorite = 1")
    fun getFavoriteTourism(): LiveData<List<TourismEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTourism(tourism: List<TourismEntity>)

    @Update
    fun updateFavoriteTourism(tourism: TourismEntity)
}