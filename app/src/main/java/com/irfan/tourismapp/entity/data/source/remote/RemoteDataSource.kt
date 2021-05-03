package com.irfan.tourismapp.entity.data.source.remote

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.irfan.tourismapp.entity.data.model.Place
import com.irfan.tourismapp.entity.data.model.TourismResponse
import com.irfan.tourismapp.entity.data.source.remote.network.ApiResponse
import com.irfan.tourismapp.entity.data.source.remote.network.ApiService
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource constructor(private val apiService: ApiService) {

    companion object{
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(service: ApiService): RemoteDataSource =
            instance?: synchronized(this){
                instance?: RemoteDataSource(service)
            }
    }

    @SuppressLint("CheckResult")
    fun getAllTourism(): Flowable<ApiResponse<List<Place>>>{
        val resultData =
            PublishSubject.create<ApiResponse<List<Place>>>()

        val client = apiService.getListTourism()
        client.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe(
                { response ->
                    val data = response.places
                    if (data.isEmpty()) resultData.onNext(ApiResponse.Empty) else ApiResponse.Success(data)
                },
                { error ->
                    resultData.onNext(ApiResponse.Error(error.message.toString()))
                }
            )
        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }
}