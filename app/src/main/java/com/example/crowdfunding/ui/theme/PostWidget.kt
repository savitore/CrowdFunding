package com.example.crowdfunding.ui.theme

import android.content.Intent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.crowdfunding.AddPatientsActivity
import com.example.crowdfunding.MainActivity
import com.example.crowdfunding.R
import com.example.crowdfunding.User

@Composable
fun PostWidget(
    user: User,
    navigateToExpand: () -> Unit,
) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .clickable { navigateToExpand() },
            shape = RoundedCornerShape(16.dp),
            backgroundColor = Color.LightGray
        )
        {
            Column() {

            Box(
                modifier = Modifier.padding(0.dp),
                contentAlignment = Alignment.TopCenter
            )
            {
        val selectedTabIndex by remember { mutableStateOf(0) }
        ScrollableTabRow(
            selectedTabIndex = selectedTabIndex,
            backgroundColor = Color.Transparent, contentColor = Color.White,
            edgePadding = 0.dp, modifier = Modifier.height(250.dp)
        ) {
            for (i in 0..user.pic.size - 1) {
                Image(
                    modifier = Modifier.height(250.dp),
                    painter = painterResource(id = user.pic[i]),
                    contentDescription = "pic",
                    contentScale = ContentScale.FillWidth
                )
            }
        }
            }
            Column() {
                Row(modifier = Modifier.padding(start = 10.dp,end=10.dp)) {
                    Text(text = user.text, color = Color.Black, fontSize = 20.sp)
                }

                Spacer(modifier = Modifier.height(13.dp))
                Row(modifier = Modifier.padding(start = 10.dp,end=10.dp)) {
                    Text(text = "Funds to raise: Rs ", color = Color.Black, fontSize = 20.sp)
                    Text(text = user.amount.toString(), color = Color.Black, fontSize = 20.sp,fontWeight = FontWeight.Bold)
                }

            }
    }
    }//card
}

