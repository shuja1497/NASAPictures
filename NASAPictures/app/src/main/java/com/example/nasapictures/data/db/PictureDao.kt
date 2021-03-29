package com.example.nasapictures.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.nasapictures.model.Picture

@Dao
interface PictureDao {

    @Insert
    suspend fun insertPictures(vararg picture: Picture)

    @Query("Select * from Picture")
    suspend fun getAllPictures(): List<Picture>

    @Update
    suspend fun updatePicture(picture: Picture)
}