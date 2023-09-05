package com.velmurugan.mvvmwithkotlincoroutinesandretrofit

import com.velmurugan.mvvmwithkotlincoroutinesandretrofit.models.ArticleResponse

class MainRepository constructor(private val retrofitService: RetrofitService) {

/*    suspend fun getAllArticles(query:String,apiKey : String) : NetworkState<ArticleResponse> {
        val response = retrofitService.getAllArticles(query,apiKey )
        return if (response.isSuccessful) {
            val responseBody = response.body()
            if (responseBody != null) {
                NetworkState.Success(responseBody)
            } else {
                NetworkState.Error(response)
            }
        } else {
            NetworkState.Error(response)
        }
    }*/


    suspend fun getAllArticles(query: String,apiKey:String) : ArticleResponse = retrofitService.getAllArticles(query,apiKey)

}