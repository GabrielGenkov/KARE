package com.koleff.kare_android.ui.compose.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.koleff.kare_android.R
import com.koleff.kare_android.ui.MainScreen

@Composable
fun Text(
    text: String,
    style: Typography,
    modifier: Modifier) {
}

@Composable
fun LoginScreen(navController: NavHostController) {

    Column(modifier = Modifier.fillMaxSize()
        .background(color = Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
            modifier = Modifier
                .width(160.dp)
                .height(200.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .width(300.dp)
                .height(265.dp)
                .background(
                    color = Color(0xFFD0BCFF),
                    shape = RoundedCornerShape(16.dp)
                ),) {


            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Log in",
                    style = MaterialTheme.typography,
                    modifier = Modifier
                        .padding(top = 16.dp)
                )
                RoundedTextField("Email:")
                Spacer(modifier = Modifier.height(16.dp))
                RoundedTextField("Password:", isPassword = true)
                Spacer(modifier = Modifier.height(16.dp))
                val emailState = remember { mutableStateOf(TextFieldValue()) }
                val passwordState = remember { mutableStateOf(TextFieldValue()) }
                Button(

                    onClick = {
                        val enteredEmail = emailState.value.text
                        val enteredPassword = passwordState.value.text

                        if (isValidCredentials(enteredEmail, enteredPassword)) {
                            navController.navigate(MainScreen.Dashboard.route)
                        } else {
                            println("Login failed. Invalid credentials.")
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                ) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "Log in",
                        fontSize = 18.sp)
                }

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(color = Color.Black)) {
                                append("Not a member? ")
                            }
                            withStyle(style = SpanStyle(color = Color.Blue, textDecoration = TextDecoration.Underline)) {
                                append("Sign up!")
                            }
                        },
                        modifier = Modifier
                            .clickable {
                                navController.navigate(MainScreen.Register.route)
                            }
                            .padding(bottom = 16.dp),
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        }
    }
}
@Composable
fun TextField(label: String, isPassword: Boolean = false) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White, shape = RoundedCornerShape(16.dp))
            .padding(8.dp)
    ) {
        Text(text = label, modifier = Modifier.padding(end = 8.dp))
        TextField(
            value = "",
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp),
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None
        )
    }
}

@Composable
fun Box(modifier: Modifier){}


private fun isValidCredentials(email: String, password: String): Boolean {
    return email == "user@example.com" && password == "password123"
}