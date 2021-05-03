package com.irfan.tourismapp.utils

import com.irfan.tourismapp.entity.data.model.Place
import com.irfan.tourismapp.entity.data.model.Tourism
import com.irfan.tourismapp.entity.data.model.TourismResponse
import com.irfan.tourismapp.entity.data.source.local.entity.TourismEntity

object DataMapper {

    fun mapEntitiesToDomain(input: List<TourismEntity>): List<Tourism> =
         input.map {
            Tourism(
                tourismId = it.tourismId,
                description = it.description,
                name = it.name,
                address = it.address,
                latitude = it.latitude,
                longitude = it.longitude,
                like = it.like,
                image = it.image,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntities(input: Tourism) =
        TourismEntity(
            tourismId = input.tourismId,
            description = input.description,
            name = input.name,
            address = input.address,
            latitude = input.latitude,
            longitude = input.longitude,
            like = input.like,
            image = input.image,
            isFavorite = input.isFavorite
        )

    fun mapResponseToEntities(input: List<Place>): List<TourismEntity>{
        val tourismList = ArrayList<TourismEntity>()
        input.map {
            val tourism = TourismEntity(
                tourismId = it.id.toString(),
                description = it.description,
                name = it.name,
                address = it.address,
                latitude = it.latitude,
                longitude = it.longitude,
                like = it.like,
                image = it.image,
                isFavorite = false
            )
            tourismList.add(tourism)
        }
        return tourismList
    }
}