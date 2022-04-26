package klt.mdy.conversationviewwithpagination

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import klt.mdy.conversationviewwithpagination.ui.theme.ConversationViewWithPaginationTheme

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

//    LaunchedEffect(key1 = true){
//        vm.loadNextItems()
//    }

    val scrollState = rememberLazyListState()
    LaunchedEffect(scrollState){
        vm.loadNextItems()
//        (condition to check if scrolled to end) { vm.loadNextItems() }
    }
    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            reverseLayout = true,
            state = scrollState,
        ) {
            items(state.items.size) { i ->
                val item = state.items[i]
                if (i >= state.items.lastIndex && !state.endOfPaginationReached && !state.isLoading) {
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
        }

        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
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
                vm.onAction(MainAction.SendMessage)
            }) {
                Text(text = "Send")
            }
        }
    }


}