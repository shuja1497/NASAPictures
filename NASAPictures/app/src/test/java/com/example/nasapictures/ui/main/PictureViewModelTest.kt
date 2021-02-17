package com.example.nasapictures.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.nasapictures.model.Failure
import com.example.nasapictures.model.Picture
import com.example.nasapictures.model.Response
import com.example.nasapictures.model.Success
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PictureViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun getPictures() {
    }

    @Test
    fun addPicture_setsNewPicture() {

        val pictureViewModel = PictureViewModel()
        val observer = Observer<Response> { }
        pictureViewModel.pictures.observeForever(observer)

        val dummyPicture = Picture(
            null,
            "2019-12-25",
            "What is this person doing?",
            "https://apod.nasa.gov/apod/image/1912/AnnularEclipse_Pinski_1522.jpg",
            "image",
            "v2",
            "An Annular Solar Eclipse over New Mexico",
            "https://apod.nasa.gov/apod/image/1912/AnnularEclipse_Pinski_960.jpg"
        )
        pictureViewModel.addPicture(arrayListOf(dummyPicture))
        val value = pictureViewModel.pictures.value
        assert(value is Success)

    }

    @Test
    fun addNullPicture_returnsFailure() {

        val pictureViewModel = PictureViewModel()
        val observer = Observer<Response> { }
        pictureViewModel.pictures.observeForever(observer)

        val dummyPicture = arrayListOf<Picture>()
        pictureViewModel.addPicture(dummyPicture)
        val value = pictureViewModel.pictures.value
        Assert.assertNotEquals(value is Success, value)

    }
}