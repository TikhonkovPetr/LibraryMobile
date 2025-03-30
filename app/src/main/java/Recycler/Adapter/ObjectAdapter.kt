package Recycler.Adapter

import Recycler.ViewHolder.ObjectViewHolder
import Recycler.util.ObjectDifUtill
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast.LENGTH_SHORT
import android.widget.Toast.makeText
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import classes.ObjectLibrary
import com.example.librarymobile.databinding.LibraryObjectListBinding

class ObjectAdapter:RecyclerView.Adapter<ObjectViewHolder>(){
    private val data:MutableList<ObjectLibrary> = mutableListOf()

    fun setNewData(newData:List<ObjectLibrary>){
        val difUtill=ObjectDifUtill(data,newData)
        DiffUtil.calculateDiff(difUtill).dispatchUpdatesTo(this)
        data.clear()
        data.addAll(newData)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ObjectViewHolder {
        val binding = LibraryObjectListBinding.inflate(LayoutInflater.from(parent.context))
        return ObjectViewHolder(binding).apply{
            binding.root.setOnClickListener{
                hendlerObjectClick(parent.context,adapterPosition)
            }
        }
    }

    private fun hendlerObjectClick(context: Context, position:Int){
        if(position != RecyclerView.NO_POSITION){
            val newData = data
            newData[position].available=!data[position].available
            val item = data[position]
            makeText(context,"Элемент с Id: ${item.id}", LENGTH_SHORT).show()
            notifyDataSetChanged()
        }
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ObjectViewHolder, position: Int) {
        holder.bind(data[position])
    }

}