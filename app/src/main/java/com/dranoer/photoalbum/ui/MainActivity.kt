package com.dranoer.photoalbum.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dranoer.photoalbum.ui.album.AlbumScreen
import com.dranoer.photoalbum.ui.theme.PhotoAlbumTheme
import com.dranoer.photoalbum.util.Route
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PhotoAlbumTheme {
                AppScreen()
            }
        }
    }
}

//region Internal
@Composable
private fun AppScreen() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Route.Album.route,
    ) {
        composable(route = Route.Album.route) {
            AlbumScreen()
        }
    }
}
//endregion

//region Preview
@Preview(showBackground = true)
@Composable
fun MainPreview() {
    PhotoAlbumTheme {
        AppScreen()
    }
}
//endregion