package klt.mdy.conversationviewwithpagination.view.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SenderConversationAudio(
    modifier: Modifier = Modifier,
    duration: String,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                start = 64.dp,
                end = 16.dp,
                top = 16.dp,
                bottom = 16.dp,
            ),
        contentAlignment = Alignment.CenterEnd
    ) {
        Row(
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
                    vertical = 4.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                imageVector = Icons.Default.Call,
                contentDescription = "audio",
                tint = MaterialTheme.colors.surface
            )
            Spacer(modifier = modifier.width(8.dp))
            Text(
                text = duration,
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.surface,
                textAlign = TextAlign.End
            )
        }
    }
}

@Composable
fun ReceiverConversationAudio(
    modifier: Modifier = Modifier,
    duration: String,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                end = 64.dp,
                start = 16.dp,
                top = 16.dp,
                bottom = 16.dp,
            ),
        contentAlignment = Alignment.CenterStart
    ) {

        Row(
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
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = Icons.Default.Call,
                contentDescription = "audio",
                tint = MaterialTheme.colors.surface
            )
            Spacer(modifier = modifier.width(8.dp))
            Text(

                text = duration,
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onSurface,
                textAlign = TextAlign.Start
            )
        }
    }
}

@Composable
@Preview
private fun SenderAudioPreview() {
    Surface {
        SenderConversationAudio(duration = "01:09")
    }
}

@Composable
@Preview
private fun ReceiverAudioPreview() {
    Surface {
        ReceiverConversationAudio(duration = "01:09")
    }
}