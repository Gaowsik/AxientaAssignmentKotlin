package com.velmurugan.mvvmwithkotlincoroutinesandretrofit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.velmurugan.mvvmwithkotlincoroutinesandretrofit.extention.toArticleList
import com.velmurugan.mvvmwithkotlincoroutinesandretrofit.localDB.ArticleEntity
import com.velmurugan.mvvmwithkotlincoroutinesandretrofit.localDB.ArticleEntityRepository
import com.velmurugan.mvvmwithkotlincoroutinesandretrofit.state_models.*
import kotlinx.coroutines.launch

class MainViewModel constructor(
    private val mainRepository: MainRepository,
    private val articleEntityRepository: ArticleEntityRepository
) : ViewModel() {

    var getAllArticlesResponseLiveData: MutableLiveData<Resource<List<ArticleEntity>>> =
        MutableLiveData<Resource<List<ArticleEntity>>>()

    var isDataDeletedLiveData: MutableLiveData<Resource<Boolean>> =
        MutableLiveData<Resource<Boolean>>()

    var isLocalStorageEmpty: MutableLiveData<Resource<Boolean>> =
        MutableLiveData<Resource<Boolean>>()


    fun getAllArticles(query: String, apiKey: String) = viewModelScope.launch {
        getAllArticlesResponseLiveData.setLoading()
        try {
            val pDriverProfileResponse =
                mainRepository.getAllArticles(query, apiKey)
            articleEntityRepository.addListArticles(pDriverProfileResponse.articles)
            val articleDataFromLocal = articleEntityRepository.getAllArticles()
            getAllArticlesResponseLiveData.setSuccess(
                data = articleDataFromLocal,
                message = null
            )
        } catch (e: Exception) {
            getAllArticlesResponseLiveData.setError(
                Resource(
                    data = e?.message,
                    message = "oops something went wrong",
                    state = ResourceState.ERROR
                )
            )
            e.printStackTrace()
            return@launch
        }
    }


    fun fetchDataFromLocal() = viewModelScope.launch {
        getAllArticlesResponseLiveData.setLoading()
        try {
            val articleDataFromLocal = articleEntityRepository.getAllArticles()
            getAllArticlesResponseLiveData.setSuccess(
                data = articleDataFromLocal,
                message = null
            )
        } catch (e: Exception) {
            getAllArticlesResponseLiveData.setError(
                Resource(
                    data = e?.message,
                    message = "oops something went wrong",
                    state = ResourceState.ERROR
                )
            )
            e.printStackTrace()
            return@launch
        }
    }

    fun isLocalStorageEmpty() = viewModelScope.launch {
        isLocalStorageEmpty.setLoading()
        try {
            val articleDataFromLocal = articleEntityRepository.getAllArticles()
            isLocalStorageEmpty.setSuccess(
                data = articleDataFromLocal.toArticleList().isNullOrEmpty(),
                message = null
            )
        } catch (e: Exception) {
            isLocalStorageEmpty.setError(
                Resource(
                    data = e?.message,
                    message = "oops something went wrong",
                    state = ResourceState.ERROR
                )
            )
            e.printStackTrace()
            return@launch
        }


    }


    fun deleteDataFromDB(article: ArticleEntity) = viewModelScope.launch {
        isDataDeletedLiveData.setLoading()
        try {
            articleEntityRepository.deleteArticle(article.id)
            isDataDeletedLiveData.setSuccess(true, "")
        } catch (e: Exception) {
            isDataDeletedLiveData.setError(
                Resource(
                    data = e?.message,
                    message = "oops something went wrong",
                    state = ResourceState.ERROR
                )
            )
            e.printStackTrace()
            return@launch
        }


    }


}