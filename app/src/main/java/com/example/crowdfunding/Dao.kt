package com.example.crowdfunding


import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.Query

interface Dao
{
    @Query("SELECT*FROM quote")
    fun getData():LiveData<List<User>>
    @Insert
    suspend fun insertQuote(quote:User)
}