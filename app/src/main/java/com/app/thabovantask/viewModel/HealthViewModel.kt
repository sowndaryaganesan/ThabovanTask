package com.app.thabovantask.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.thabovantask.HealthList
import com.app.thabovantask.apiCall.MainRepository
import retrofit2.Call

class HealthViewModel(private val mainRepository: MainRepository) : ViewModel() {

    //    Declaring the mutable livedata
    val healthList = MutableLiveData<List<HealthList.DataList.Health>>()
    val errorMessage = MutableLiveData<String>()

    fun getHealthList() {
        val response = mainRepository.getHealthList()

        response.enqueue(object : retrofit2.Callback<HealthList> {
            override fun onResponse(
                call: Call<HealthList>,
                response: retrofit2.Response<HealthList>
            ) {
                healthList.postValue(response.body()?.data!!.health)
            }

            override fun onFailure(call: Call<HealthList>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }

}