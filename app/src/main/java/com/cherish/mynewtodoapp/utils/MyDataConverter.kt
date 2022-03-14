package com.cherish.mynewtodoapp.utils

import androidx.room.TypeConverter
import com.cherish.mynewtodoapp.data.model.api.Article
import com.cherish.mynewtodoapp.data.model.api.Source
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class MyDataConverter {
    @TypeConverter
    fun fromGroupTaskMemberList(value: List<Article>): String {
        val gson = Gson()
        val type = object : TypeToken<List<Article>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toGroupTaskMemberList(value: String): List<Article> {
        val gson = Gson()
        val type = object : TypeToken<List<Article>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromSourceToString(value: Source): String {
        val gson = Gson()
        val type = object : TypeToken<Source>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toStringToSource(value: String): Source {
        val gson = Gson()
        val type = object : TypeToken<Source>() {}.type
        return gson.fromJson(value, type)
    }
}