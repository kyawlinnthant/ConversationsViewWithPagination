package klt.mdy.conversationviewwithpagination.data

import klt.mdy.conversationviewwithpagination.view.MovieItemVo
import klt.mdy.conversationviewwithpagination.RemoteResource

interface Repository {
    suspend fun getMovies(
        page: Int,
        loadSize: Int
    ): RemoteResource<List<MovieItemVo>>
}