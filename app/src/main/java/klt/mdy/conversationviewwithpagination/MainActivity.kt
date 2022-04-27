package klt.mdy.conversationviewwithpagination

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import klt.mdy.conversationviewwithpagination.ui.theme.ConversationViewWithPaginationTheme
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ConversationViewWithPaginationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ConversationScreen()
                }
            }
        }
    }
}

@Composable
fun ConversationScreen() {
    val vm: MainViewModel = hiltViewModel()
    val state = vm.mainState.value
    val scrollState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    LaunchedEffect(scrollState) {
        vm.loadNextItems()
        if (scrollState.firstVisibleItemIndex == 0) {
            vm.onAction(MainAction.ClickNewMessage)
        }
//        (condition to check if scrolled to end) { vm.loadNextItems() }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomEnd
    ) {

        Column(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                reverseLayout = true,
                state = scrollState,
            ) {
                items(state.oldItems.size) { i ->
                    val item = state.oldItems[i]
                    if (i >= state.oldItems.lastIndex && !state.endOfPaginationReached && !state.isLoading) {
                        vm.loadNextItems()
                    }
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        text = item.originalTitle,
                        fontSize = 20.sp,
                        color = Color.Black
                    )
                }
                item {
                    if (state.isLoading) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }

                if (state.error.isNotEmpty()) {
                    item {

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(text = state.error)
                            Spacer(modifier = Modifier.height(8.dp))
                            OutlinedButton(onClick = {
                                vm.loadNextItems()
                            }) {
                                Text(text = "Try again")
                            }
                        }

                    }
                }

                if (state.endOfPaginationReached) {
                    item {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            text = "End of pagination"
                        )
                    }
                }

            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    value = state.inputText,
                    onValueChange = {
                        vm.onAction(MainAction.ChangeInputText(text = it))
                    },
                    placeholder = {
                        Text(text = "Type message")
                    }
                )
                Button(onClick = {

                    if (scrollState.firstVisibleItemIndex == 0 && !scrollState.isScrollInProgress) {
                        vm.onAction(MainAction.SendMessage)
                    } else {
                        vm.onAction(MainAction.ReceiveMessage)
                    }

                }) {
                    Text(text = "Send")
                }
            }

        }
        // We use our remember composable to get the scroll context
        val scrollContext = rememberScrollContext(scrollState)
        if (scrollContext.isTop) {
            vm.onAction(MainAction.ClickNewMessage)
        }

        if (
            scrollState.firstVisibleItemIndex > 5 &&
            state.newItems.isEmpty()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 60.dp),
                contentAlignment = Alignment.Center
            ) {
                Surface(
                    modifier = Modifier
                        .size(30.dp)
                        .clip(CircleShape)
                        .clickable {
                            scope.launch {
                                scrollState.animateScrollToItem(index = 0)
                            }
                        },
                    elevation = 16.dp,
                    contentColor = Color.White,
                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f),
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = "scroll in top"
                    )
                }
            }
        }

        if (state.newItems.isNotEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        end = 20.dp,
                        bottom = 90.dp
                    ),
                contentAlignment = Alignment.BottomEnd
            ) {
                Surface(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .clickable {
                            vm.onAction(MainAction.ClickNewMessage)
                            scope.launch {
                                scrollState.animateScrollToItem(index = 0)
                            }
                        },
                    elevation = 16.dp,
                    contentColor = Color.White,
                    color = MaterialTheme.colors.primary,
                ) {
                    Text(
                        modifier = Modifier.matchParentSize(),
                        text = state.newItems.size.toString(),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.h4
                    )
                }
            }

        }
    }
}

val LazyListState.isLastItemVisible: Boolean
    get() = layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1

val LazyListState.isFirstItemVisible: Boolean
    get() = firstVisibleItemIndex == 0

data class ScrollContext(
    val isTop: Boolean,
    val isBottom: Boolean,
)

@Composable
fun rememberScrollContext(listState: LazyListState): ScrollContext {
    val scrollContext by remember {
        derivedStateOf {
            ScrollContext(
                isTop = listState.isFirstItemVisible,
                isBottom = listState.isLastItemVisible
            )
        }
    }
    return scrollContext
}
