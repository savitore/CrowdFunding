package com.example.crowdfunding.cloudFirestore

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.crowdfunding.User
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class UserViewModel:ViewModel() {
    private var db = Firebase.firestore
    private val users = "users"

    val createLiveData: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

        val updateLiveData: MutableLiveData<Boolean> by lazy{
        MutableLiveData<Boolean>()
    }

    val getListLiveData: MutableLiveData<List<User>> by lazy {
        Log.d("getListLiveData","getListLiveDataFunction decleration")
        MutableLiveData<List<User>>()
    }

    val deleteLiveData: MutableLiveData<Boolean> by lazy{
        MutableLiveData<Boolean>()
    }

    fun create(user: User) {
        val docRef = db.collection(users)
        docRef.add(user).addOnSuccessListener {
            Log.d("Users","Users created/added")
            createLiveData.postValue(true)
        }.addOnFailureListener {
            Log.d("Users", it.localizedMessage!!)
            createLiveData.postValue(false)
        }
    }
    fun update(user: User){
        val docRef=db.collection(users)
        docRef.document(user.amount!!).update(user.toMap()).addOnSuccessListener {
            updateLiveData.postValue(true)
        }.addOnFailureListener {
            Log.d("update",it.localizedMessage!!)
            updateLiveData.postValue(false)
        }

    }

    fun delete(id:String){
        val docRef=db.collection(users)
        docRef.document(id).delete().addOnSuccessListener {
            deleteLiveData.postValue(true)

        }.addOnFailureListener {
            Log.d("delete",it.localizedMessage!!)
            deleteLiveData.postValue(false)
        }
    }

    fun getList() {
        val docRef=db.collection(users)
        docRef.get().addOnSuccessListener {
            val usersList=ArrayList<User>()
            for(item in it.documents){
                Log.d("get","Getting the list from cloud")
                val user=User()
                user.id=item.data!!["id"] as String?
                Log.d("this",user.id.toString())
                user.name= item.data!!["name"] as String?
                user.age=item.data!!["age"] as String?
                user.amount= item.data!!["amount"] as String?
                user.desc= item.data!!["description"] as String?
                usersList.add(user)
            }

            Log.d("Outside the for loop","calling getListLIvedata")
            getListLiveData.value=usersList
        }
            .addOnFailureListener {
                Log.d("get",it.localizedMessage!!)
                getListLiveData.postValue(null)
            }
    }

}
//        return  usersList


