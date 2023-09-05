package com.velmurugan.mvvmwithkotlincoroutinesandretrofit.localDB

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "article")
data class ArticleEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val sourceId : String?,
    val sourceName: String?,
    val title: String?,
    val url: String?,
    val urlToImage: String?
)