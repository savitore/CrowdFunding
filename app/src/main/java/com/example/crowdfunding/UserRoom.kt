package com.example.crowdfunding



import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quote")
data class UserRoom(
    @PrimaryKey(autoGenerate=true)
    val id:Int,
    val name:String,
    val title:String,
    val funds:Int
)
