package com.velmurugan.mvvmwithkotlincoroutinesandretrofit

import com.velmurugan.mvvmwithkotlincoroutinesandretrofit.models.ArticleResponse
import com.velmurugan.mvvmwithkotlincoroutinesandretrofit.retrofit.createAppApiClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

/*    @GET("everything")
    suspend fun getAllArticles(@Query("q") query: String,
                               @Query("apiKey") apiKey: String,): Response<ArticleResponse>*/

    @GET("everything")
    suspend fun getAllArticles(@Query("q") query: String,
                               @Query("apiKey") apiKey: String,): ArticleResponse

    companion object {
        var retrofitService: RetrofitService? = null
//        fun getInstance(): RetrofitService {
//            if (retrofitService == null) {
//                val retrofit = Retrofit.Builder()
//                    .baseUrl("https://howtodoandroid.com/")
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build()
//                retrofitService = retrofit.create(RetrofitService::class.java)
//            }
//            return retrofitService!!
//        }


/*        val mainApiClient =
            createAppApiClient("https://howtodoandroid.com/")

        fun getInstance(): RetrofitService {
            return mainApiClient.create(RetrofitService::class.java)

        }*/

    }
}