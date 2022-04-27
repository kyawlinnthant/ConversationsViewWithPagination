package klt.mdy.conversationviewwithpagination

data class MainState(
    val isLoading: Boolean = false,
    val oldItems: List<MovieItemVo> = emptyList(),
    val newItems: List<MovieItemVo> = emptyList(),
    val error: String = "",
    val endOfPaginationReached: Boolean = false,
    val page: Int = 1,

    val inputText : String = "",
)
