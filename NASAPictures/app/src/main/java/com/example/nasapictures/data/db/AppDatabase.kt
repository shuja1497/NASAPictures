package com.example.nasapictures.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.nasapictures.model.Picture

@Database(entities = [Picture::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): PictureDao
}