package com.velmurugan.mvvmwithkotlincoroutinesandretrofit.state_models

/**
 * @Author Shalitha Samarasignghe
 * @create 10/20/2020 10:38 AM
 */

sealed class ResourceState {
    object LOADING : ResourceState()
    object SUCCESS : ResourceState()
    object ERROR : ResourceState()
}
