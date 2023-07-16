package com.dranoer.photoalbum.ui.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
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
            //region Header Image
            AsyncImage(
                modifier = modifier
                    .constrainAs(image) {
                        top.linkTo(parent.top)
                        centerHorizontallyTo(parent)
                    }
                    .aspectRatio(1f),
                model = url,
                contentDescription = "",
                placeholder = painterResource(id = R.drawable.ic_launcher_background),
                contentScale = ContentScale.Crop,
            ) //endregion
            //region Content
            Spacer(
                modifier = modifier
                    .height(40.dp)
                    .constrainAs(spacer) {
                        bottom.linkTo(image.bottom)
                    }
            )
            Surface(
                modifier = modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .constrainAs(text) {
                        top.linkTo(spacer.top)
                        centerHorizontallyTo(parent)
                    },
                shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp),
            ) {
                //region Title
                Text(
                    modifier = modifier
                        .padding(start = 20.dp, top = 40.dp, end = 20.dp, bottom = 40.dp),
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    color = colorResource(id = R.color.black),
                ) //endregion
            }
            //endregion
        }
    }
}

//region Preview
@Preview(name = "Normal")
@Composable
private fun DetailViewPreview() {
    PhotoAlbumTheme {
        DetailView(modifier = Modifier, title = "title", url = "")
    }
}
//endregion