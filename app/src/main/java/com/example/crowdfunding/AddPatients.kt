package com.example.crowdfunding

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri

import android.os.Bundle
import android.provider.ContactsContract.Data
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
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.selects.select
import java.text.SimpleDateFormat
import java.util.*

class AddPatientsActivity: ComponentActivity() {
    val db = Firebase.firestore

    val formatter = SimpleDateFormat("yyyy_MM_dd_mm", Locale.getDefault())
    val now = Date()
    val filename = formatter.format(now)
    lateinit var ImageUri: Uri
    private var selected: User = User()
    private lateinit var userViewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
            AddPatients()
        }
    }

    @Composable
    fun AddPatients() {
        val pic:String
        val storageReference=Firebase.storage.reference.child("image/").path
        Scaffold(
            topBar = {
                TopBar1()
            },
//            bottomBar = {
//                BottomAppBar(backgroundColor = Color.Blue) {
//                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center,) {
//                        Text(
//                            text = "Submit",
//                            color = Color.White,
//                            fontSize = 20.sp,
//                            textAlign = TextAlign.Center,
//                            modifier = Modifier.clickable {
//                            }
//                        )
//                    }
//                }
//            },
            backgroundColor = Color.Black,
        )
        { padding ->
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
                    if (isError1) {
                        Text(
                            text = "Name cannot be empty",
                            color = MaterialTheme.colors.error,
                            style = MaterialTheme.typography.caption,
                            modifier = Modifier.padding(start = 14.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    var text2 by remember { mutableStateOf("") }
                    var isError2 by rememberSaveable { mutableStateOf(false) }
                    OutlinedTextField(
                        value = text2,
                        onValueChange = { newText ->
                            text2 = newText
                            isError2 = false
                        },
                        trailingIcon = {
                            if (isError2) {
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
                    if (isError2) {
                        Text(
                            text = "Please enter age of patient",
                            color = MaterialTheme.colors.error,
                            style = MaterialTheme.typography.caption,
                            modifier = Modifier.padding(start = 14.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    var text5 by remember { mutableStateOf("") }
                    var isError5 by rememberSaveable { mutableStateOf(false) }
                    OutlinedTextField(
                        value = text5,
                        onValueChange = { newText ->
                            text5 = newText
                            isError5 = false
                        },
                        trailingIcon = {
                            if (isError5) {
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
                            imeAction = ImeAction.Next
                        ),
                        label = { Text(text = "Enter title") },
                        maxLines = 1,
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.White,
                            focusedIndicatorColor = Color.Blue,
                            unfocusedIndicatorColor = Color.Black,
                            focusedLabelColor = Color.Blue
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                    if (isError5) {
                        Text(
                            text = "Title cannot be empty",
                            color = MaterialTheme.colors.error,
                            style = MaterialTheme.typography.caption,
                            modifier = Modifier.padding(start = 14.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    var text3 by remember { mutableStateOf("") }
                    var isError3 by rememberSaveable { mutableStateOf(false) }
                    OutlinedTextField(
                        value = text3,
                        onValueChange = { newText ->
                            text3 = newText
                            isError3 = false
                        },
                        trailingIcon = {
                            if (isError3) {
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
                    if (isError3) {
                        Text(
                            text = "Please enter details",
                            color = MaterialTheme.colors.error,
                            style = MaterialTheme.typography.caption,
                            modifier = Modifier.padding(start = 4.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    var text4 by remember { mutableStateOf("") }
                    var isError4 by rememberSaveable { mutableStateOf(false) }
                    OutlinedTextField(
                        value = text4,
                        onValueChange = { newText ->
                            text4 = newText
                            isError4 = false
                        },
                        trailingIcon = {
                            if (isError4) {
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
                    val id = db.collection("users").document().id
//                    val pic
//                    val storage=FirebaseStorage.getInstance().reference.child("images/")
                    val storage=Firebase.storage.reference.child("images/").path
                    Log.d("storage",storage)
                    val user = User(id, text1, text2, text4, text3, text4)
                    if (isError4) {
                        Text(
                            text = "Please enter the amount",
                            color = MaterialTheme.colors.error,
                            style = MaterialTheme.typography.caption,
                            modifier = Modifier.padding(start = 4.dp)
                        )
                    }
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
                                    selectImage()

                                },
                                enabled = true,
                                border = BorderStroke(width = 1.dp, brush = SolidColor(Color.Blue)),
                                shape = MaterialTheme.shapes.medium,
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = Color.Transparent,
                                    contentColor = Color.Blue
                                ),
                                modifier = Modifier.height(10.dp).width(30.dp)
                            ) {
                                Text(text = "Select Image")
                            }
                            Spacer(modifier = Modifier.width(2.dp))
                            Button(
                                onClick = {
                                    uploadImage()

                                },
                                enabled = true,
                                border = BorderStroke(width = 1.dp, brush = SolidColor(Color.Blue)),
                                shape = MaterialTheme.shapes.medium,
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = Color.Transparent,
                                    contentColor = Color.Blue
                                ),
                                modifier = Modifier.height(10.dp).width(30.dp)
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
                    Spacer(modifier = Modifier.height(10.dp))
                    Button(
                        onClick = {
                            userViewModel.getList()
                            if (db.collection("user") !== null) {
                                userViewModel.getList()
                            }
                            userViewModel.create(user)
                            startActivity(Intent(baseContext, MainActivity2::class.java))

                        },
                        enabled = true,
                        border = BorderStroke(width = 1.dp, brush = SolidColor(Color.Blue)),
                        shape = MaterialTheme.shapes.medium,
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.Transparent,
                            contentColor = Color.Blue
                        )
                    ) {
                        Text(text = "Submit")


                    }
                }//card
            }//scaffold
        }

    }

    private fun uploadImage() {
        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Uploading file")
        progressDialog.setCancelable(false)
        progressDialog.show()

//        val formatter=SimpleDateFormat("yyyy_MM_dd_mm", Locale.getDefault())
//        val now=Date()
//        val filename=formatter.format(now)

        val storageReference = FirebaseStorage.getInstance().getReference("images/$filename")
        storageReference.child("images/$filename").downloadUrl.addOnSuccessListener { it ->
//            pic = it.toString()
        }
        storageReference.putFile(ImageUri).addOnSuccessListener {
            Toast.makeText(this, "Successfully uploaded", Toast.LENGTH_SHORT).show()
            if (progressDialog.isShowing) {
                progressDialog.dismiss()
            }
        }
            .addOnFailureListener {
                if (progressDialog.isShowing) progressDialog.dismiss()
                Toast.makeText(this, "failed to upload Image", Toast.LENGTH_SHORT).show()

            }


    }

    private fun selectImage() {
        val intent = Intent()
        intent.type = "images/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, 100)


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        ImageUri = data?.data!!

    }


    @Preview(showBackground = true)
    @Composable
    fun Preview1() {
        AddPatientsActivity()
    }
}