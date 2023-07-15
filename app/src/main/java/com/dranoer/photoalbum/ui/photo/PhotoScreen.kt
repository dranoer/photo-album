package com.dranoer.photoalbum.ui.photo

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dranoer.photoalbum.R
import com.dranoer.photoalbum.ui.component.AppBar
import com.dranoer.photoalbum.ui.component.PhotoCard
import com.dranoer.photoalbum.ui.theme.PhotoAlbumTheme

@Composable
fun PhotoScreen(
    albumId: String,
    viewModel: PhotoViewModel = hiltViewModel(),
    backPress: () -> Unit,
) {

    LaunchedEffect(key1 = albumId) {
        viewModel.fetchPhotos(albumId = albumId.toInt())
    }

    PhotoAlbumTheme {
        val state = viewModel.photoState.collectAsState().value

        Scaffold(
            topBar = { AppBar(title = stringResource(id = R.string.photos)) },
            containerColor = MaterialTheme.colorScheme.secondary,
            content = { padding ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                ) {
                    //region UI State
                    when (state) {
                        //region Loading
                        is PhotoUiState.Loading -> {
                            if (!state.isRefreshing) CircularProgressIndicator()
                        }
                        //endregion
                        //region Success
                        is PhotoUiState.Success -> {
                            LazyColumn(
                                modifier = Modifier.padding(start = 18.dp, end = 18.dp)
                            ) {
                                items(state.data) { photo ->
                                    //region Card
                                    PhotoCard(
                                        modifier = Modifier.wrapContentWidth(),
                                        title = photo.title
                                    )
                                    //endregion
                                    //region Vertical Space
                                    Spacer(modifier = Modifier.height(10.dp))
                                    //endregion
                                }
                            }
                        }
                        //endregion
                        //region Error
                        is PhotoUiState.Error -> {
                            if (!state.isRefreshing) Text(text = "Oops! there is something wrong..")
                        }
                        //endregion
                    }
                    //endregion
                }
            }
        )
    }
}

//region Preview
@Preview(name = "PhotoScreen")
@Composable
private fun PhotoScreenPreview() {
    PhotoAlbumTheme {
        PhotoScreen(albumId = "1", backPress = {})
    }
}
//endregion