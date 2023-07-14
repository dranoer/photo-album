package com.dranoer.photoalbum.ui.album

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dranoer.photoalbum.R
import com.dranoer.photoalbum.ui.theme.PhotoAlbumTheme

@Composable
fun AlbumScreen(
    viewModel: AlbumViewModel = hiltViewModel(),
) {
    val state = viewModel.albumState.collectAsState().value

    PhotoAlbumTheme {
        Scaffold(
            topBar = { AppBar() },
            containerColor = MaterialTheme.colorScheme.primary
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
                                    title = album.title
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

//region Internal
@Composable
private fun AlbumCard(modifier: Modifier, title: String) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.Yellow),
        shape = RoundedCornerShape(6)
    ) {
        Text(modifier = modifier.padding(10.dp), text = title)
    }
}

@Composable
private fun AppBar() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(start = 18.dp)
    ) {
        //region Title
        Text(text = stringResource(id = R.string.app_name))
        //endregion
        //region Icon
        IconButton(
            modifier = Modifier.padding(16.dp),
            onClick = { /* todo */ }
        ) {
            Icon(
                imageVector = Icons.Filled.AccountCircle,
                contentDescription = stringResource(R.string.account)
            )
        }
        //endregion
    }
}
//endregion

//region Preview
@Preview(name = "Album")
@Composable
private fun AlbumPreview() {
    PhotoAlbumTheme {
        AlbumScreen()
    }
}

@Preview(name = "Card")
@Composable
private fun CardPreview() {
    PhotoAlbumTheme {
        AlbumCard(modifier = Modifier, title = "Title")
    }
}
//endregion