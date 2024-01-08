package com.koleff.kare_android.ui.compose.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.koleff.kare_android.R
import com.koleff.kare_android.data.MainScreen

@Composable
fun HomeScreen(navController: NavHostController){
    Column(modifier = Modifier.fillMaxSize()
        .background(color = Color.White),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally){
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
            modifier = Modifier
                .width(160.dp)
                .height(200.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(100.dp))
        Button(
            onClick = {
                navController.navigate(MainScreen.Login.route)
            },
            modifier = Modifier
                .width(300.dp)
                .height(56.dp)
        ) {
            androidx.compose.material3.Text(text = "Log in",
                fontSize = 18.sp)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                navController.navigate(MainScreen.Register.route)
            },
            modifier = Modifier
                .width(300.dp)
                .height(56.dp)
        ) {

            androidx.compose.material3.Text(text = "Sign up",
                fontSize = 18.sp)
        }
    }
}
