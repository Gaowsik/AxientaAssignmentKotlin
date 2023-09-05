package com.velmurugan.mvvmwithkotlincoroutinesandretrofit.localDB

import androidx.room.*
import com.velmurugan.mvvmwithkotlincoroutinesandretrofit.models.Article

@Dao
interface ArticleDao {
    @Query("SELECT * FROM article")
    suspend fun getAllArticles(): List<ArticleEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(articleEntity: ArticleEntity) : Long

    // delete  this taxi hire  from local db
    @Query("DELETE FROM `article` where id=:id")
    suspend fun deleteThisArticleFromLocalDb(id: Int): Int

    @Transaction
    suspend fun insertArticles(articles: List<ArticleEntity>) {
        articles.forEach { article ->
            insertArticle(article)
        }
    }
}