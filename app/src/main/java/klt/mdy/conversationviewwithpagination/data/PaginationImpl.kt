package klt.mdy.conversationviewwithpagination.data

import android.util.Log
import klt.mdy.conversationviewwithpagination.RemoteResource

class PaginationImpl<Key,Item>(
    private val initialKey : Key,
    private inline val onLoadUpdated : (Boolean) -> Unit,
    private inline val onRequest: suspend (nextKey: Key) -> RemoteResource<List<Item>>,
    private inline val getNextKey: suspend (List<Item>) -> Key,
    private inline val onError: suspend (String) -> Unit,
    private inline val onSuccess: suspend (items: List<Item>, newKey: Key) -> Unit
) : PaginationProvider<Key, Item> {

    private var currentKey = initialKey
    private var isMakingRequest = false

    override suspend fun loadMore() {
        if(isMakingRequest) {
            return
        }
        isMakingRequest = true
        onLoadUpdated(true)
        onError("")
        val result = onRequest(currentKey)
        isMakingRequest = false
        when(result){
            is RemoteResource.ErrorEvent -> {
                Log.d("klt.klt.error",result.message?: "error")
                onError(result.message?: "error")
                onLoadUpdated(false)
            }
            is RemoteResource.LoadingEvent -> {
                Log.d("klt.klt.loading",result.message?: "loading")
                onLoadUpdated(true)
            }
            is RemoteResource.SuccessEvent -> {
                Log.d("klt.klt.success",result.data!!.toString())
                currentKey = getNextKey(result.data!!)
                onSuccess(result.data, currentKey)
                onLoadUpdated(false)
            }
        }
    }

    override fun reset() {
        currentKey = initialKey
    }
}