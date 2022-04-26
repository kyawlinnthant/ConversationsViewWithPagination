package klt.mdy.conversationviewwithpagination

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlin.random.Random

@Parcelize
data class MovieItemVo(
    val id: Long = -1L,
    val imageUrl: String? = null,
    val posterUrl: String? = null,
    val originalTitle: String = "",
    val title: String = "",
    val overview: String = "",
    val releasedDate: String = "",
    val popularity: Double = -0.0,
    val averageVote: Double = -0.0,
    val voteCount: Int = -1,
    val viewType: Int = Random.nextInt(from = 1, until = 8)
) : Parcelable
