package com.irfan.tourismapp.entity.data.source.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.irfan.tourismapp.entity.data.source.local.entity.TourismEntity

@Database(entities = [TourismEntity::class], version = 1, exportSchema = false)
abstract class TourismDatabase: RoomDatabase() {
    abstract fun tourismDao(): TourismDao

    companion object{
        @Volatile
        private var INSTANCE: TourismDatabase? = null

        fun getInstance(context: Context): TourismDatabase {
            INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TourismDatabase::class.java,
                    "Tourism.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
            }
            return INSTANCE!!
        }
    }
}