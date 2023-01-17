package com.mx.android.ui.components.topAppBars

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Contrast
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TopBarItem(title: String, image: ImageVector) {
    Row(modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = title,
            modifier = Modifier
                .padding(start = 2.dp))
        Icon(
            imageVector = image,
            contentDescription = "Top bar icon",
            modifier = Modifier
                .padding(start = 16.dp)
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun DashboardTest() {
    Column {
        TopBarItem("Tema del dispositivo", Icons.Filled.Contrast)
    }
}