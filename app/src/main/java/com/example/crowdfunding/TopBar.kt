package com.example.crowdfunding

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun TopBar(){
    val context = LocalContext.current
    TopAppBar(
        backgroundColor = Color.White,
        actions = {
            Row(
                Modifier
//                    .fillMaxWidth()
                    .padding(0.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween) {
                IconButton(onClick = { }) {
                    Icon(
                        painter = painterResource(id = R.drawable.back),
                        contentDescription = "back",
                        modifier = Modifier.padding(start = 0.dp)
                            .clickable {
                                val intent = Intent(context, MainActivity::class.java)
                                context.startActivity(intent)
                            }
                    )

                }
//                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    modifier = Modifier.weight(8f),
                    text = "Know more",
                    textAlign = TextAlign.Start,
                    color = Color.DarkGray,
                    fontSize = 20.sp
                )
            }

        },
        title = {
        }
    )
}

@Preview(showBackground = true)
@Composable
fun TopBarPreview()
{
    TopBar()
}