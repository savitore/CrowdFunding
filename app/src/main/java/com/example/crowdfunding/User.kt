package com.example.crowdfunding

import android.icu.util.LocaleData
import android.net.Uri
import androidx.annotation.DrawableRes
import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties
import java.text.DateFormat
import java.util.Locale

//data class User(
//    @DrawableRes val pic: Array<Int>,
//    val text: String,
//    val amount: Long,
//    val desc: String
//)
@IgnoreExtraProperties
data class User(
    var id:String?=null,
    var name:String?=null,
    var age:String?=null,
    var title:String?=null,
    var desc:String?=null,
    var amount: String?=null,
//    var pic:String?=null
    //    @DrawableRes
//    val pic: Int

){
    @Exclude
    fun toMap():Map<String,Any?>{
        return mapOf(
            "id" to id,
            "name" to name,
            "age" to age,
            "title" to title,
            "desc" to desc,
            "amount" to amount

        )
    }
}