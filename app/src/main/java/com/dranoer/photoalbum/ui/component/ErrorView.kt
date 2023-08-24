package com.dranoer.rijksmuseum.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.dranoer.photoalbum.R
import com.dranoer.photoalbum.ui.theme.PhotoAlbumTheme

@Composable
fun ErrorView(
    message: String,
    refresh: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(message)
        Spacer(Modifier.height(dimensionResource(id = R.dimen.size_16)))
        Button(onClick = refresh) { Text(text = stringResource(id = R.string.retry)) }
    }
}

//region Preview
@Preview
@Composable
private fun PreviewErrorView_Normal() {
    PhotoAlbumTheme() {
        ErrorView(
            message = "Oops! Something went wrong..",
            refresh = {}
        )
    }
} //endregion