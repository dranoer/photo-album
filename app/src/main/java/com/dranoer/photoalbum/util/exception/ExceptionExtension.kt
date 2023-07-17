package com.dranoer.photoalbum.util.exception

import retrofit2.HttpException
import java.io.IOException

fun Exception.toAppException(): Exception {
    return when (this) {
        is HttpException -> when (code()) {
            404 -> throw AppException.DataNotFoundException()
            else -> this
        }

        is IOException -> throw AppException.NetworkException()
        else -> this
    }
}