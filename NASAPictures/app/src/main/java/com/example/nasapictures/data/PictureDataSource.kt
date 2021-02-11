package com.example.nasapictures.data

import com.example.nasapictures.init.AppController
import com.example.nasapictures.model.Picture
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException
import java.nio.charset.Charset

object PictureDataSource {

    suspend fun getAllPictures(): ArrayList<Picture>? {

        return try {
            val pictures: List<Picture> =
                Gson().fromJson(loadJSONFromAsset(), object : TypeToken<List<Picture>>() {}.type)
            ArrayList(pictures)
        } catch (e: Exception) {
            null
        }
    }

    private fun loadJSONFromAsset(): String = try {
        val inputStream = AppController.instance.assets.open("obvious_data.json")
        val size = inputStream.available()
        val buffer = ByteArray(size)
        val charset: Charset = Charsets.UTF_8
        inputStream.read(buffer)
        inputStream.close()
        String(buffer, charset)
    } catch (ex: IOException) {
        ex.printStackTrace()
        ""
    }

    suspend fun getPictureDetailByUrl() {

    }

}