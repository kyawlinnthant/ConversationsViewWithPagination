package klt.mdy.conversationviewwithpagination

import klt.mdy.conversationviewwithpagination.view.MovieItemVo

data class MovieDTO(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)

data class Movie(
    val id: Long,
    val backdrop_path: String?,
    val poster_path: String?,
    val original_title: String,
    val title: String,
    val overview: String,
    val release_date: String,
    val popularity: Double,
    val vote_average: Double,
    val vote_count: Int,
    val adult: Boolean,
    val genre_ids: List<Int>,
    val original_language: String,
    val video: Boolean
) {
    fun toVo(): MovieItemVo {
        return MovieItemVo(
            id = id,
            imageUrl = backdrop_path,
            posterUrl = poster_path,
            originalTitle = original_title,
            title = title,
            overview = overview,
            releasedDate = release_date,
            popularity = popularity,
            averageVote = vote_average,
            voteCount = vote_count
        )
    }
}
