package Recycler.Adapter

import Recycler.ViewHolder.ObjectViewHolder
import Recycler.util.ObjectDifUtill
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import classes.Book
import classes.Disk
import classes.Newspaper
import classes.ObjectLibrary
import com.example.librarymobile.AddedActivity
import com.example.librarymobile.MainActivity
import com.example.librarymobile.RedactBookActivity
import com.example.librarymobile.RedactDiskActivity
import com.example.librarymobile.RedactNewspaperActivity
import com.example.librarymobile.databinding.LibraryObjectListBinding

class ObjectAdapter:ListAdapter<ObjectLibrary, ObjectViewHolder>(ObjectDifUtill()){
    private val data:MutableList<ObjectLibrary> = mutableListOf()

    private var onObjectClickListener:((Int,ObjectLibrary) ->Unit)? = null

    fun setNewData(newData:List<ObjectLibrary>){
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ObjectViewHolder {
        val binding = LibraryObjectListBinding.inflate(LayoutInflater.from(parent.context))
        return ObjectViewHolder(binding).apply {
            binding.root.setOnClickListener {
                hendlerObjectClick(parent.context, adapterPosition)
            }
        }
    }

    private fun hendlerObjectClick(context: Context, position:Int){
        if(position != RecyclerView.NO_POSITION){
            val element = data[position]
            onObjectClickListener?.invoke(position,element)
            when(element.getType()){
                1 ->{
                    val intent = Intent(context,RedactBookActivity::class.java)
                    intent.putExtra(MainActivity.POSITION,element.id)
                    intent.putExtra(MainActivity.NAME,element.name)
                    intent.putExtra(MainActivity.TYPE,1)
                    intent.putExtra(MainActivity.AVAILABLE,element.available)
                    intent.putExtra(MainActivity.AUTOR,(element as Book).autor)
                    intent.putExtra(MainActivity.COUNTPAGES,element.countPages.toString())
                    (context as MainActivity).startForResult.launch(intent)
                }
                2 ->{
                    val intent = Intent(context,RedactNewspaperActivity::class.java)
                    intent.putExtra(MainActivity.POSITION,element.id)
                    intent.putExtra(MainActivity.NAME,element.name)
                    intent.putExtra(MainActivity.TYPE,1)
                    intent.putExtra(MainActivity.AVAILABLE,element.available)
                    intent.putExtra(MainActivity.MONTHCREATE,(element as Newspaper).monthCreate)
                    intent.putExtra(MainActivity.ISSUENUMBER,element.issueNumber.toString())
                    (context as MainActivity).startForResult.launch(intent)
                }
                3 ->{
                    val intent = Intent(context, RedactDiskActivity::class.java)
                    intent.putExtra(MainActivity.POSITION,element.id)
                    intent.putExtra(MainActivity.NAME,element.name)
                    intent.putExtra(MainActivity.TYPE,3)
                    intent.putExtra(MainActivity.AVAILABLE,element.available)
                    intent.putExtra(MainActivity.TYPEDISK,(element as Disk).typeDiscID)
                    (context as MainActivity).startForResult.launch(intent)
                }
            }
        }
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ObjectViewHolder, position: Int) {
        holder.bind(data[position])
    }
}