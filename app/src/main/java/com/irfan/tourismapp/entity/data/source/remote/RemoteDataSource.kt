package com.irfan.tourismapp.entity.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.irfan.tourismapp.entity.data.model.Place
import com.irfan.tourismapp.entity.data.model.TourismResponse
import com.irfan.tourismapp.entity.data.source.remote.network.ApiResponse
import com.irfan.tourismapp.entity.data.source.remote.network.ApiService
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

    fun getAllTourism(): LiveData<ApiResponse<List<Place>>>{
        val resultData = MutableLiveData<ApiResponse<List<Place>>>()

        val client = apiService.getListTourism()
        client.enqueue(object : Callback<TourismResponse> {
            override fun onFailure(call: Call<TourismResponse>, t: Throwable) {
                resultData.value = ApiResponse.Error(t.message.toString())
                Log.e("RemoteDataSource", t.message.toString())
            }

            override fun onResponse(
                call: Call<TourismResponse>,
                response: Response<TourismResponse>
            ) {
                val dataToursim = response.body()?.places
                resultData.value = if (dataToursim != null){
                    ApiResponse.Success(dataToursim)
                }
                else{
                    ApiResponse.Empty
                }
            }
        })
        return resultData
    }
}