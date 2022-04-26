package klt.mdy.conversationviewwithpagination

import retrofit2.Response
import java.io.IOException

sealed class RemoteResource<T>(
    val data: T? = null,
    val message: String? = null
) {
    class LoadingEvent<T> : RemoteResource<T>()
    class ErrorEvent<T>(errorMessage: String) : RemoteResource<T>(null, errorMessage)
    class SuccessEvent<T>(data: T) : RemoteResource<T>(data)
}

suspend fun <T> safeApiCall(
    apiCall: suspend () -> Response<T>
): RemoteResource<T> {
    try {
        val response = apiCall()
        if (response.isSuccessful) {
            val body = response.body() ?: return RemoteResource.ErrorEvent("EMPTY BODY")
            return RemoteResource.SuccessEvent(body)
        }
        return RemoteResource.ErrorEvent("ERROR CODE ${response.code()} : ${response.message()}")

    } catch (e: Exception) {
        return RemoteResource.ErrorEvent(e.message ?: e.toString())
    } catch (e: IOException) {
        return RemoteResource.ErrorEvent(e.message ?: e.toString())
    }
}