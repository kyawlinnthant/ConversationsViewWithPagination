package klt.mdy.conversationviewwithpagination.data

interface PaginationProvider<Key,Item> {
    suspend fun loadMore()
    fun reset()
}