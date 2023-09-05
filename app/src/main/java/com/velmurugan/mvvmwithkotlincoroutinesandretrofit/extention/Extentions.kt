package com.velmurugan.mvvmwithkotlincoroutinesandretrofit.extention

import android.content.Context
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import com.velmurugan.mvvmwithkotlincoroutinesandretrofit.localDB.ArticleEntity
import com.velmurugan.mvvmwithkotlincoroutinesandretrofit.models.Article
import com.velmurugan.mvvmwithkotlincoroutinesandretrofit.models.Source

fun Article.toArticleEntity(): ArticleEntity {
    return ArticleEntity(
        author = this.author,
        content = this.content,
        description = this.description,
        publishedAt = this.publishedAt,
        sourceId = this.source.id,
        sourceName = this.source.name,
        title = this.title,
        url = this.url,
        urlToImage = this.urlToImage
    )
}

fun ArticleEntity.toArticle(): Article {
    return Article(
        author = this.author ?: "",
        content = this.content ?: "",
        description = this.description ?: "",
        publishedAt = this.publishedAt ?: "",
        source = Source(this.sourceId ?: "",this.sourceName ?: ""),
        title = this.title ?: "",
        url = this.url ?: "",
        urlToImage = this.urlToImage ?: ""
    )
}

fun List<ArticleEntity>.toArticleList(): List<Article> {

    return map{articleEntity ->
        articleEntity.toArticle()
    }

}


fun Context.showYesNoDialog(
    title: String,
    message: String,
    positiveButtonText: String = "Yes",
    negativeButtonText: String = "No",
    onPositiveButtonClick: () -> Unit,
    onNegativeButtonClick: () -> Unit
) {
    val builder = AlertDialog.Builder(this)
    builder.setTitle(title)
    builder.setMessage(message)
    builder.setPositiveButton(positiveButtonText) { _, _ ->
        onPositiveButtonClick.invoke()
    }
    builder.setNegativeButton(negativeButtonText) { _, _ ->
        onNegativeButtonClick.invoke()
    }
    builder.setCancelable(true)
    builder.create().show()
}

fun View.showSnackbar(
    message: String,
    duration: Int = Snackbar.LENGTH_SHORT,
    actionText: String? = null,
    actionListener: (() -> Unit)? = null
) {
    val snackbar = Snackbar.make(this, message, duration)
    if (actionText != null && actionListener != null) {
        snackbar.setAction(actionText) {
            actionListener.invoke()
        }
    }
    snackbar.show()
}
