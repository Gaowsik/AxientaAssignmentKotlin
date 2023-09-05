package com.velmurugan.mvvmwithkotlincoroutinesandretrofit.di

import androidx.room.Room
import com.velmurugan.mvvmwithkotlincoroutinesandretrofit.MainRepository
import com.velmurugan.mvvmwithkotlincoroutinesandretrofit.MainViewModel
import com.velmurugan.mvvmwithkotlincoroutinesandretrofit.RetrofitService
import com.velmurugan.mvvmwithkotlincoroutinesandretrofit.localDB.ArticleDao
import com.velmurugan.mvvmwithkotlincoroutinesandretrofit.localDB.ArticleDataBase
import com.velmurugan.mvvmwithkotlincoroutinesandretrofit.localDB.ArticleEntityRepository
import com.velmurugan.mvvmwithkotlincoroutinesandretrofit.retrofit.createAppApiClient
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module



val retrofitNetworkModule = module {

    val mainApiClient =
        createAppApiClient("https://newsapi.org/v2/")

    single { mainApiClient.create(RetrofitService::class.java) }

}


val mRepositoryModule = module {


    single {
        MainRepository(retrofitService = get())
    }

    single { ArticleEntityRepository(dao = get()) }


}

val viewModelModule = module {

    viewModel {
        MainViewModel(
            mainRepository = get(),
            articleEntityRepository = get()
        )
    }


}

val roomDatabaseModule = module {
    val APP_LOCAL_DB_NAME = "articles_dp"

    single {
        Room.databaseBuilder(
            androidApplication(),
            ArticleDataBase::class.java,
            APP_LOCAL_DB_NAME
        ).build()
    }

    single { get<ArticleDataBase>().provideArticleEntityDao() }



}