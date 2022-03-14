package com.cherish.mynewtodoapp.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cherish.mynewtodoapp.data.model.api.Article
import com.cherish.mynewtodoapp.data.model.db.NewsEntity
import dagger.internal.DaggerGenerated

@Dao
interface NewDao {
    @Query("SELECT * from news")
    suspend fun getAllNews() : List<NewsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAllNews(newsData: List<NewsEntity>)

}