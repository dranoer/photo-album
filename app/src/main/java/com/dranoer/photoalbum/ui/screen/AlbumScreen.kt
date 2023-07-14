package com.dranoer.photoalbum.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dranoer.photoalbum.ui.theme.PhotoAlbumTheme

@Composable
fun AlbumScreen() {
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Text(text = "Android")
    }
}

//region Preview
@Preview
@Composable
private fun AlbumPreview() {
    PhotoAlbumTheme {
        AlbumScreen()
    }
}
//endregion