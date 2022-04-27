package klt.mdy.conversationviewwithpagination.view.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import klt.mdy.conversationviewwithpagination.R

@Composable
fun SenderConversationImage(
    modifier: Modifier = Modifier,
    imageUrl: String?
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
        ) {
            if (imageUrl.isNullOrEmpty()) {
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
                    model = "https://image.tmdb.org/t/p/original$imageUrl",
                    contentDescription = "conversation image",
                    placeholder = painterResource(id = R.drawable.ic_launcher_background),
                    contentScale = ContentScale.Crop
                )

            }
        }
    }
}

@Composable
fun ReceiverConversationImage(
    modifier: Modifier = Modifier,
    imageUrl: String?
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
            if (imageUrl.isNullOrEmpty()) {
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
                    model = "https://image.tmdb.org/t/p/original$imageUrl",
                    contentDescription = "conversation image",
                    placeholder = painterResource(id = R.drawable.ic_launcher_background),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}


@Composable
@Preview
private fun SenderConversationImagePreview() {
    Surface {
        SenderConversationImage(imageUrl = null)
    }
}

@Composable
@Preview
private fun ReceiverConversationImagePreview() {
    Surface {
        ReceiverConversationImage(imageUrl = null)
    }
}

@Composable
@Preview
private fun Preview() {
    Surface {
        Column {
            ReceiverConversationImage(imageUrl = null)
            ReceiverConversationImage(imageUrl = null)
            SenderConversationImage(imageUrl = null)
        }
    }
}