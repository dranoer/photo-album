package com.dranoer.photoalbum.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dranoer.photoalbum.R
import com.dranoer.photoalbum.ui.theme.PhotoAlbumTheme

@Composable
fun AppBar(title: String) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(start = 18.dp)
    ) {
        //region Title
        Text(text = title)
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

//region Preview
@Preview(name = "Normal")
@Composable
private fun AppBarPreview() {
    PhotoAlbumTheme {
        AppBar(title = "AppBar Title")
    }
}
//endregion