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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dranoer.photoalbum.R
import com.dranoer.photoalbum.ui.component.AlbumCard
import com.dranoer.photoalbum.ui.theme.PhotoAlbumTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlbumScreen(
    viewModel: AlbumViewModel = hiltViewModel(),
    navigateToPhoto: (String) -> Unit,
) {
    val state = viewModel.albumState.collectAsState().value

    PhotoAlbumTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = stringResource(id = R.string.app_name)) },
                    colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = colorResource(id = R.color.gray_100))
                )
            },
            containerColor = colorResource(id = R.color.gray_100),
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .statusBarsPadding()
                    .navigationBarsPadding()
                    .padding(innerPadding)
            ) {
                when (state) {
                    is AlbumUiState.Empty -> {
                        Text(text = "No data available")
                    }

                    is AlbumUiState.Loading -> {
                        CircularProgressIndicator()
                    }

                    is AlbumUiState.Loaded -> {
                        LazyColumn(
                            modifier = Modifier.padding(start = 18.dp, top = 16.dp, end = 18.dp)
                        ) {
                            items(state.data) { album ->
                                //region Card
                                AlbumCard(
                                    modifier = Modifier.wrapContentWidth(),
                                    title = album.title,
                                    id = album.id.toString(),
                                    onAlbumClicked = { navigateToPhoto(album.id.toString()) }
                                ) //endregion
                                //region Vertical Space
                                Spacer(
                                    modifier = Modifier.height(10.dp),
                                ) //endregion
                            }
                        }
                    }

                    is AlbumUiState.Error -> {
                        Text(text = "Oops! there is something wrong..")
                    }
                }
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