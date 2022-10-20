package com.example.crowdfunding

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class ProfileActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Profile()
            }
        }

    @Composable
    fun Profile()
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
                      .fillMaxWidth()
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
                          Button(
                              onClick = {
                                        val intent=Intent(context,WithdrawActivity::class.java)
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
                              Text(text = "Withdraw funds")
                          }
                      }
                }
            }
        }//scaffold
    }
    @Preview(showBackground = true)
    @Composable
    fun Preview(){
        Profile()
    }
}

