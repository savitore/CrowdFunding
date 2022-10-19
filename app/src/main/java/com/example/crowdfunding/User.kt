package com.example.crowdfunding

import androidx.annotation.DrawableRes
import com.google.firebase.database.Exclude

//data class User(
//    @DrawableRes val pic: Array<Int>,
//    val text: String,
//    val amount: Long,
//    val desc: String
//)
data class User(
    var id: String? =null,
    var name:String?=null,
    var age:String?=null,
    var desc:String?=null,
    var amount: String?=null,
//    @DrawableRes
//    val pic: Int

){
    @Exclude
    fun toMap():Map<String,Any?>{
        return mapOf(
            "id" to id,
            "name" to name,
            "age" to age,
            "desc" to desc,
            "amount" to amount

        )
    }
}