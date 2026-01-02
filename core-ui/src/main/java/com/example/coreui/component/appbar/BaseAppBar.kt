package com.example.coreui.component.appbar

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.coreui.extension.Zero


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseAppBar(
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior,
    title: String,
) {
    CenterAlignedTopAppBar(
        windowInsets = WindowInsets.Zero,
        scrollBehavior = scrollBehavior,
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            scrolledContainerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp),
            titleContentColor = MaterialTheme.colorScheme.onBackground
        ),
        modifier = modifier.heightIn(max = 48.dp),
    )
}