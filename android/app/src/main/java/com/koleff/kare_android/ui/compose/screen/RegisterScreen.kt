package com.koleff.kare_android.ui.compose.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.koleff.kare_android.R
import com.koleff.kare_android.data.MainScreen


@Composable
fun RegisterScreen(navController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize()
        .background(color = Color.White),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally)
    {
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
                .height(400.dp)
                .background(
                    color = Color(0xFFD0BCFF),
                    shape = RoundedCornerShape(16.dp)
                ),) {
            Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                    ) {
                RoundedTextField("Username:")
                Spacer(modifier = Modifier.height(16.dp))
                RoundedTextField("Email:")
                Spacer(modifier = Modifier.height(16.dp))
                RoundedTextField("Password:", isPassword = true)
                Spacer(modifier = Modifier.height(16.dp))
                val confirmPasswordText = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Color.Black)) {
                        append("Confirm\n")
                    }
                    withStyle(style = SpanStyle(color = Color.Gray)) {
                        append("Password:")
                    }
                }
                RoundedTextField(confirmPasswordText.toString(), isPassword = true)

                Spacer(modifier = Modifier.height(16.dp))

                val usernameState = remember { mutableStateOf(TextFieldValue()) }
                val emailState = remember { mutableStateOf(TextFieldValue()) }
                val passwordState = remember { mutableStateOf(TextFieldValue()) }
                val confirmPasswordState = remember { mutableStateOf(TextFieldValue()) }
                Button(
                    onClick = {
                        val username = usernameState.value.text
                        val email = emailState.value.text
                        val password = passwordState.value.text
                        val confirmPassword = confirmPasswordState.value.text
                        if (isValidInput(username, email, password, confirmPassword)) {
                            println("Sign-up successful!")
                            navController.navigate(MainScreen.Home.route)
                        } else {
                            println("Sign-up failed.")
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                ) {
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Sign up",
                        fontSize = 18.sp)
                }
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(color = Color.Black)) {
                                append("Already a member? ")
                            }
                            withStyle(style = SpanStyle(color = Color.Blue, textDecoration = TextDecoration.Underline)) {
                                append("Log in!")
                            }
                        },
                        modifier = Modifier
                            .clickable {
                                navController.navigate(MainScreen.Login.route)
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
fun RoundedTextField(label: String, isPassword: Boolean = false) {
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
                .height(35.dp),
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None
        )
    }
}
private fun isValidInput(
    username: String,
    email: String,
    password: String,
    confirmPassword: String
): Boolean {

    return username.isNotBlank() &&
            email.isNotBlank() &&
            isValidEmail(email) &&
            password.isNotBlank() &&
            confirmPassword.isNotBlank() &&
            password == confirmPassword
}

private fun isValidEmail(email: String): Boolean {
    // A simple email validation check
    val emailRegex = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    return email.matches(emailRegex.toRegex())
}
