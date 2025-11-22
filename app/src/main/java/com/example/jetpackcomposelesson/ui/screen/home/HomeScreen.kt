package com.example.jetpackcomposelesson.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jetpackcomposelesson.ui.theme.LocalThemeManager
import com.example.jetpackcomposelesson.ui.theme.color.baseColorTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel = viewModel()){
    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Small Top App Bar")
                },
                navigationIcon = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                },
            )
        }
    ){ it
        Section()
    }
}

@Preview(showBackground = true)
@Composable
fun Section(){
    val manager = LocalThemeManager.current

    Column(
        modifier = Modifier.fillMaxSize().padding(all = 16.dp),
        verticalArrangement = Arrangement.spacedBy(space = 10.dp, alignment = Alignment.CenterVertically),
        horizontalAlignment = Alignment.Start
    ) {
        /// Title
        Text("Featured", style = MaterialTheme.typography.titleLarge)

        /// Boxes
        Row(modifier = Modifier.fillMaxWidth().height(120.dp)
        ) {
            Box(modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .background(color = MaterialTheme.baseColorTheme.primaryColor, shape = RoundedCornerShape(16.dp))
            )
            Spacer(modifier = Modifier.width(16.dp))
            Box(modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .background(color = MaterialTheme.baseColorTheme.secondaryColor, shape = RoundedCornerShape(16.dp)))

        }

        Switch(
            modifier = Modifier.fillMaxWidth(),
            checked = manager.isDarkTheme,
            onCheckedChange = {
                manager.changeTheme()
            }
        )
    }
}