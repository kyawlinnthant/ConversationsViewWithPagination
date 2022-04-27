package klt.mdy.conversationviewwithpagination.view.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import klt.mdy.conversationviewwithpagination.R

@Composable
fun SenderConversationVideo(
    modifier: Modifier = Modifier,
    videoUrl: String?
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = 16.dp,
                vertical = 2.dp,
            ),
        contentAlignment = Alignment.CenterEnd
    ) {

        Box(
            modifier = modifier
                .fillMaxWidth(fraction = 0.5f)
                .aspectRatio(ratio = 1f),
            contentAlignment = Alignment.CenterEnd
        ) {
            Box(modifier = modifier.matchParentSize(), contentAlignment = Alignment.Center) {
                if (videoUrl.isNullOrEmpty()) {
                    Image(
                        modifier = modifier
                            .matchParentSize()
                            .clip(
                                RoundedCornerShape(
                                    topEnd = 16.dp,
                                    topStart = 16.dp,
                                    bottomStart = 16.dp,
                                )
                            ),
                        painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = "conversation image",
                        contentScale = ContentScale.Crop
                    )
                } else {

                    AsyncImage(
                        modifier = modifier
                            .matchParentSize()
                            .clip(
                                RoundedCornerShape(
                                    topEnd = 16.dp,
                                    topStart = 16.dp,
                                    bottomStart = 16.dp,
                                )
                            ),
                        model = "https://image.tmdb.org/t/p/original$videoUrl",
                        contentDescription = "conversation image",
                        placeholder = painterResource(id = R.drawable.ic_launcher_background),
                        contentScale = ContentScale.Crop
                    )
                    ConversationVideoPlay()
                }
            }

        }
    }
}

@Composable
fun ReceiverConversationVideo(
    modifier: Modifier = Modifier,
    videoUrl: String?
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = 16.dp,
                vertical = 2.dp,
            ),
        contentAlignment = Alignment.CenterStart
    ) {

        Box(
            modifier = modifier
                .fillMaxWidth(fraction = 0.5f)
                .aspectRatio(ratio = 1f),
            contentAlignment = Alignment.CenterStart
        ) {

            Box(modifier = modifier.matchParentSize(), contentAlignment = Alignment.Center) {
                if (videoUrl.isNullOrEmpty()) {
                    Image(
                        modifier = modifier
                            .matchParentSize()
                            .clip(
                                RoundedCornerShape(
                                    topEnd = 16.dp,
                                    bottomEnd = 16.dp,
                                    bottomStart = 16.dp,
                                )
                            ),
                        painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = "conversation image",
                        contentScale = ContentScale.Crop
                    )
                } else {

                    AsyncImage(
                        modifier = modifier
                            .matchParentSize()
                            .clip(
                                RoundedCornerShape(
                                    topEnd = 16.dp,
                                    bottomEnd = 16.dp,
                                    bottomStart = 16.dp,
                                )
                            ),
                        model = "https://image.tmdb.org/t/p/original$videoUrl",
                        contentDescription = "conversation image",
                        placeholder = painterResource(id = R.drawable.ic_launcher_background),
                        contentScale = ContentScale.Crop
                    )

                }
                ConversationVideoPlay()
            }
        }

    }
}


@Composable
@Preview
private fun SenderConversationVideoPreview() {
    Surface {
        SenderConversationVideo(videoUrl = null)
    }
}

@Composable
@Preview
private fun ReceiverConversationVideoPreview() {
    Surface {
        ReceiverConversationVideo(videoUrl = null)
    }
}

@Composable
@Preview
private fun Preview() {
    Surface {
        Column {
            ReceiverConversationVideo(videoUrl = null)
            ReceiverConversationVideo(videoUrl = null)
            SenderConversationVideo(videoUrl = null)
        }
    }
}

@Composable
fun ConversationVideoPlay(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .clip(
                RoundedCornerShape(16.dp)
            )
            .background(
                color = Color.Black.copy(
                    alpha = 0.5f
                )
            )
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Default.PlayArrow,
            contentDescription = "Play",
            tint = Color.White
        )
    }
}

@Composable
@Preview
private fun PlayIconPreview() {
    Surface {
        ConversationVideoPlay()
    }
}