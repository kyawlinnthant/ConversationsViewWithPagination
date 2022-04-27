package klt.mdy.conversationviewwithpagination.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import klt.mdy.conversationviewwithpagination.view.component.*

@Composable
fun ConversationItemView(
    item: MovieItemVo
) {
    when (item.viewType) {
        1 -> {
            SenderConversationMessage(text = item.originalTitle)
        }
        2 -> {
            ReceiverConversationMessage(text = item.originalTitle)
        }
        3 -> {
            SenderConversationAudio(duration = item.popularity.toString())
        }
        4 -> {
            ReceiverConversationAudio(duration = item.popularity.toString())
        }
        5 -> {
            SenderConversationImage(imageUrl = item.posterUrl)
        }
        6 -> {
            ReceiverConversationImage(imageUrl = item.posterUrl)
        }
        7 -> {
            SenderConversationVideo(videoUrl = item.imageUrl)
        }
        8 -> {
            ReceiverConversationVideo(videoUrl = item.imageUrl)
        }
        9->{
            SenderConversationMessage(text = item.overview)
        }
        10->{
            ReceiverConversationMessage(text = item.overview)
        }
    }
}