package klt.mdy.conversationviewwithpagination

data class MainState(
    val isLoading: Boolean = false,
    val items: List<MovieItemVo> = emptyList(),
    val error: String? = null,
    val endOfPaginationReached: Boolean = false,
    val page: Int = 1,

    val inputText : String = "",
)
