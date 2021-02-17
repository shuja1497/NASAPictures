package com.example.nasapictures.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nasapictures.R
import com.example.nasapictures.data.PictureDataSource
import com.example.nasapictures.init.AppController
import com.example.nasapictures.model.Failure
import com.example.nasapictures.model.Response
import com.example.nasapictures.model.Success
import kotlinx.coroutines.launch

class PictureViewModel : ViewModel() {

    private val _pictures = MutableLiveData<Response>()
    val pictures: LiveData<Response> = _pictures
    private val fileName = "obvious_data.json"

    fun getAllPictures() {

        viewModelScope.launch {
            try {
                val pictures = try {
                    PictureDataSource.getAllPictures(fileName)
                } catch (e: java.lang.Exception) {
                    null
                }

                if (pictures != null) {
                    _pictures.value = Success(pictures)
                } else {
                    _pictures.value =
                        Failure(AppController.instance.getString(R.string.no_pics_found_msg), null)
                }
            } catch (e: Exception) {
                _pictures.value =
                    Failure(AppController.instance.getString(R.string.parsing_error_msg), e)

            }
        }
    }

}