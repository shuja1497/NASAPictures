package com.example.nasapictures

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.nasapictures.data.PictureDataSource
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PictureDataSourceTest {

    @Test
    fun loadFromAsset_correctFileName_returnsPicturesString() {

        val fileName = "obvious_data.json"
        runBlocking {
            val pictures =
                PictureDataSource.loadJSONFromAsset(
                    fileName
                )
            assertNotEquals("", pictures)
        }
    }

    @Test
    fun loadFromAsset_inCorrectFileName_returnsEmptyString() {

        val fileName = "obs_data.json"
        runBlocking {
            val pictures =
                PictureDataSource.loadJSONFromAsset(
                    fileName
                )
            assertEquals("", pictures)
        }
    }

    @Test
    fun getAllPictures_correctFileName_returnsPicturesList() {

        val fileName = "obvious_data.json"
        runBlocking {
            val pictures =
                PictureDataSource.getAllPictures(
                    fileName
                )
            assertNotNull(pictures)
        }
    }

    @Test
    fun getAllPictures_inCorrectFileName_returnsNull() {

        val fileName = "obs_data.json"
        runBlocking {
            val pictures =
                PictureDataSource.getAllPictures(
                    fileName
                )
            assertNull(pictures)
        }
    }
}