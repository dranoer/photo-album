package com.dranoer.photoalbum.ui.photo

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.dranoer.photoalbum.R
import com.dranoer.photoalbum.domain.model.PhotoItem
import com.dranoer.photoalbum.ui.component.PhotoCard
import com.dranoer.photoalbum.ui.theme.PhotoAlbumTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhotoScreen(
    albumId: String,
    viewModel: PhotoViewModel,
    backPress: () -> Unit,
    navigateToDetail: (String) -> Unit,
) {
    LaunchedEffect(key1 = albumId) {
        viewModel.fetchPhotos(albumId = albumId.toInt())
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
                            text = stringResource(id = R.string.photos),
                            color = when (isSystemInDarkTheme()) {
                                true -> colorResource(id = R.color.gray_100)
                                false -> colorResource(id = R.color.gray_500)
                            },
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { backPress() }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = stringResource(id = R.string.photos),
                                tint = when (isSystemInDarkTheme()) {
                                    true -> colorResource(id = R.color.gray_100)
                                    false -> colorResource(id = R.color.gray_500)
                                },
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = when (isSystemInDarkTheme()) {
                            true -> colorResource(id = R.color.black)
                            false -> colorResource(id = R.color.gray_100)
                        }
                    )
                )
            }, //endregion
            //region Content
            content = { padding ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                ) {
                    when (val state = viewModel.photoState.collectAsState().value) {
                        is PhotoUiState.Empty -> {
                            Text(text = stringResource(R.string.no_data))
                        }

                        is PhotoUiState.Loading -> {
                            CircularProgressIndicator()
                        }

                        is PhotoUiState.Loaded -> {
                            PhotoList(data = state.data, navigateToDetail = navigateToDetail)
                        }

                        is PhotoUiState.Error -> {
                            Text(text = stringResource(R.string.error))
                        }
                    }
                }
            } //endregion
        )
    }
}

@Composable
private fun PhotoList(data: List<PhotoItem>, navigateToDetail: (String) -> Unit) {
    LazyVerticalGrid(
        modifier = Modifier.padding(
            start = dimensionResource(id = R.dimen.size_18),
            top = dimensionResource(id = R.dimen.size_2),
            end = dimensionResource(id = R.dimen.size_18),
            bottom = dimensionResource(id = R.dimen.size_18)
        ),
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalArrangement = Arrangement.SpaceAround,
    ) {
        items(data) { photo ->
            //region Card
            PhotoCard(
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight(),
                photo = photo,
                onPhotoClicked = { navigateToDetail(photo.id.toString()) }
            ) //endregion
        }
    }
}

//region Preview
@Preview
@Composable
private fun PhotoScreenPreview_Normal() {
    PhotoAlbumTheme {
        PhotoScreen(viewModel = hiltViewModel(), albumId = "1", backPress = {}, navigateToDetail = {})
    }
}

@Preview
@Composable
private fun PhotoListPreview_Normal() {
    PhotoAlbumTheme {
        PhotoList(
            data = listOf(
                PhotoItem(
                    albumId = 1,
                    id = 2,
                    title = "This is a normal title.",
                    url = "",
                    thumbnailUrl = ""
                ),
                PhotoItem(
                    albumId = 3,
                    id = 4,
                    title = "This is a very long long long long long long long long long long long long long long long  title.",
                    url = "",
                    thumbnailUrl = ""
                ),
            ),
            navigateToDetail = {},
        )
    }
}
//endregion