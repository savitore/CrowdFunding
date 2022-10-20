package com.example.crowdfunding

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.crowdfunding.cloudFirestore.Adapter

import com.example.crowdfunding.cloudFirestore.UserViewModel

class MainActivity : ComponentActivity() {
//    private lateinit var userViewModel: UserViewModel
//    private lateinit var adapterUser:Adapter
//    private lateinit var rv:RecyclerView
    private lateinit var list: ArrayList<User>
    private var selected: User = User()
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

//    val users= listOf(
//        User(
////            pic = arrayOf(R.drawable.pic2),
////            name = "My Little Boy’s Cancer Has Relapsed Twice But I’m Helpless. Please Save Him",
//            name = ,
//            amount = 50000000,
//            desc = "What goes on in a mother’s mind when any time she visits the hospital with her son, she is informed that his cancer has relapsed?\n" +
//                    "Having faced that situation thrice, I pray to God none of the parents ever have the ill fortune to witness their own child suffering from cancer from time and again.\n" +
//                    "\n" +
//                    "My family's happiness lies in Abir. Him being the youngest member of my family of four, all of us are accustomed to his joyous and playful. When he became extremely sick the first time, he was scared and sad. Yet he tried to cheer all of us up as much as he could."
//        ),
//        User(
////            pic = arrayOf(R.drawable.pic2,R.drawable.pic1),
//            text = "My Young 30 Year Old Husband Is In Urgent Need Of Heart Transplant, Please Help Us Save Him And Get Treated",
//            amount = 3030000,
//            desc = "What goes on in a mother’s mind when any time she visits the hospital with her son, she is informed that his cancer has relapsed?\n" +
//                    "Having faced that situation thrice, I pray to God none of the parents ever have the ill fortune to witness their own child suffering from cancer from time and again.\n" +
//                    "\n" +
//                    "My family's happiness lies in Abir. Him being the youngest member of my family of four, all of us are accustomed to his joyous and playful. When he became extremely sick the first time, he was scared and sad. Yet he tried to cheer all of us up as much as he could."
//        ),
//        User(
//            pic = arrayOf(R.drawable.pic2,R.drawable.pic1),
//            text = "My Young 30 Year Old Husband Is In Urgent Need Of Heart Transplant, Please Help Us Save Him And Get Treated",
//            amount = 48656515,
//            desc = "What goes on in a mother’s mind when any time she visits the hospital with her son, she is informed that his cancer has relapsed?\n" +
//                    "Having faced that situation thrice, I pray to God none of the parents ever have the ill fortune to witness their own child suffering from cancer from time and again.\n" +
//                    "\n" +
//                    "My family's happiness lies in Abir. Him being the youngest member of my family of four, all of us are accustomed to his joyous and playful. When he became extremely sick the first time, he was scared and sad. Yet he tried to cheer all of us up as much as he could."
//        )
//    )
    Scaffold(
        topBar = {},
        bottomBar = {},
        backgroundColor = Color.LightGray,
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding)
        ){
//            items{
//                ()
////                                PostWidget(user = user){
////                    val userString = Gson().toJson(user)
////                    val intent = Intent(activity, PostExpandableActivity::class.java)
////                    intent.putExtra("user", userString)
////                    activity.startActivity(intent)
////                }
//                Text(text = user.value)
////                Toast.makeText(context,"Hi",Toast.LENGTH_SHORT).show()
////                Log.d("AMAN",users.toString())
////                UserListItem(user = users)
//
//                }
            }
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(20.dp), verticalArrangement = Arrangement.Bottom, horizontalAlignment = Alignment.End) {
            val context = LocalContext.current
            FloatingActionButton(
                onClick = {
                    val intent = Intent(context, LoginActivity::class.java)
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

@Composable
fun UserListItem(user: User) {
    Card(
        modifier = Modifier.padding(end = 8.dp),
        shape = RoundedCornerShape(4.dp),
        elevation = 2.dp
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = user.name.toString())
            Spacer(modifier = Modifier.padding(8.dp))
            Text(text = user.desc.toString())
            Spacer(modifier = Modifier.padding(8.dp))
            Text(text = user.amount.toString())
        }
    }
}





