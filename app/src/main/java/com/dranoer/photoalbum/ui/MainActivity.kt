package com.dranoer.photoalbum.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dranoer.photoalbum.ui.album.AlbumScreen
import com.dranoer.photoalbum.ui.photo.PhotoScreen
import com.dranoer.photoalbum.ui.theme.PhotoAlbumTheme
import com.dranoer.photoalbum.util.Constant
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
        //region Album
        composable(route = Route.Album.route) {
            AlbumScreen(
                navigateToPhoto = { id -> navController.navigate(Route.Photo.createRoute(id = id.toInt())) },
            )
        }
        //endregion
        //region Photo
        composable(
            route = Route.Photo.route,
            arguments = listOf(
                navArgument(Constant.ALBUM_ID) {
                    type = NavType.StringType
                }
            )) { backStackEntry ->
            val albumId = backStackEntry.arguments?.getString(Constant.ALBUM_ID)
            Log.d("naz", "id > $albumId")
            requireNotNull(albumId) { "ID not found" }
            PhotoScreen(
                albumId = albumId,
                backPress = { navController.navigateUp() },
            )
        }
        //endregion
        //region Detail
        //endregion
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