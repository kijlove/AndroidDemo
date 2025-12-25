package com.kijlee.android.demo.ui.compose.ui



import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.ui.compose.ui
 * @ClassName:      ContactsScreen
 * @Author:     kij
 * @Description:
 * @Date:    2025/12/18 15:17
 * @Version:    1.0
 */



data class Contact(val name: String, val department: String)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ContactsScreen() {
    val contacts = mapOf(
        "A" to listOf(Contact("Alice", "HR"), Contact("Alex", "Engineering")),
        "B" to listOf(Contact("Bob", "Sales"), Contact("Brian", "Marketing")),
        "C" to listOf(Contact("Charlie", "Engineering"), Contact("Chloe", "Design")),
        "D" to listOf(Contact("David", "Product"), Contact("Diana", "HR")),
        "E" to listOf(Contact("Eve", "Engineering"), Contact("Eric", "Sales")),
        "F" to listOf(Contact("Frank", "Marketing"), Contact("Fiona", "Design")),
        "G" to listOf(Contact("George", "Product"), Contact("Grace", "HR")),
        "H" to listOf(Contact("Harry", "Engineering"), Contact("Hannah", "Sales")),
        "J" to listOf(Contact("Jack", "Marketing"), Contact("Julia", "Design")),
        "K" to listOf(Contact("Kevin", "Product"), Contact("Kate", "HR")),
        "L" to listOf(Contact("Liam", "Engineering"), Contact("Lily", "Sales")),
        "M" to listOf(Contact("Mike", "Marketing"), Contact("Mia", "Design")),
        "N" to listOf(Contact("Nathan", "Product"), Contact("Nina", "HR")),
        "O" to listOf(Contact("Oliver", "Engineering"), Contact("Olivia", "Sales")),
        "P" to listOf(Contact("Peter", "Marketing"), Contact("Penny", "Design")),
        "R" to listOf(Contact("Ryan", "Product"), Contact("Rachel", "HR")),
        "S" to listOf(Contact("Sam", "Engineering"), Contact("Sarah", "Sales")),
        "T" to listOf(Contact("Tom", "Marketing"), Contact("Tina", "Design")),
        "V" to listOf(Contact("Victor", "Product"), Contact("Victoria", "HR")),
        "W" to listOf(Contact("William", "Engineering"), Contact("Wendy", "Sales")),
        "Z" to listOf(Contact("Zack", "Marketing"), Contact("Zoe", "Design"))
    )

    val sortedKeys = contacts.keys.sorted()
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    // State for drag gesture and overlay
    var selectedLetter by remember { mutableStateOf<String?>(null) }
    var sidebarHeight by remember { mutableStateOf(0f) }

    // Calculate indices for each letter
    val letterIndices = remember(contacts) {
        val indices = mutableMapOf<String, Int>()
        var currentIndex = 0
        sortedKeys.forEach { key ->
            indices[key] = currentIndex
            // Add 1 for header + number of items
            currentIndex += 1 + (contacts[key]?.size ?: 0)
        }
        indices
    }

    fun scrollToLetter(letter: String) {
        val index = letterIndices[letter] ?: 0
        coroutineScope.launch {
            listState.scrollToItem(index)
        }
    }

    fun getLetterFromPosition(y: Float): String? {
        if (sidebarHeight == 0f || sortedKeys.isEmpty()) return null
        val itemHeight = sidebarHeight / sortedKeys.size
        val index = (y / itemHeight).toInt().coerceIn(0, sortedKeys.size - 1)
        return sortedKeys[index]
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Contacts",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(16.dp)
        )

        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                state = listState
            ) {
                sortedKeys.forEach { initial ->
                    val list = contacts[initial] ?: emptyList()
                    stickyHeader {
                        ContactHeader(initial)
                    }
                    items(list) { contact ->
                        ContactItem(contact)
                    }
                }
            }

            // Sidebar
            Column(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .fillMaxHeight()
                    .padding(vertical = 16.dp, horizontal = 4.dp)
                    .onGloballyPositioned { coordinates ->
                        sidebarHeight = coordinates.size.height.toFloat()
                    }
                    .pointerInput(Unit) {
                        detectVerticalDragGestures(
                            onDragStart = { offset ->
                                val letter = getLetterFromPosition(offset.y)
                                if (letter != null) {
                                    selectedLetter = letter
                                    scrollToLetter(letter)
                                }
                            },
                            onDragEnd = {
                                selectedLetter = null
                            },
                            onDragCancel = {
                                selectedLetter = null
                            },
                            onVerticalDrag = { change, _ ->
                                val letter = getLetterFromPosition(change.position.y)
                                if (letter != null && letter != selectedLetter) {
                                    selectedLetter = letter
                                    scrollToLetter(letter)
                                }
                            }
                        )
                    }
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onPress = { offset ->
                                val letter = getLetterFromPosition(offset.y)
                                if (letter != null) {
                                    selectedLetter = letter
                                    scrollToLetter(letter)
                                    tryAwaitRelease()
                                    selectedLetter = null
                                }
                            }
                        )
                    },
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                sortedKeys.forEach { letter ->
                    Text(
                        text = letter,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(vertical = 2.dp)
                    )
                }
            }

            // Overlay
            if (selectedLetter != null) {
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .background(Color.Gray.copy(alpha = 0.5f), MaterialTheme.shapes.medium)
                        .align(Alignment.Center),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = selectedLetter!!,
                        style = MaterialTheme.typography.displayLarge,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun ContactHeader(initial: String) {
    Surface(
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = initial,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun ContactItem(contact: Contact) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(MaterialTheme.colorScheme.primaryContainer, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
        Column(modifier = Modifier.padding(start = 16.dp)) {
            Text(text = contact.name, style = MaterialTheme.typography.bodyLarge)
            Text(text = contact.department, style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
        }
    }
}
