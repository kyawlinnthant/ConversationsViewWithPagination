package klt.mdy.conversationviewwithpagination

import android.util.Log
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val api: ApiService
) : Repository {
    override suspend fun getMovies(
        page: Int,
        loadSize: Int,
    ): RemoteResource<List<MovieItemVo>> {

        val result = safeApiCall {
            api.fetchMovies(
                page = page
            )
        }
        return when (result) {
            is RemoteResource.ErrorEvent -> {
                Log.d("klt.error", result.message ?: "error")
                RemoteResource.ErrorEvent<List<MovieItemVo>>(
                    errorMessage = result.message ?: "error"
                )
            }
            is RemoteResource.LoadingEvent -> {
                Log.d("klt.loading", "loading")
                RemoteResource.LoadingEvent<List<MovieItemVo>>()
            }
            is RemoteResource.SuccessEvent -> {
                Log.d("klt.success", result.data!!.results.size.toString())
                RemoteResource.SuccessEvent<List<MovieItemVo>>(data = result.data.results.map { it.toVo() })
                /*if (startingIndex + loadSize <= result.data.results.size) {
                    RemoteResource.SuccessEvent<List<MovieItemVo>>(data = result.data.results.map { it.toVo() })
                } else {
                    RemoteResource.SuccessEvent(data = emptyList())
                }*/

            }
        }
    }

}