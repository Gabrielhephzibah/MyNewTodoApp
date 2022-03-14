package com.cherish.mynewtodoapp.data.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.cherish.mynewtodoapp.data.model.api.GetNewsResponse
import com.cherish.mynewtodoapp.data.model.api.Source
import com.cherish.mynewtodoapp.utils.MyDataConverter
import com.google.gson.annotations.SerializedName
import retrofit2.Converter

@Entity(tableName = "news")
data class NewsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @SerializedName("author")
    val author: String?,
    @SerializedName("content")
    val content: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("publishedAt")
    val publishedAt: String?,
    @SerializedName("source")
    val source: Source?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("urlToImage")
    val urlToImage: String?
)
