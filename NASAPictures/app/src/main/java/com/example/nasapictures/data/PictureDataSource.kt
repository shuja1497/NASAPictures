package com.example.nasapictures.data

import android.content.res.AssetManager.ACCESS_STREAMING
import com.example.nasapictures.init.AppController
import com.example.nasapictures.model.Picture
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.nio.charset.Charset

object PictureDataSource {

    suspend fun getAllPictures(fileName: String): ArrayList<Picture>? {

        var picturesString: String
        return withContext(Dispatchers.IO) {
            try {
                val inputStream = AppController.instance.assets.open(fileName, ACCESS_STREAMING)
                val size = inputStream.available()
                val buffer = ByteArray(size)
                val charset: Charset = Charsets.UTF_8
                inputStream.read(buffer)
                inputStream.close()
                picturesString = String(buffer, charset)

                val pictures: List<Picture> =
                    Gson().fromJson(
                        picturesString,
                        object : TypeToken<List<Picture>>() {}.type
                    )
                ArrayList(pictures)
            } catch (e: Exception) {
                null
            }
        }
    }
}