package com.cherish.mynewtodoapp.data.repository

import com.cherish.mynewtodoapp.data.local.db.NewDao
import com.cherish.mynewtodoapp.data.model.api.GetNewsResponse
import com.cherish.mynewtodoapp.data.model.db.NewsEntity
import com.cherish.mynewtodoapp.data.remote.ApiService
import com.cherish.mynewtodoapp.data.remote.ResultManager
import dagger.Component
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import okhttp3.Dispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepository @Inject constructor(private val apiService: ApiService, private val dao: NewDao) {
    fun  getNewHeadlines(apiKey:String, country : String) =
        flow {
           val response = apiService.getNewByHeadLines(apiKey,country)
               emit(ResultManager.Success(response))
               emit(ResultManager.Loading(false))

            response.articles?.let { dao.saveAllNews(it) }
        }.flowOn(Dispatchers.IO)
            .catch { emit(ResultManager.Failure(it)) }
            .onStart { emit(ResultManager.Loading(true)) }
            .onCompletion { emit(ResultManager.Loading(false)) }


    fun getAllData() = flow {
        val response = dao.getAllNews()
        emit(ResultManager.Success(response))
        emit(ResultManager.Loading(false))
    }.flowOn(Dispatchers.IO)
        .catch { emit(ResultManager.Failure(it)) }
        .onStart { emit(ResultManager.Loading(true)) }
        .onCompletion { emit(ResultManager.Loading(false)) }


}

