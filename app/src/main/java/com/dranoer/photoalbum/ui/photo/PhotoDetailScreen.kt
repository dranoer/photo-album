package com.dranoer.photoalbum.ui.photo

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.dranoer.photoalbum.R
import com.dranoer.photoalbum.domain.model.PhotoItem
import com.dranoer.photoalbum.ui.component.DetailView
import com.dranoer.photoalbum.ui.theme.PhotoAlbumTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhotoDetailScreen(
    photo: PhotoItem,
    backPress: () -> Unit,
) {
    PhotoAlbumTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "Photo detail") },
                    navigationIcon = {
                        IconButton(onClick = { backPress() }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = stringResource(id = R.string.photos),
                                tint = colorResource(id = R.color.black)
                            )
                        }
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = colorResource(id = R.color.gray_100))
                )
            },
            containerColor = colorResource(id = R.color.gray_100),
            content = { padding ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues = padding),
                ) {
                    DetailView(
                        modifier = Modifier.wrapContentWidth(),
                        title = photo.title,
                        url = photo.url
                    )
                }
            }
        )
    }
}

//region Preview
@Preview(name = "Normal")
@Composable
private fun DetailScreenPreview() {
    PhotoAlbumTheme {
        PhotoDetailScreen(
            backPress = {},
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