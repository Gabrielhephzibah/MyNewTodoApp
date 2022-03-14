package com.cherish.mynewtodoapp.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.cherish.mynewtodoapp.data.model.db.NewsEntity
import com.cherish.mynewtodoapp.utils.MyDataConverter

@Database(entities = [NewsEntity::class], version = 1, exportSchema = false)
@TypeConverters(MyDataConverter::class)
abstract class AppDataBase : RoomDatabase()  {
    abstract fun newsDao() : NewDao
}