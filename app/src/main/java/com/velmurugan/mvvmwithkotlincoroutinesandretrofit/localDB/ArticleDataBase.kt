package com.velmurugan.mvvmwithkotlincoroutinesandretrofit.localDB

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ArticleEntity::class], version = 2)
abstract class ArticleDataBase : RoomDatabase(){


    abstract fun provideArticleEntityDao(): ArticleDao
}