package com.example.nasapictures.ui.grid

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.nasapictures.R
import com.example.nasapictures.data.PictureDataSource
import com.example.nasapictures.model.Failure
import com.example.nasapictures.model.Response
import com.example.nasapictures.model.Success
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GridViewModel(application: Application) : AndroidViewModel(application) {

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
                            getApplication<Application>().applicationContext.getString(R.string.no_pics_found_msg),
                            null
                        )
                    )
                }
            } catch (e: Exception) {
                _pictures.postValue(
                    Failure(
                        getApplication<Application>().applicationContext.getString(R.string.parsing_error_msg),
                        e
                    )
                )
            }
        }
    }

}