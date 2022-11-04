package com.example.crowdfunding

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.Profile
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class WithdrawActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var userdatabase=UserDatabase
        val dao=UserDatabase.getDatabase(applicationContext).getDao()
        setContent {
            Withdraw()
        }
    }

    @Composable
    fun Withdraw()
    {
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
                    .padding(10.dp),
                backgroundColor = Color.White
            )
            {
                val scrollState = rememberScrollState()
                Column(modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState))
                {
                    Spacer(modifier = Modifier.height(5.dp))
                    Row(modifier = Modifier
                        .padding(10.dp))
                    {
                        Text(text = "Patient's name:", fontSize = 20.sp, color = Color.Black)
                        Spacer(modifier = Modifier.width(2.dp))
                        Text(text = "data", fontSize = 20.sp, color = Color.Black)//name
                    }
                    Spacer(modifier = Modifier.height(0.dp))
                    Row(modifier = Modifier
                        .padding(10.dp))
                    {
                        Text(text = "data", fontSize = 20.sp, color = Color.Black)//title
                    }
                    Spacer(modifier = Modifier.height(0.dp))
                    Row(modifier = Modifier
                        .padding(10.dp))
                    {
                        Text(text = "Funds to be raised: Rs", fontSize = 20.sp, color = Color.Black)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = "data", fontSize = 20.sp, color = Color.Black, fontWeight = FontWeight.Bold)//amount
                    }
                    Spacer(modifier = Modifier.height(0.dp))
                    Row(modifier = Modifier
                        .padding(10.dp))
                    {
                        Text(text = "Funds raised: Rs", fontSize = 20.sp, color = Color.Black)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = "data", fontSize = 20.sp, color = Color.Black, fontWeight = FontWeight.Bold)//amount
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                    Row(
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth(), horizontalArrangement = Arrangement.Center
                    ) {
                        var text1 by remember { mutableStateOf("") }
                        var isError1 by rememberSaveable { mutableStateOf(false) }
                        OutlinedTextField(
                            value = text1,
                            onValueChange = { newText ->
                                text1 = newText
                                isError1 = false
                            },
                            trailingIcon = {
                                if (isError1) {
                                    Icon(
                                        Icons.Filled.Info,
                                        "Error",
                                        tint = MaterialTheme.colors.error
                                    )
                                }
                            },
                            keyboardOptions = KeyboardOptions
                                (
                                keyboardType = KeyboardType.Text,
                                imeAction = ImeAction.Go
                            ),
                            label = { Text(text = "Enter UPI Id") },
                            maxLines = 1,
                            colors = TextFieldDefaults.textFieldColors(
                                backgroundColor = Color.White,
                                focusedIndicatorColor = Color.Blue,
                                unfocusedIndicatorColor = Color.Black,
                                focusedLabelColor = Color.Blue
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )
                        if (isError1) {
                            Text(
                                text = "UPI Id cannot be empty",
                                color = MaterialTheme.colors.error,
                                style = MaterialTheme.typography.caption,
                                modifier = Modifier.padding(start = 14.dp)
                            )
                        }
                    }
                        Row(
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxWidth(), horizontalArrangement = Arrangement.Center
                        )
                        {
                            Button(
                                onClick = {
                                    Toast.makeText(
                                        context,
                                        "You will receive funds within 2-4 business days.",
                                        Toast.LENGTH_LONG
                                    ).show()
                                    val intent = Intent(context, ProfileActivity::class.java)
                                    context.startActivity(intent)
                                },
                                enabled = true,
                                border = BorderStroke(width = 1.dp, brush = SolidColor(Color.Blue)),
                                shape = MaterialTheme.shapes.medium,
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = Color.Transparent,
                                    contentColor = Color.Blue
                                ),
//                          modifier = Modifier.height(10.dp).width(30.dp)
                            ) {
                                Text(text = "Submit")
                            }
                        }

                }
            }
        }//scaffold
    }
    @Preview(showBackground = true)
    @Composable
    fun Preview(){
        Withdraw()
    }
}

