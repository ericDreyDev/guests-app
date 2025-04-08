package com.example.guest.ui.screens

import GuestDatabaseHelper
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.guest.data.Guest
import com.example.guest.data.GuestRepositorySQLiteImpl
import com.example.guest.viewmodel.GuestViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GuestListScreen(navController: NavController) {

    val dbHelper = GuestDatabaseHelper(LocalContext.current)
    val repository = GuestRepositorySQLiteImpl(dbHelper)
    val viewmodel = GuestViewModel(repository)
    val guestList by viewmodel.guests.collectAsState()

    var guestToEdit by remember { mutableStateOf<Guest?>(null) }
    var showDialog by remember { mutableStateOf(false) }
    BackHandler {}

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Convidados") },
                navigationIcon = {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(Icons.Default.Menu, contentDescription = "Menu")
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            navController.navigate("ProfileScreen")
                        }
                    ) {
                        Icon(Icons.Default.Person, contentDescription = "Informações")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                guestToEdit = null
                showDialog = true
            }) {
                Icon(Icons.Default.Add, contentDescription = "Adicionar")
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.padding(padding)
        ) {
            items(guestList.size) { index ->
                GuestCard(
                    guest = guestList[index],
                    onDelete = { viewmodel.deleteGuest(guestList[index]) },
                    onEdit = {
                        showDialog = true
                        guestToEdit = guestList[index]
                    },
                    onToggleConfirmed = { guest ->
                        val updatedGuest = guest.copy(confirmed = if (guest.confirmed == 1) 0 else 1)
                        viewmodel.updateGuest(updatedGuest)
                    }
                )
            }
        }
    }
    GuestDialogForm(
        isOpen = showDialog,
        onDismiss = { showDialog = false },
        onSave = { guest ->
            if (guestToEdit == null) {
                viewmodel.insertGuest(guest)
            } else {
                viewmodel.updateGuest(guest)
            }
            showDialog = false
        },
        guest = guestToEdit
    )
}

@Composable
fun GuestCard(
    guest: Guest,
    onDelete: (Guest) -> Unit,
    onEdit: (Guest) -> Unit,
    onToggleConfirmed: (Guest) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = guest.name,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = guest.email,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = guest.phone,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { onToggleConfirmed(guest) }) {
                    Icon(
                        imageVector = if (guest.confirmed == 1) Icons.Filled.CheckCircle else Icons.Outlined.CheckCircle,
                        contentDescription = if (guest.confirmed == 1) "Confirmado" else "Não confirmado"
                    )
                }
                IconButton(onClick = { onEdit(guest) }) {
                    Icon(Icons.Default.Edit, contentDescription = "Alterar")
                }
                IconButton(onClick = { onDelete(guest) }) {
                    Icon(Icons.Default.Delete, contentDescription = "Excluir")
                }
            }
        }
    }
}
