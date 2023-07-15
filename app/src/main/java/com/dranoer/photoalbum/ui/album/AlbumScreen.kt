package com.dranoer.photoalbum.ui.album

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dranoer.photoalbum.R
import com.dranoer.photoalbum.ui.component.AlbumCard
import com.dranoer.photoalbum.ui.component.AppBar
import com.dranoer.photoalbum.ui.theme.PhotoAlbumTheme

@Composable
fun AlbumScreen(
    viewModel: AlbumViewModel = hiltViewModel(),
    navigateToPhoto: (String) -> Unit,
) {
    val state = viewModel.albumState.collectAsState().value

    PhotoAlbumTheme {
        Scaffold(
            topBar = { AppBar(title = stringResource(id = R.string.albums)) },
            containerColor = MaterialTheme.colorScheme.primary,
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .statusBarsPadding()
                    .navigationBarsPadding()
                    .padding(innerPadding)
            ) {
                //region UI State
                when (state) {
                    //region Loading
                    is AlbumUiState.Loading -> {
                        if (!state.isRefreshing) CircularProgressIndicator()
                    }
                    //endregion
                    //region Success
                    is AlbumUiState.Success -> {
                        LazyColumn(
                            modifier = Modifier.padding(start = 18.dp, end = 18.dp)
                        ) {
                            items(state.data) { album ->
                                //region Card
                                AlbumCard(
                                    modifier = Modifier.wrapContentWidth(),
                                    title = album.title,
                                    id = album.id.toString(),
                                    onAlbumClicked = { navigateToPhoto(album.id.toString()) }
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
                    is AlbumUiState.Error -> {
                        if (!state.isRefreshing) Text(text = "Oops! there is something wrong..")
                    }
                    //endregion
                }
                //endregion
            }
        }
    }
}

//region Preview
@Preview(name = "Album")
@Composable
private fun AlbumPreview() {
    PhotoAlbumTheme {
        AlbumScreen(navigateToPhoto = {})
    }
}

@Preview(name = "Card")
@Composable
private fun CardPreview() {
    PhotoAlbumTheme {
        AlbumCard(modifier = Modifier, title = "Title", id = "1", onAlbumClicked = {})
    }
}
//endregion