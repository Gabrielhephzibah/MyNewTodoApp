package com.cherish.mynewtodoapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.cherish.mynewtodoapp.BuildConfig
import com.cherish.mynewtodoapp.data.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AppViewModel  @Inject constructor(var repository: AppRepository) : ViewModel(){
    fun getNewByHeadlines() = repository.getNewHeadlines(BuildConfig.API_KEY, "us")
    fun getDataFromDb() = repository.getAllData()

}