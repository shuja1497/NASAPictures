package com.example.nasapictures.model

sealed class Response

data class Success(val value: ArrayList<Picture>) : Response()

data class Failure(
    val message: String?,
    val throwable: Throwable?
) : Response()

data class Dummy(val value: Picture): Response()