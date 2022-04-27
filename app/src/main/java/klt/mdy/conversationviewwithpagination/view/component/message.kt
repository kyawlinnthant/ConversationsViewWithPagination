package klt.mdy.conversationviewwithpagination.view.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SenderConversationMessage(
    modifier: Modifier = Modifier,
    text: String,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                start = 64.dp,
                end = 16.dp,
                top = 2.dp,
                bottom = 2.dp,
            ),
        contentAlignment = Alignment.CenterEnd
    ) {
        Text(
            modifier = modifier
                .clip(
                    RoundedCornerShape(
                        topEnd = 16.dp,
                        topStart = 16.dp,
                        bottomStart = 16.dp,
                    )
                )
                .background(color = MaterialTheme.colors.primary)
                .padding(
                    horizontal = 16.dp,
                    vertical = 8.dp
                ),
            text = text,
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.surface,
            textAlign = TextAlign.End
        )
    }
}

@Composable
fun ReceiverConversationMessage(
    modifier: Modifier = Modifier,
    text: String,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                end = 64.dp,
                start = 16.dp,
                top = 2.dp,
                bottom = 2.dp,
            ),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            modifier = modifier
                .clip(
                    RoundedCornerShape(
                        topEnd = 16.dp,
                        bottomEnd = 16.dp,
                        bottomStart = 16.dp,
                    )
                )
                .background(color = MaterialTheme.colors.onBackground.copy(0.3f))
                .padding(
                    horizontal = 16.dp,
                    vertical = 8.dp
                ),
            text = text,
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.onSurface,
            textAlign = TextAlign.Start
        )
    }
}


@Composable
@Preview
private fun SenderConversationMessagePreview() {
    Surface {
        SenderConversationMessage(text = "Hello darkness my old friend!")
    }
}

@Composable
@Preview
private fun ReceiverConversationMessagePreview() {
    Surface {
        ReceiverConversationMessage(text = "Hello darkness my old friend!")
    }
}

@Composable
@Preview
private fun Preview() {
    Surface {
        Column {
            SenderConversationMessage(text = "Hi!")
            ReceiverConversationMessage(text = "hello")
            SenderConversationMessage(text = "A yin lo chit thay lar?")
            SenderConversationImage(imageUrl = null)
            ReceiverConversationMessage(text = "Shin ba thu lel loz?")
            ReceiverConversationVideo(videoUrl = null)
            ReceiverConversationAudio(duration = "1:01")
            SenderConversationMessage(
                text = "Min Chit nay tar a thi thar kyi par kwar. " +
                        "Ma nyar chin can par nat. Min thu ko a yan chit mi nay b ma lar." +
                        " Tha ngel chin yar, ma nyar chin san par nat!!!"
            )
            SenderConversationMessage(text = "Chit mi nay b lar")
            SenderConversationAudio(duration = "0:09")
        }
    }
}