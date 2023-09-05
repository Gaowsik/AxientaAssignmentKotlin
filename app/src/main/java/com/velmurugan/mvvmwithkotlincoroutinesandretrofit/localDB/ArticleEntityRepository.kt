package com.velmurugan.mvvmwithkotlincoroutinesandretrofit.localDB

import com.velmurugan.mvvmwithkotlincoroutinesandretrofit.extention.toArticleEntity
import com.velmurugan.mvvmwithkotlincoroutinesandretrofit.models.Article

class ArticleEntityRepository(private val dao: ArticleDao) {

//    companion object {
//        fun provideArticleEntityRepository(orderDao: ArticleDao): ArticleEntityRepository {
//            return ArticleEntityRepository(dao = orderDao)
//        }
//    }

    suspend fun addListArticles(
        listArticles: List<Article>
    ) {

        val articleEntities = listArticles.map { article ->
            article.toArticleEntity()

        }
        dao.insertArticles(articleEntities)

    }

    suspend fun deleteArticle(articleId: Int): Int = dao.deleteThisArticleFromLocalDb(articleId)

    suspend fun getAllArticles() = dao.getAllArticles()


}