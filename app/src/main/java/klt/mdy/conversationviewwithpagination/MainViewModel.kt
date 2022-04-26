package klt.mdy.conversationviewwithpagination

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo : Repository
) : ViewModel(){

    private val _mainState = mutableStateOf(MainState())
    val mainState : State<MainState> get() = _mainState

    private val _mainEvent = MutableSharedFlow<MainEvent>()
    val mainEvent : SharedFlow<MainEvent>get() = _mainEvent


    private val paginationSource = PaginationImpl(
        initialKey = _mainState.value.page,
        onLoadUpdated = {
            _mainState.value = mainState.value.copy(
                isLoading = it
            )
        },
        onRequest = { nextPage ->
           repo.getMovies(
                page = nextPage,
                loadSize = 20
            )
        },
        getNextKey = {
            _mainState.value.page + 1
        },
        onError = {
            _mainState.value = mainState.value.copy(
                error = it
            )
        },
        onSuccess = { items, newKey ->
            Log.d("klt.klt.items",items.toString())
            _mainState.value = mainState.value.copy(
                items = _mainState.value.items + items,
                page = newKey,
                endOfPaginationReached = items.isEmpty()
            )
        }
    )
    fun loadNextItems() {
        viewModelScope.launch {
            paginationSource.loadMore()
        }
    }
    fun onAction(action : MainAction){
        when(action){
            is MainAction.ChangeInputText -> {
                _mainState.value = mainState.value.copy(
                    inputText = action.text
                )
            }
            MainAction.ClearInputText -> {
                _mainState.value = mainState.value.copy(
                    inputText = ""
                )
            }
            is MainAction.SendMessage -> {
                if (_mainState.value.inputText.isNotEmpty()){
                    val newMessage = MovieItemVo(
                        originalTitle = _mainState.value.inputText
                    )
                    val originalList = _mainState.value.items
                    val listToChange = originalList.toMutableList()
                    listToChange.add(index = 0, element = newMessage)
                    _mainState.value = mainState.value.copy(
                        items = listToChange
                    )
                }
            }
        }
    }
}