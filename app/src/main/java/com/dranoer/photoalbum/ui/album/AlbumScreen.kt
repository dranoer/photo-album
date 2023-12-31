package com.dranoer.photoalbum.ui.album

import android.content.res.Configuration
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.dranoer.photoalbum.R
import com.dranoer.photoalbum.ui.model.AlbumUiModel
import com.dranoer.photoalbum.ui.model.AlbumUiState
import com.dranoer.photoalbum.ui.theme.PhotoAlbumTheme
import com.dranoer.photoalbum.util.getRandomColor
import com.dranoer.rijksmuseum.ui.component.ErrorView
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlbumScreen(
    viewModel: AlbumViewModel = hiltViewModel(),
    navigateToPhoto: (String) -> Unit,
) {
    val state = viewModel.albumState.collectAsState().value
    LaunchedEffect(Unit) {
        viewModel.fetchAlbums()
    }

    PhotoAlbumTheme {
        Scaffold(
            containerColor = when (isSystemInDarkTheme()) {
                true -> colorResource(id = R.color.black)
                false -> colorResource(id = R.color.gray_100)
            },
            //region Toolbar
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = stringResource(id = R.string.albums),
                            color = when (isSystemInDarkTheme()) {
                                true -> colorResource(id = R.color.gray_100)
                                false -> colorResource(id = R.color.gray_500)
                            }
                        )
                    },
                    colors = topAppBarColors(
                        containerColor = when (isSystemInDarkTheme()) {
                            true -> colorResource(id = R.color.black)
                            false -> colorResource(id = R.color.gray_100)
                        }
                    ),
                )
            }, //endregion
            //region Content
            content = { padding ->
                val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = false)

                SwipeRefresh(
                    state = swipeRefreshState,
                    onRefresh = { viewModel.fetchAlbums() }
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues = padding),
                    ) {
                        when (state) {
                            is AlbumUiState.Empty -> {
                                Text(text = stringResource(id = R.string.no_data))
                            }

                            is AlbumUiState.Loading -> {
                                CircularProgressIndicator()
                            }

                            is AlbumUiState.Loaded -> {
                                AlbumList(data = state.data, navigateToPhoto = navigateToPhoto)
                            }

                            is AlbumUiState.Error -> {
                                ErrorView(
                                    message = state.message,
                                    refresh = viewModel::fetchAlbums,
                                )
                            }
                        }
                    }
                }
            } //endregion
        )
    }
}

@Composable
private fun AlbumList(
    data: List<AlbumUiModel>,
    navigateToPhoto: (String) -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.album_card_space)),
            modifier = Modifier.padding(
                start = dimensionResource(id = R.dimen.size_26),
                top = dimensionResource(id = R.dimen.size_8),
                end = dimensionResource(id = R.dimen.size_26)
            ),
        ) {
            items(data) { album ->
                AlbumView(
                    title = album.title,
                    id = album.id.toString(),
                    color = getRandomColor(),
                    onAlbumClicked = { navigateToPhoto(album.id.toString()) },
                    modifier = Modifier.wrapContentWidth(),
                )
            }
        }
    }
}

//region Preview
@Preview(name = "LightTheme", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "DarkTheme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AlbumPreview() {
    PhotoAlbumTheme {
        AlbumScreen(navigateToPhoto = {})
    }
}

@Preview(name = "LightTheme", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "DarkTheme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun AlbumListPreview() {
    PhotoAlbumTheme {
        AlbumList(
            data = listOf(
                AlbumUiModel(
                    userId = 1,
                    id = 2,
                    title = "AlbumItem title"
                ),
                AlbumUiModel(
                    userId = 1,
                    id = 2,
                    title = "AlbumItem title"
                ),
                AlbumUiModel(
                    userId = 1,
                    id = 2,
                    title = "AlbumItem title"
                )
            ),
            navigateToPhoto = {},
        )
    }
}
//endregion