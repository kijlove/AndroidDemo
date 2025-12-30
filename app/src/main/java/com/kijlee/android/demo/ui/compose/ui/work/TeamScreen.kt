package com.kijlee.android.demo.ui.compose.ui.work

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.ui.compose
 * @ClassName:      TeamScreen
 * @Author:     kij
 * @Description:
 * @Date:    2025/12/18 15:41
 * @Version:    1.0
 */



data class TeamMember(val name: String, val role: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamScreen(onBack: () -> Unit) {
    val teamMembers = listOf(
        TeamMember("Alice Smith", "Project Manager"),
        TeamMember("Bob Jones", "Lead Developer"),
        TeamMember("Charlie Brown", "UI/UX Designer"),
        TeamMember("David Lee", "Backend Developer"),
        TeamMember("Eve Wilson", "QA Engineer"),
        TeamMember("Frank Miller", "DevOps Engineer"),
        TeamMember("Grace Davis", "Frontend Developer"),
        TeamMember("Henry White", "Data Scientist"),
        TeamMember("Alice Smith", "Project Manager"),
        TeamMember("Bob Jones", "Lead Developer"),
        TeamMember("Charlie Brown", "UI/UX Designer"),
        TeamMember("David Lee", "Backend Developer"),
        TeamMember("Eve Wilson", "QA Engineer"),
        TeamMember("Frank Miller", "DevOps Engineer"),
        TeamMember("Grace Davis", "Frontend Developer"),
        TeamMember("Henry White", "Data Scientist"),
        TeamMember("Alice Smith", "Project Manager"),
        TeamMember("Bob Jones", "Lead Developer"),
        TeamMember("Charlie Brown", "UI/UX Designer"),
        TeamMember("David Lee", "Backend Developer"),
        TeamMember("Eve Wilson", "QA Engineer"),
        TeamMember("Frank Miller", "DevOps Engineer"),
        TeamMember("Grace Davis", "Frontend Developer"),
        TeamMember("Henry White", "Data Scientist")
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Team Members") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            items(teamMembers) { member ->
                TeamMemberItem(member)
            }
        }
    }
}

@Composable
fun TeamMemberItem(member: TeamMember) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(50.dp)
                .background(MaterialTheme.colorScheme.secondaryContainer, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSecondaryContainer
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = member.name,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = member.role,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }
    }
}
