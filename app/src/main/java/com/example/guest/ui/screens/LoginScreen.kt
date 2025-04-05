package com.example.guest.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.guest.ui.theme.Purple40

@Composable
fun LoginScreen(navController: NavController) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Scaffold(
        content = { innerPadding ->
            Box(
                modifier = Modifier.padding(innerPadding).fillMaxSize(),
                contentAlignment = Alignment.TopCenter
            ) {
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(horizontal = 14.dp)
                ) {
                    Text(
                        text = "Login",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.W400,
                        color = Purple40,
                        modifier = Modifier.padding(top = 120.dp)

                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Column {
                        OutlinedTextField(
                            label = { Text("E-mail") },
                            value = email,
                            onValueChange = {
                                email = it
                            },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                            modifier = Modifier.padding(bottom = 10.dp).fillMaxWidth()
                        )
                        OutlinedTextField(
                            label = { Text("Senha") },
                            value = password,
                            onValueChange = {
                                password = it
                            },
                            visualTransformation = PasswordVisualTransformation(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                            modifier = Modifier.padding(bottom = 12.dp).fillMaxWidth()
                        )
                        Button(
                            onClick = { navController.navigate("GuestListScreen") {
                            } },

                            modifier = Modifier.padding(top = 10.dp).fillMaxWidth(),
                        ) {
                            Text("Entrar")
                        }
                    }
                    Text(
                        text = "Esqueci minha senha",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W500,
                        color = Purple40,
                        modifier = Modifier.padding(top = 12.dp)
                    )
                    Text(
                        text = "Cadastrar-se",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W500,
                        color = Purple40,
                        modifier = Modifier.padding(top = 25.dp)
                    )
                }
            }
        }

    )
}