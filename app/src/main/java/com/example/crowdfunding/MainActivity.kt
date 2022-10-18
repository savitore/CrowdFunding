package com.example.crowdfunding

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.crowdfunding.ui.theme.PostWidget
import com.google.gson.Gson

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomePage()
        }
    }
}

@Composable
private fun HomePage() {
    val context = LocalContext.current
    val activity = remember { context as MainActivity }
    val users= listOf(
        User(
            pic = arrayOf(R.drawable.pic2),
            text = "My Little Boy’s Cancer Has Relapsed Twice But I’m Helpless. Please Save Him",
            amount = 50000000,
            desc = ""
        ),
        User(
            pic = arrayOf(R.drawable.pic2,R.drawable.pic1),
            text = "My Young 30 Year Old Husband Is In Urgent Need Of Heart Transplant, Please Help Us Save Him And Get Treated",
            amount = 3030000,
            desc = ""
        ),
        User(
            pic = arrayOf(R.drawable.pic2,R.drawable.pic1),
            text = "My Young 30 Year Old Husband Is In Urgent Need Of Heart Transplant, Please Help Us Save Him And Get Treated",
            amount = 3030000,
            desc = ""
        )
    )
    Scaffold(
        topBar = {},
        bottomBar = {},
        backgroundColor = Color.White,
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding)
        ){
            items(users){ user ->
                PostWidget(user = user){
                    val userString = Gson().toJson(user)
                    val intent = Intent(activity, PostExpandableActivity::class.java)
                    intent.putExtra("user", userString)
                    activity.startActivity(intent)
                }
                }
            }
        Column(modifier = Modifier.fillMaxSize().padding(20.dp), verticalArrangement = Arrangement.Bottom, horizontalAlignment = Alignment.End) {
            val context = LocalContext.current
            FloatingActionButton(
                onClick = {
                    val intent = Intent(context, AddPatientsActivity::class.java)
                    context.startActivity(intent)
                },
                backgroundColor = Color.Blue,
                contentColor = Color.White
            )
            {
                Icon(Icons.Filled.Add, "add")
            }
        }
        }
    }




