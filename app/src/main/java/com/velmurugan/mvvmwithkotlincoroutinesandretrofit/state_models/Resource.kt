package com.velmurugan.mvvmwithkotlincoroutinesandretrofit.state_models

/**
 * @Author Shalitha Samarasignghe
 * @create 10/20/2020 10:25 AM
 */

data class Resource<out T> constructor(
    val state: ResourceState,
    val data: T? = null,
    var message: String? = null,
    var responseCode: Int? = null,
)