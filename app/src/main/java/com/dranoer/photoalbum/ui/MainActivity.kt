package com.dranoer.photoalbum.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dranoer.photoalbum.R
import com.dranoer.photoalbum.ui.album.AlbumScreen
import com.dranoer.photoalbum.ui.photo.PhotoDetailScreen
import com.dranoer.photoalbum.ui.photo.PhotoScreen
import com.dranoer.photoalbum.ui.photo.PhotoViewModel
import com.dranoer.photoalbum.ui.theme.PhotoAlbumTheme
import com.dranoer.photoalbum.util.Constant
import com.dranoer.photoalbum.util.Route
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    /**
     * Due to the small scope of this app, the same instance of PhotoViewModel
     * is being shared between the PhotoScreen and DetailScreen.
     */
    private val photoViewModel: PhotoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PhotoAlbumTheme {
                AppScreen(photoViewModel = photoViewModel)
            }
        }
    }
}

//region Internal
@Composable
private fun AppScreen(
    photoViewModel: PhotoViewModel,
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Route.Album.route,
    ) {
        //region Album Screen
        composable(route = Route.Album.route) {
            AlbumScreen(
                navigateToPhoto = { id -> navController.navigate(Route.Photo.createRoute(id = id.toInt())) },
            )
        } //endregion
        //region Photo Screen
        composable(
            route = Route.Photo.route,
            arguments = listOf(
                navArgument(Constant.ALBUM_ID) {
                    type = NavType.StringType
                }
            )) { backStackEntry ->
            val albumId = backStackEntry.arguments?.getString(Constant.ALBUM_ID)
            requireNotNull(albumId) { stringResource(id = R.string.not_found) }
            PhotoScreen(
                viewModel = photoViewModel,
                albumId = albumId,
                backPress = { navController.navigateUp() },
                navigateToDetail = { id -> navController.navigate(Route.Detail.createRoute(id = id.toInt())) },
            )
        } //endregion
        //region Detail Screen
        composable(
            route = Route.Detail.route,
            arguments = listOf(
                navArgument(Constant.PHOTO_ID) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val photoId = backStackEntry.arguments?.getString(Constant.PHOTO_ID)
            val selectedPhoto = photoViewModel.getPhotoDetail(photoId = photoId?.toInt() ?: 0)
            selectedPhoto?.let {
                PhotoDetailScreen(
                    photo = it,
                    backPress = { navController.navigateUp() },
                )
            }
        } //endregion
    }
}
//endregion

//region Preview
@Preview(showBackground = true)
@Composable
private fun MainPreview_Normal() {
    PhotoAlbumTheme {
        AppScreen(photoViewModel = hiltViewModel())
    }
} //endregion