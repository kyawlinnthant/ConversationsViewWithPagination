package klt.mdy.conversationviewwithpagination

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/upcoming")
    suspend fun fetchMovies(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("page") page: Int = 1,
        @Query("loadSize")loadSize : Int = 20,
    ): Response<MovieDTO>

}