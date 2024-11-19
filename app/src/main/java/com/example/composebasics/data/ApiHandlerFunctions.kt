package com.example.composebasics.data

import com.google.gson.Gson
import retrofit2.HttpException
import java.util.concurrent.CancellationException

object ApiHandlerFunctions {
    inline fun <T, E> execute(serviceCall: () -> T, transform: (T) -> E): NetworkResult<E> {
        return try {
            Success(transform(serviceCall()))
        } catch (e: HttpException) {
            e.printStackTrace()
            try {
                val errorString = e.response()?.errorBody()?.string()
                if (errorString.isNullOrBlank()) {
                    return Error(
                        error = ApiError(message = (e.message())),
                        exception = e,
                        code = e.code()
                    )
                } else {
                    errorString
                    Error(
                        error = ApiError(message = errorString),
                        exception = e,
                        code = e.code()
                    )
                }
            } catch (e: Exception) {
                Error(error = ApiError(message =e.message), exception = e)
            }
        } catch (e: Exception) {
            if (e is CancellationException) {
                throw e
            }
            e.printStackTrace()
            Error(error = ApiError(message =e.message), exception = e)
        }
    }
}