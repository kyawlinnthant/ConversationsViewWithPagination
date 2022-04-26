package klt.mdy.conversationviewwithpagination

interface PaginationProvider<Key,Item> {
    suspend fun loadMore()
    fun reset()
}