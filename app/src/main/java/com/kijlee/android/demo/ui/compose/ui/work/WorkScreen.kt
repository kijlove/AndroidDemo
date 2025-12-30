package com.kijlee.android.demo.ui.compose.ui.work

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
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.kijlee.android.demo.ui.compose.model.Module
import com.kijlee.android.demo.ui.compose.ui.home.MenuCard
import com.kijlee.android.demo.ui.compose.ui.home.MenuItem
import org.json.JSONArray

@Composable
fun WorkScreen(
    modules: List<Module>,
    onModuleClick: (Module) -> Unit
) {
    // Filter root modules (pid == "0")
    val rootModules = remember(modules) {
        modules.filter { it.pid == "0" }
    }



    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Work",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(16.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(rootModules) { module ->
                // Check if this module has children
                val hasChildren = modules.any { it.pid == module.mid }
                
                val icon = if (hasChildren) Icons.Default.Folder else Icons.Default.Work
                // We reuse MenuItem from Home, assuming it accepts ImageVector
                MenuCard(item = MenuItem(module.name, icon), onClick = {
                    onModuleClick(module)
                })
            }
        }
    }
}
