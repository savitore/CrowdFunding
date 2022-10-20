package com.example.crowdfunding

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class IntroActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context= LocalContext.current
            val isFirstRun = getSharedPreferences("CrowdFunding", MODE_PRIVATE)
                .getBoolean("isFirstRun", true)

            if (isFirstRun) {
                //show start activity
                startActivity(Intent(context, MainActivity2::class.java))
            }
            getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                .putBoolean("isFirstRun", false).commit()
            Intro()
        }
    }

    @Composable
    fun Intro() {
        val context= LocalContext.current
        Scaffold(
            topBar = {
            },
            bottomBar = {},
            backgroundColor = Color.LightGray,
        )
        { padding->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .fillMaxHeight(),
                backgroundColor = Color.White
            )
            {
                val scrollState = rememberScrollState()
                Column(modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState), horizontalAlignment = Alignment.CenterHorizontally) {
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(), horizontalArrangement = Arrangement.Center){
                        Image(painter = painterResource(id = R.drawable.logo), contentDescription ="logo" )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(), horizontalArrangement = Arrangement.Center)
                    {
                        Text(
                            text = "What is Crowdfunding?",
                            fontWeight = FontWeight.Bold,
                            fontSize = 30.sp,
                        )
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                    Row(modifier = Modifier
                        .padding(10.dp)) {
                        Text(text = "In its simplest form, Crowdfunding is a practice of giving monetary funds to overcome specific social, cultural, or economic hurdles individuals face in their daily lives.",
                            fontSize = 20.sp,
                            fontFamily = FontFamily.Serif,
                            color = Color.DarkGray
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(), horizontalArrangement = Arrangement.Center)
                    {
                        Text(
                            text = "How does it work?",
                            fontWeight = FontWeight.Bold,
                            fontSize = 30.sp,
                        )
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                    Row(modifier = Modifier
                        .padding(10.dp)) {
                        Text(text = "Let us assume an individual, unfortunately, meets with an accident on the road. His medical expenses and hospital bills start piling up. Now he needs ₹5 Lakh to pay his expensive medical bills. Fortunately, his best friend signed up on this crowdfunding platform, completed the process of submitting valid documents needed for verification. In a few minutes, he created a crowdfunding campaign to raise funds for his friend’s medical expenses.n a matter of few minutes, funds start coming in to support the financial needs of the injured friend.",
                            fontSize = 20.sp,
                            fontFamily = FontFamily.Serif,
                            color = Color.DarkGray
                        )
                    }

                    Row(horizontalArrangement = Arrangement.End, verticalAlignment = Alignment.Bottom) {
                        Text(text = "Next", fontSize = 18.sp, modifier = Modifier.clickable {
                            val intent = Intent(context,MainActivity2::class.java)
                            context.startActivity(intent)
                        }, color = Color.Blue,fontWeight = FontWeight.Bold)
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun Preview()
{
    IntroActivity()
}