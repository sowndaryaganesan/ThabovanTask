package com.app.thabovantask.apiCall

class MainRepository constructor(private val retrofitService: RetrofitService) {

    fun getHealthList() = retrofitService.getHealthList()

}