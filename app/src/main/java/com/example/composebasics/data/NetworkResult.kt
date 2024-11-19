package com.example.composebasics.data

sealed class NetworkResult<T>(open val data: T?) {
    fun <E> modify(data: E): NetworkResult<E> {
        return when (this) {
            is Success -> {
                Success(data)
            }
            is Error -> {
                Error(data, error, code = code, exception = exception)
            }
        }
    }
}

data class Success<T>(override val data: T) : NetworkResult<T>(data)
data class Error<T>(
    override val data: T? = null,
    val error: ApiError? = null,
    val code: Int? = null,
    val exception: Exception
) :
    NetworkResult<T>(data)

data class ApiError(val status: Int? = null, val message: String? = null, val messageShortcode: String? = null, val header: String? = null)
