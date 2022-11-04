package com.example.crowdfunding



import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [UserRoom::class],
    version=1
)
abstract class UserDatabase : RoomDatabase() {
    abstract fun  getDao():Dao

    companion object{
        private var INSTANCE:UserDatabase?=null
        fun getDatabase(context: Context):UserDatabase{
            if(INSTANCE==null){
                synchronized(this){
                    INSTANCE= Room.databaseBuilder(context,UserDatabase::class.java,"quote-database").build()
                }
            }
            return INSTANCE!!
        }
    }
}