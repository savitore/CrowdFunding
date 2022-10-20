package com.example.crowdfunding

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.google.gson.Gson

class PostExpandableActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val userString = intent.extras!!.getString("user")!!
//        val user = Gson().fromJson(userString, User::class.java)

        setContent {

//            val bundle:Bundle?=intent.extras
//            val name=bundle!!.getString("name")
//            val amount= bundle.getString("amount")
//
//            val desc= bundle.getString("desc")
//            val id= bundle.getString("id")
//            val pic= bundle.getString("pic")
//            val age= bundle.getString("age")
//            val user=User(id,name,age,desc,amount,pic)
            PostContent()
        }
    }
    @Composable
    fun PostContent() {
        val bundle:Bundle?=intent.extras
        val name=bundle!!.getString("name")
        val amount= bundle.getString("amount")
        val title=bundle.getString("title")
        val desc= bundle.getString("desc")
        val id= bundle.getString("id")
        val pic= bundle.getString("pic")
        val age= bundle.getString("age")
        Scaffold(
            topBar = {
                TopBar()
            },
            bottomBar = {},
            backgroundColor = Color.LightGray,
        )
        {padding->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .fillMaxHeight(),
                backgroundColor = Color.White
            )
            {
                val scrollState = rememberScrollState()
                val context = LocalContext.current
                Column(modifier = Modifier.verticalScroll(scrollState)) {

                    Column() {
                        Row(modifier = Modifier.padding(start = 10.dp, end = 10.dp)) {
                            Text(
                                text = title.toString(),
                                color = Color.Black,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Log.d("in column",name.toString())
                        }
                        Spacer(modifier = Modifier.height(13.dp))

                        Row(modifier = Modifier.padding(start = 10.dp, end = 10.dp)) {
                            Text(text = desc.toString(), color = Color.Black, fontSize = 20.sp)
                        }

                        Spacer(modifier = Modifier.height(13.dp))
                        Row(modifier = Modifier.padding(start = 10.dp, end = 10.dp)) {
                            Text(
                                text = "Funds to raise: Rs ",
                                color = Color.Black,
                                fontSize = 20.sp
                            )
                            Text(
                                text = amount.toString(),
                                color = Color.Black,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Spacer(modifier = Modifier.height(15.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                        )
                        {
                            Button(
                                onClick = {
                                    val intent = Intent(context, DonateActivity::class.java)
                                    context.startActivity(intent)
                                },
                                enabled = true,
                                border = BorderStroke(
                                    width = 1.dp,
                                    brush = SolidColor(Color.Black),
                                ),
                                shape = RectangleShape,
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue),
                                modifier = Modifier
                                    .height(50.dp)
                                    .width(150.dp)
                                    .padding(5.dp)
                            ) {
                                Icon(painter = painterResource(id = R.drawable.love), contentDescription ="love", tint = Color.White)
                                Spacer(modifier = Modifier.width(5.dp))
                                Text(text = "Donate", color = Color.White)
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Button(
                                onClick = {
                                    val type = "text/plain"
                                    val subject = "Your subject"
                                    val extraText = age.toString()
                                    val shareWith = "ShareWith"

                                    val intent = Intent(Intent.ACTION_SEND)
                                    intent.type = type
                                    intent.putExtra(Intent.EXTRA_SUBJECT, subject)
                                    intent.putExtra(Intent.EXTRA_TEXT, extraText)

                                    ContextCompat.startActivity(
                                        context,
                                        Intent.createChooser(intent, shareWith),
                                        null
                                    )
                                },
                                enabled = true,
                                border = BorderStroke(
                                    width = 1.dp,
                                    brush = SolidColor(Color.Black)
                                ),
                                shape = RectangleShape,
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue),
                                modifier = Modifier
                                    .height(50.dp)
                                    .width(150.dp)
                                    .padding(5.dp)
                            ) {
                                Icon(painter = painterResource(id = R.drawable.share), contentDescription ="share", tint = Color.White)
                                Spacer(modifier = Modifier.width(5.dp))
                                Text(text = "Share", color = Color.White)
                            }
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
        }
    }
}