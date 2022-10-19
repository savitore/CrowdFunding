package com.example.crowdfunding

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.example.crowdfunding.cloudFirestore.UserViewModel
import com.example.crowdfunding.ui.theme.PostWidget
import com.google.firebase.ktx.Firebase

class AddPatientsActivity: ComponentActivity() {
    private lateinit var userViewModel: UserViewModel
    private lateinit var list: ArrayList<User>
    private var selected: User = User()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
            AddPatients()
            initViewModel()


        }
    }

    @Composable
    fun DetailAmount(fieldValue: MutableState<TextFieldValue>) {
        OutlinedTextField(
            value = fieldValue.value,
            onValueChange = { newText ->
                fieldValue.value = newText
            },
            keyboardOptions = KeyboardOptions
                (
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            label = { Text(text = "How much do you want to raise?") },
            maxLines = 1,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                focusedIndicatorColor = Color.Blue,
                unfocusedIndicatorColor = Color.Black,
                focusedLabelColor = Color.Blue
            ),
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.rupee),
                    contentDescription = "rupee"
                )
            }
        )
    }

    @Composable
    fun DetailPatientdesc(fieldValue: MutableState<TextFieldValue>) {
        OutlinedTextField(
            value = fieldValue.value,
            onValueChange = { newText ->
                fieldValue.value = newText
            },
            keyboardOptions = KeyboardOptions
                (
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            label = { Text(text = "Enter details about the patient") },
            maxLines = 8,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                focusedIndicatorColor = Color.Blue,
                unfocusedIndicatorColor = Color.Black,
                focusedLabelColor = Color.Blue
            ),
            modifier = Modifier.fillMaxWidth()
        )

    }

    @Composable
    fun DetailPatientAge(fieldValue: MutableState<TextFieldValue>) {
        var isError1 by rememberSaveable { mutableStateOf(false) }
        OutlinedTextField(
            value = fieldValue.value,
            onValueChange = { newText ->
                fieldValue.value = newText
                isError1=false
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
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            label = { Text(text = "Patient's age") },
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
                text = "Age cannot be empty",
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(start = 14.dp)
            )
        }
    }

    @Composable
    fun DetailTitle(fieldValue: MutableState<TextFieldValue>) {
        OutlinedTextField(
            value = fieldValue.value,
            onValueChange = { newText ->
                fieldValue.value = newText
            },
            keyboardOptions = KeyboardOptions
                (
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            label = { Text(text = "Patient's full name") },
            maxLines = 1,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                focusedIndicatorColor = Color.Blue,
                unfocusedIndicatorColor = Color.Black,
                focusedLabelColor = Color.Blue
            ),
            modifier = Modifier.fillMaxWidth()
        )

    }

    @Composable
    fun AddPatients() {
        val text1 = remember { mutableStateOf(TextFieldValue()) }
        val text2 = remember { mutableStateOf(TextFieldValue()) }
        val text3 = remember { mutableStateOf(TextFieldValue()) }
        val text4 = remember { mutableStateOf(TextFieldValue()) }
        val context= LocalContext.current

        PostWidget(
            user = User(
                text1.value.text,
                text2.value.toString(),
                text3.value.text,
                text4.value.text.toString()
            )
        ) {}
        Scaffold(
            topBar = {
                TopBar1()
            },
            bottomBar = {
                BottomAppBar(backgroundColor = Color.Blue) {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                            .clickable { val user = User(
                                selected.id.toString(),
                                text1.toString(),
                                text2.toString(),
                                text3.toString(),
                                text4.toString()
                            )
                                userViewModel.create(user)
                                userViewModel.getList()
                                Toast.makeText(context,"Fundraiser created successfully",Toast.LENGTH_SHORT).show()
                                       val intent=Intent(context,MainActivity::class.java)
                                       context.startActivity(intent)},
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        Text(
                            text = "Submit",
                            color = Color.White,
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            },
            backgroundColor = Color.Black,
        ) { padding->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(10.dp),
                shape = RoundedCornerShape(16.dp),
                backgroundColor = Color.White
            )
            {
                val scrollState = rememberScrollState()
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth(), horizontalArrangement = Arrangement.Center
                    )
                    {
                        Text(
                            text = "Tell us about the patient",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                        )
                    }
                    Divider()
                    Spacer(modifier = Modifier.height(10.dp))
                    DetailTitle(text1)
                    Spacer(modifier = Modifier.height(10.dp))
                    DetailPatientAge(text2)
//                    var text2 by remember { mutableStateOf(TextFieldValue()) }
//                    OutlinedTextField(
//                        value = text2,
//                        onValueChange = { newText ->
//                            text2 = newText
//                        },
//                        keyboardOptions = KeyboardOptions
//                            (
//                            keyboardType = KeyboardType.Number,
//                            imeAction = ImeAction.Next
//                        ),
//                        label = { Text(text = "Patient's age") },
//                        maxLines = 1,
//                        colors = TextFieldDefaults.textFieldColors(
//                            backgroundColor = Color.White,
//                            focusedIndicatorColor = Color.Blue,
//                            unfocusedIndicatorColor = Color.Black,
//                            focusedLabelColor = Color.Blue
//                        ),
//                        modifier = Modifier.fillMaxWidth()
//                    )

                    Spacer(modifier = Modifier.height(10.dp))
                    DetailPatientdesc(text3)
//                    var text3 by remember { mutableStateOf(TextFieldValue()) }
//                    OutlinedTextField(
//                        value = text3,
//                        onValueChange = { newText ->
//                            text3 = newText
//                        },
//                        keyboardOptions = KeyboardOptions
//                            (
//                            keyboardType = KeyboardType.Text,
//                            imeAction = ImeAction.Next
//                        ),
//                        label = { Text(text = "Enter details about the patient") },
//                        maxLines = 8,
//                        colors = TextFieldDefaults.textFieldColors(
//                            backgroundColor = Color.White,
//                            focusedIndicatorColor = Color.Blue,
//                            unfocusedIndicatorColor = Color.Black,
//                            focusedLabelColor = Color.Blue
//                        ),
//                        modifier = Modifier.fillMaxWidth()
//                    )

                    Spacer(modifier = Modifier.height(10.dp))
//                    var text4 by remember { mutableStateOf(TextFieldValue()) }
                    DetailAmount(text4)
//                    OutlinedTextField(
//                        value = text4,
//                        onValueChange = { newText ->
//                            text4 = newText
//                        },
//                        keyboardOptions = KeyboardOptions
//                            (
//                            keyboardType = KeyboardType.Number,
//                            imeAction = ImeAction.Next
//                        ),
//                        label = { Text(text = "How much do you want to raise?") },
//                        maxLines = 1,
//                        colors = TextFieldDefaults.textFieldColors(
//                            backgroundColor = Color.White,
//                            focusedIndicatorColor = Color.Blue,
//                            unfocusedIndicatorColor = Color.Black,
//                            focusedLabelColor = Color.Blue
//                        ),
//                        modifier = Modifier.fillMaxWidth(),
//                        leadingIcon = {
//                            Icon(
//                                painter = painterResource(id = R.drawable.rupee),
//                                contentDescription = "rupee"
//                            )
//                        }
//                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Box(
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth()
                            .border(
                                brush = SolidColor(Color.Black),
                                width = 1.dp,
                                shape = RectangleShape
                            )
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Add image of patient",
                                color = Color.Black,
                                fontSize = 18.sp,
                                modifier = Modifier.padding(7.dp)
                            )
                            Spacer(modifier = Modifier.width(2.dp))
                            Button(
                                onClick = {
//                                    val user = User(
//                                        selected.id.toString(),
//                                        text1.toString(),
//                                        text2.toString(),
//                                        text3.toString(),
//                                        text4.toString(),
//                                    )
//                                    userViewModel.create(user)
//                                    userViewModel.getList()
                                },
                                enabled = true,
                                border = BorderStroke(width = 1.dp, brush = SolidColor(Color.Blue)),
                                shape = MaterialTheme.shapes.medium,
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = Color.Transparent,
                                    contentColor = Color.Blue
                                )
                            ) {
                                Text(text = "Upload")
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Image(
                        modifier = Modifier.height(250.dp),
                        painter = painterResource(id = R.drawable.pic1),
                        contentDescription = "upload",
                        contentScale = ContentScale.FillWidth
                    )
                }
                //card
            }//fun
        }



    }
    fun initViewModel() {
        userViewModel.createLiveData.observe(this, {bool->
            onCreate(bool)
        })

        userViewModel.updateLiveData.observe(this, {bool->
            onUpdate(bool)
        })
        userViewModel.deleteLiveData.observe(this, {bool->
            onDelete(bool)
        })

        userViewModel.getListLiveData.observe(this, {lists->
            onGetList(lists)
        })
    }

    fun onGetList(it: List<User>?) {
        list = ArrayList()
        list.addAll(it!!)
    }

    fun onDelete(it: Boolean?) {
        if (it == true) {
            userViewModel.getList()
            resetText()
        }
    }

    fun resetText() {
        selected = User()


    }

    fun onCreate(it: Boolean?) {
        if (it == true) {
            userViewModel.getList()
            resetText()
        }

    }

    private fun onUpdate(it: Boolean?) {
        if (it == true) {
            userViewModel.getList()
            resetText()
        }

    }
}