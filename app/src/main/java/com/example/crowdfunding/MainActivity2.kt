package com.example.crowdfunding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.crowdfunding.cloudFirestore.Adapter
import com.example.crowdfunding.cloudFirestore.UserViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity2 : AppCompatActivity(),Adapter.OnItemClickListener {
    private lateinit var userViewModel: UserViewModel
    private lateinit var adapterUser: Adapter
    private lateinit var rv: RecyclerView
    private lateinit var list: ArrayList<User>
    private var selected: User = User()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rv=findViewById(R.id.recyclerView)
        val float:FloatingActionButton=findViewById(R.id.floatingActionButton)
        float.setOnClickListener{
            startActivity(Intent(this,LoginActivity::class.java))
        }
        userViewModel=ViewModelProvider(this).get(UserViewModel::class.java)
        userViewModel.getList()
        initViewModel()
    }
    fun initViewModel() {
        Log.d("initBlock","within init block")

        Log.d("getListLiveData","this is above of getListLIveData")

        userViewModel.getListLiveData.observe(this,  { lists->
            Log.d("getListLiveData","getListLiveData is Called")
            onGetList(lists)
        })
        userViewModel.createLiveData.observe(this, {bool->
            Log.d("createLiveData","createLiveData is called")
            onCreate(bool)
        })

        userViewModel.updateLiveData.observe(this, {bool->
            onUpdate(bool)
        })
        userViewModel.deleteLiveData.observe(this,{bool->
            onDelete(bool)
        })

    }

    fun onGetList(it: List<User>) {
        list = ArrayList()
        list.addAll(it)
        Log.d("onGetList","OnGetList called")

        adapterUser= Adapter(this,list,this)
        rv.adapter=adapterUser
        rv.layoutManager=LinearLayoutManager(baseContext)
        adapterUser.notifyDataSetChanged()


    }

    fun onDelete(it: Boolean?) {

        if (it == true) {
            userViewModel.getList()
            resetText()
        }
    }

    fun resetText() {
        Log.d("resetText","resetText called")
        selected = User()
        selected.id=null
        selected.name=null
    }

    fun onCreate(it: Boolean?) {
        if (it == true) {
            Log.d("onCreate","onCreate called")
            userViewModel.getList()
            resetText()
        }

    }

    private fun onUpdate(it: Boolean?) {
        if (it == true) {
            userViewModel.getList()
            resetText()
        }

    }
    override fun onClick(item: User) {

        Log.d("onClick","Passing the intent")
        val intent=Intent(this@MainActivity2,PostExpandableActivity::class.java)
        intent.putExtra("name",item.name)
        intent.putExtra("amount",item.amount)
        intent.putExtra("desc",item.desc)
        intent.putExtra("id",item.id)
        intent.putExtra("title",item.title)
        intent.putExtra("age",item.age)
        intent.putExtra("pic",item.pic)
        startActivity(intent)

     }

    override fun onDelete(item: User, position: Int) {
//        userViewModel.delete(item.id
    }
}