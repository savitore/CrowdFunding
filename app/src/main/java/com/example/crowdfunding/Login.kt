package com.example.crowdfunding

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.crowdfunding.ui.theme.CrowdFundingTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : ComponentActivity() {

    companion object{
        val TAG : String=LoginActivity::class.java.simpleName
    }
    private val auth by lazy{
        Firebase.auth
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                LoginScreen(auth)
//
        }
    }
}
@Composable
fun LoginScreen(auth: FirebaseAuth){

    val focusManager= LocalFocusManager.current
    val context= LocalContext.current

    var email by remember{
        mutableStateOf("")
    }

    var Password by remember{
        mutableStateOf("")
    }

    val isEmailvalid  by derivedStateOf {
        Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    val isPasswordvalid by derivedStateOf {
        Password.length>7
    }
    var isPasswordremember by remember {
        mutableStateOf(false)
    }
    Scaffold(
        topBar = {
        },
        bottomBar = {
        },
        backgroundColor = Color.LightGray,
    )
    { padding->
        Card(
            modifier = Modifier
                .padding(10.dp),
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(1.dp, Color.Black)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(all = 10.dp)
            ) {
                Row(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(), horizontalArrangement = Arrangement.Center
                )
                {
                    Text(
                        text = "Login to create a new Fund Raiser",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                    )
                }
                Divider()
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(value = email,
                    onValueChange = { newText ->
                        email = newText
                    },
                    label = { Text("Email Address") },
                    placeholder = { Text("abc@gmail.com") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.White,
                        focusedIndicatorColor = Color.Blue,
                        unfocusedIndicatorColor = Color.Black,
                        focusedLabelColor = Color.Blue
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Down) }
                    ),
                    isError = !isEmailvalid,
                    trailingIcon = {
                        if (email.isNotBlank()) {
                            IconButton(onClick = { email = "" }) {
                                Icon(
                                    imageVector = Icons.Filled.Clear,
                                    contentDescription = "Clear Email"
                                )
                            }
                        }
                    }
                )

                OutlinedTextField(value = Password,
                    onValueChange = { newText ->
                        Password = newText
                    },
                    label = { Text("Password!!") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.White,
                        focusedIndicatorColor = Color.Blue,
                        unfocusedIndicatorColor = Color.Black,
                        focusedLabelColor = Color.Blue
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = { focusManager.clearFocus() }
                    ),
                    isError = !isPasswordvalid,
                    trailingIcon = {
                        IconButton(onClick = { isPasswordremember = !isPasswordremember }) {
                            Icon(
                                imageVector = if (isPasswordremember) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                contentDescription = "Toggle password visibility"
                            )
                        }
                    },
                    visualTransformation = if (isPasswordremember) VisualTransformation.None else PasswordVisualTransformation()
                )
                Spacer(modifier = Modifier.height(10.dp))
                Button(
                    onClick = {
                        auth.signInWithEmailAndPassword(email, Password).addOnCompleteListener {
                            if (it.isSuccessful) {
                                Log.d(TAG, "The user has successfully logged in")

                                val intent = Intent(context, AddPatientsActivity::class.java)
                                context.startActivity(intent)
                            } else {
                                Log.w(TAG, "The user has FAILED to log in", it.exception)
                            }
                        }
                    },
//                    border = BorderStroke(width = 1.dp, brush = SolidColor(Color.Blue)),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Transparent,
                        contentColor = Color.Blue
                    ),
                    enabled = isEmailvalid && isPasswordvalid,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Log in",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        fontSize = 16.sp,
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Not a user? Register now",
                    fontSize = 16.sp,
                    modifier = Modifier.clickable {
                        val intent = Intent(context, RegisterActivity::class.java)
                        context.startActivity(intent)
                    }
                )
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
        LoginScreen(Firebase.auth)
}