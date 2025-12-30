package com.example.loginapp.ui.home

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.kijlee.android.demo.ui.compose.model.Module
import com.kijlee.android.demo.ui.compose.ui.home.MenuCard
import com.kijlee.android.demo.ui.compose.ui.home.MenuItem


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubModuleScreen(
    currentModule: Module,
    allModules: List<Module>,
    onItemClick: (Module) -> Unit,
    onBack: () -> Unit
) {
    val subModules = remember(currentModule, allModules) {
        allModules.filter { it.pid == currentModule.mid }
    }
    
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(currentModule.name) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(subModules) { module ->
                    val hasChildren = allModules.any { it.pid == module.mid }
                    val icon = if (hasChildren) Icons.Default.Folder else Icons.Default.Description
                    
                    MenuCard(
                        item = MenuItem(module.name, icon),
                        onClick = {
                             if (hasChildren) {
                                 onItemClick(module)
                             } else {
                                 // Leaf node action
                                 Toast.makeText(context, "Opened ${module.name}", Toast.LENGTH_SHORT).show()
                             }
                        }
                    )
                }
            }
        }
    }
}
