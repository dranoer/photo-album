package com.dranoer.photoalbum.ui.photo

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dranoer.photoalbum.R
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
            topBar = {
                TopAppBar(
                    title = { Text(text = "Photos") },
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
                        .padding(padding)
                ) {
                    when (val state = viewModel.photoState.collectAsState().value) {
                        is PhotoUiState.Empty -> {
                            Text(text = "No data available")
                        }

                        is PhotoUiState.Loading -> {
                            CircularProgressIndicator()
                        }

                        is PhotoUiState.Loaded -> {
                            LazyVerticalGrid(
                                modifier = Modifier.padding(start = 18.dp, end = 18.dp),
                                columns = GridCells.Adaptive(minSize = 128.dp),
                            ) {
                                items(state.data) { photo ->
                                    //region Card
                                    PhotoCard(
                                        modifier = Modifier.wrapContentWidth(),
                                        photo = photo,
                                        onPhotoClicked = { navigateToDetail(photo.id.toString()) }
                                    ) //endregion
                                    //region Vertical Space
                                    Spacer(
                                        modifier = Modifier.height(10.dp)
                                    ) //endregion
                                }
                            }
                        }

                        is PhotoUiState.Error -> {
                            Text(text = "Oops! there is something wrong..")
                        }
                    }
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
        PhotoScreen(viewModel = hiltViewModel(), albumId = "1", backPress = {}, navigateToDetail = {})
    }
}
//endregion