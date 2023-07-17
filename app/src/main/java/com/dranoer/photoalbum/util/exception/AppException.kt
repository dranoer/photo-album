package com.dranoer.photoalbum.util.exception

/**
 * Encapsulates application-specific exceptions for organized error handling.
 * Handles network-related errors and cases where requested data isn't found.
 */
sealed class AppException(message: String) : Exception(message) {
    data class NetworkException(
        val errorDetails: String = "Network error occurred. Check your connection.",
    ) : AppException(message = errorDetails)

    data class DataNotFoundException(
        val errorDetails: String = "Requested data was not found.",
    ) : AppException(message = errorDetails)
}