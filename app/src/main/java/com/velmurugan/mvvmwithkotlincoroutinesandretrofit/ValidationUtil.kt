package com.velmurugan.mvvmwithkotlincoroutinesandretrofit

import com.velmurugan.mvvmwithkotlincoroutinesandretrofit.localDB.ArticleEntity
import com.velmurugan.mvvmwithkotlincoroutinesandretrofit.models.Article

object ValidationUtil {

    fun validateMovie(movie: Movie) : Boolean {
        if (movie.name.isNotEmpty() && movie.category.isNotEmpty()) {
            return true
        }
        return false
    }

    fun validateArticle(article: ArticleEntity?) : Boolean {
        if (article?.urlToImage?.isNotEmpty() == true && article.title?.isNotEmpty() == true) {
            return true
        }
        return false
    }
}