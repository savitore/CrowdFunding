package com.example.crowdfunding

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.SemanticsProperties.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import org.json.JSONException
import org.json.JSONObject

class DonateActivity: ComponentActivity(),PaymentResultListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Donate()
        }
    }

    @Composable
    fun Donate()
    {
        Scaffold(
            topBar = {
                TopBar2()
            },
            bottomBar = {
            },
            backgroundColor = Color.LightGray,
        )
        {padding->
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
                )
                {
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(modifier = Modifier.padding(start = 10.dp, end = 10.dp))
                    {
                        Text(text = "Every penny counts! Please donate as much as you can.", color = Color.Black, fontSize = 20.sp)
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    var text by remember { mutableStateOf("") }
                    OutlinedTextField(
                        value = text,
                        onValueChange = { newText ->
                            text = newText
                            if(newText.equals("0") ||newText.equals("00") || newText.equals("000"))
                            {
                                Toast.makeText(baseContext,"Min value should be Rs 1",Toast.LENGTH_SHORT).show()
                            }
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
//                        imeAction = ImeAction.
                        ),
                        label = { Text(text = "Enter amount to donate") },
                        maxLines = 1,
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.White,
                            focusedIndicatorColor = Color.Blue,
                            unfocusedIndicatorColor = Color.Black,
                            focusedLabelColor = Color.Blue,
                            textColor = Color.Black
                        ),
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.rupee),
                                contentDescription = "rupee"
                            )
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(horizontalArrangement=Arrangement.Start, verticalAlignment=Alignment.CenterVertically)
                    {
                        Button(
                            onClick = {
                                if(text.toInt()>0) {
                                    val amount = Math.round(text.toFloat() * 100)
                                    val checkout = Checkout()
                                    checkout.setKeyID("rzp_live_7A2cLQLDxsT0Is")
                                    checkout.setImage(R.drawable.logo)
                                    val obj = JSONObject()
                                    try {
                                        // to put name
                                        obj.put("name", "CrowdFunding")

                                        // put description
                                        obj.put("description", "Donate")

                                        // to set theme color
                                        obj.put("theme.color", "")

                                        // put the currency
                                        obj.put("currency", "INR")

                                        // put amount
                                        obj.put("amount", amount)

                                        // put mobile number
                                        obj.put("prefill.contact", "9406380105")

                                        // put email
                                        obj.put("prefill.email", "21je0482@iitism.ac.in")

                                        // open razorpay to checkout activity
                                        checkout.open(this@DonateActivity, obj)
                                    } catch (e: JSONException) {
                                        e.printStackTrace()
                                    }
                                }
                                      },
                            enabled = true,
                            border = BorderStroke(width = 1.dp, brush = SolidColor(Color.Blue)),
                            shape = MaterialTheme.shapes.medium,
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color.Blue,
                                contentColor = Color.White
                            )
                        ) {
                            Text(text = "Pay")
                        }
                    }
                }
            }
        }
    }

    override fun onPaymentSuccess(p0: String?) {

        Toast.makeText(this, "Payment is successful" , Toast.LENGTH_SHORT).show();
        val intent=Intent(baseContext,MainActivity2::class.java)
        startActivity(intent)
    }

    override fun onPaymentError(p0: Int, p1: String?) {
        Toast.makeText(this, "Payment Failed due to error", Toast.LENGTH_SHORT).show();
    }
    }
