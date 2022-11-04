package com.example.crowdfunding



import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quote")
data class UserRoom(
    @PrimaryKey(autoGenerate=true) val id:Int,
    @ColumnInfo(name = "first_name") val name:String,
    @ColumnInfo(name = "title") val title:String,
    @ColumnInfo(name = "funds") val funds:Int
)
