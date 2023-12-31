package com.dranoer.photoalbum.ui.photo

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import com.dranoer.photoalbum.R
import com.dranoer.photoalbum.ui.model.PhotoUiModel
import com.dranoer.photoalbum.ui.theme.PhotoAlbumTheme

@Composable
fun PhotoView(photo: PhotoUiModel, onPhotoClicked: (String) -> Unit, modifier: Modifier) {
    Surface(
        color = when (isSystemInDarkTheme()) {
            true -> colorResource(id = R.color.gray_400)
            false -> colorResource(id = R.color.white)
        },
        modifier = modifier.padding(dimensionResource(id = R.dimen.size_10)),
    ) {
        ConstraintLayout(
            modifier = Modifier.clickable(onClick = { onPhotoClicked(photo.id.toString()) })
        ) {
            val (image, name) = createRefs()
            //region Photo
            AsyncImage(
                model = photo.url,
                contentDescription = photo.title,
                placeholder = painterResource(R.drawable.placeholder),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.size_10))
                    .aspectRatio(6f / 3f)
                    .fillMaxHeight()
                    .constrainAs(image) {
                        centerHorizontallyTo(parent)
                        top.linkTo(parent.top)
                    },
            ) //endregion
            //region Title
            Text(
                text = photo.title,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                color = when (isSystemInDarkTheme()) {
                    true -> colorResource(id = R.color.white)
                    false -> colorResource(id = R.color.black)
                },
                maxLines = 2,
                minLines = 2,
                modifier = Modifier
                    .padding(
                        start = dimensionResource(id = R.dimen.size_20),
                        top = dimensionResource(id = R.dimen.size_8),
                        end = dimensionResource(id = R.dimen.size_18),
                        bottom = dimensionResource(id = R.dimen.size_20)
                    )
                    .background(
                        color = when (isSystemInDarkTheme()) {
                            true -> colorResource(id = R.color.gray_400)
                            false -> colorResource(id = R.color.white)
                        }
                    )
                    .constrainAs(name) {
                        centerHorizontallyTo(parent)
                        top.linkTo(image.bottom)
                    }
            ) //endregion
        }
    }
}

//region Preview
@Preview
@Composable
private fun PhotoViewPreview_Normal() {
    PhotoAlbumTheme {
        PhotoView(
            photo = PhotoUiModel(
                albumId = 1,
                id = 2,
                title = "PhotoItem title.",
                url = "url",
                thumbnailUrl = "thumbnailUrl"
            ),
            onPhotoClicked = {},
            modifier = Modifier
        )
    }
}

@Preview
@Composable
private fun PhotoViewPreview_LongTitle() {
    PhotoAlbumTheme {
        PhotoView(
            photo = PhotoUiModel(
                albumId = 1,
                id = 2,
                title = "This is a very long long long long long long long long long long long long long long long long long long long long long long long long long long long PhotoItem title.",
                url = "url",
                thumbnailUrl = "thumbnailUrl"
            ),
            onPhotoClicked = {},
            modifier = Modifier
        )
    }
}

@Preview
@Composable
private fun PhotoViewPreview_EmptyTitle() {
    PhotoAlbumTheme {
        PhotoView(
            photo = PhotoUiModel(
                albumId = 1,
                id = 2,
                title = "PhotoItem title",
                url = "url",
                thumbnailUrl = "thumbnailUrl"
            ),
            onPhotoClicked = {},
            modifier = Modifier
        )
    }
}
//endregion