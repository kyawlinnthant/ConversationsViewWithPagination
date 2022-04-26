package klt.mdy.conversationviewwithpagination

interface Repository {
    suspend fun getMovies(
        page: Int,
        loadSize: Int
    ): RemoteResource<List<MovieItemVo>>
}