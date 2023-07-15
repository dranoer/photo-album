package com.dranoer.photoalbum.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import com.dranoer.photoalbum.R
import com.dranoer.photoalbum.domain.model.PhotoItem
import com.dranoer.photoalbum.ui.theme.PhotoAlbumTheme

@Composable
fun PhotoCard(modifier: Modifier, photo: PhotoItem) {
    Surface(
        modifier = modifier.padding(10.dp),
    ) {
        val featuredString = stringResource(id = R.string.account)
        ConstraintLayout(
            modifier = Modifier
                .clickable(
                    onClick = { }
                )
                .semantics {
                    contentDescription = featuredString
                }
        ) {
            val (image, name) = createRefs()

            AsyncImage(
                model = photo.url,
                contentDescription = "contentDescription",
                placeholder = painterResource(R.drawable.ic_launcher_background),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .aspectRatio(6f / 3f)
                    .constrainAs(image) {
                        centerHorizontallyTo(parent)
                        top.linkTo(parent.top)
                    }
            )
            Text(
                text = photo.title,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 10.dp)
                    .constrainAs(name) {
                        centerHorizontallyTo(parent)
                        top.linkTo(image.bottom)
                    }
            )
        }
    }
}

//region Preview
@Preview(name = "Normal")
@Composable
private fun PhotoCardPreview() {
    PhotoAlbumTheme {
        PhotoCard(
            modifier = Modifier,
            photo = PhotoItem(
                albumId = 1,
                id = 2,
                title = "This is a normal title.",
                url = "",
                thumbnailUrl = ""
            )
        )
    }
}
//endregion