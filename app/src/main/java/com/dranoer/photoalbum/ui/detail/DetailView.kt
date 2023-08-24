package com.dranoer.photoalbum.ui.detail

import android.content.res.Configuration
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import com.dranoer.photoalbum.R
import com.dranoer.photoalbum.ui.theme.PhotoAlbumTheme

@Composable
fun DetailView(modifier: Modifier, title: String, url: String) {
    Surface {
        ConstraintLayout {
            val (image, spacer, text) = createRefs()
            val orientation = LocalConfiguration.current.orientation
            //region Header Image
            AsyncImage(
                model = url,
                contentDescription = "",
                placeholder = painterResource(id = R.drawable.placeholder),
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .constrainAs(image) {
                        top.linkTo(parent.top)
                        centerHorizontallyTo(parent)
                    }
                    .let { if (orientation == Configuration.ORIENTATION_PORTRAIT) it.aspectRatio(1f) else it },
            ) //endregion
            //region Content
            Spacer(
                modifier = modifier
                    .height(dimensionResource(id = R.dimen.size_40))
                    .constrainAs(spacer) { bottom.linkTo(image.bottom) }
            )
            Surface(
                shape = RoundedCornerShape(
                    topStart = dimensionResource(id = R.dimen.size_40),
                    topEnd = dimensionResource(id = R.dimen.size_40)
                ),
                modifier = modifier
                    .fillMaxSize()
                    .constrainAs(text) {
                        top.linkTo(spacer.top)
                        centerHorizontallyTo(parent)
                    },
            ) {
                Column(
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = modifier.padding(all = dimensionResource(id = R.dimen.size_50)),
                ) {
                    Text(
                        text = stringResource(R.string.detail),
                        style = MaterialTheme.typography.displayMedium,
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                        color = colorResource(id = R.color.gray_400),
                    )
                    Text(
                        text = title,
                        style = MaterialTheme.typography.bodyLarge,
                        color = when (isSystemInDarkTheme()) {
                            true -> colorResource(id = R.color.white)
                            false -> colorResource(id = R.color.black)
                        },
                        modifier = modifier.padding(top = 30.dp),
                    )
                }
            }
            //endregion
        }
    }
}

//region Preview
@Preview
@Composable
private fun DetailViewPreview_Normal() {
    PhotoAlbumTheme {
        DetailView(
            title = "DetailView title.",
            url = "url",
            modifier = Modifier,
        )
    }
}

@Preview
@Composable
private fun DetailViewPreview_LongTitle() {
    PhotoAlbumTheme {
        DetailView(
            title = "This is a very long long long long long long long long long long long long long long long DetailView title.",
            url = "url",
            modifier = Modifier,
        )
    }
}
//endregion