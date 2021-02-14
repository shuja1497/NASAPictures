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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PictureViewModel : ViewModel() {

    private val _pictures = MutableLiveData<Response>()
    val pictures: LiveData<Response> = _pictures

    fun getAllPictures() {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val pictures = PictureDataSource.getAllPictures()

                if (pictures != null) {
                    _pictures.postValue(Success(pictures))
                } else {
                    _pictures.postValue(
                        Failure(
                            AppController.instance.getString(R.string.no_pics_found_msg),
                            null
                        )
                    )
                }
            } catch (e: Exception) {
                _pictures.postValue(
                    Failure(
                        AppController.instance.getString(R.string.parsing_error_msg),
                        e
                    )
                )
            }
        }
    }

}