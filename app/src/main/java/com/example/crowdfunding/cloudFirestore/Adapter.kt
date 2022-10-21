package com.example.crowdfunding.cloudFirestore

import android.content.Context
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.crowdfunding.R
import com.example.crowdfunding.User
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.NonDisposableHandle.parent
import java.io.File

class Adapter(val context: Context, var list:List<User>, var onItemClickListener:OnItemClickListener) :RecyclerView.Adapter<Adapter.UserViewHolder>(){



    val localfile=File.createTempFile("tempImage","")
    class UserViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        val title:TextView=itemView.findViewById(R.id.title)
//        val desc:TextView=itemView.findViewById(R.id.desc)
        val amount:TextView=itemView.findViewById(R.id.amount)
//        val imageView:ImageView=itemView.findViewById(R.id.imageView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.itemview, parent, false)
        return UserViewHolder(inflater)
    }
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.title.text=list[position].title
        holder.amount.text=list[position].amount
//        val storageRef=FirebaseStorage.getInstance().reference.child("images/${list[position].pic}")
//        storageRef.getFile(localfile).addOnSuccessListener {
//           val bitmap=BitmapFactory.decodeFile(localfile.absolutePath)
//            holder.imageView.setImageBitmap(bitmap)
//        }
//        Log.d("Glide","Image inside the adapter${list[position].pic}")
//        Glide.with(holder.itemView).load(list[position].pic).into(holder.imageView)
        holder.itemView.setOnClickListener {
            onItemClickListener.onClick(list[position])
        }

//        holder.itemView.setOnClickListener {
////            onItemClickListener.onClick(item =User(list[position].id,list[position].name,list[position].age,list[position].desc,list[position].pic))
//        }
            }

    override fun getItemCount(): Int {
       return list.size
    }
    interface OnItemClickListener {
        fun onClick(item:User )
        fun onDelete(item: User, position: Int)
    }
}