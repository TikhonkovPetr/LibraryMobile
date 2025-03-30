package Recycler.ViewHolder

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import classes.ObjectLibrary
import com.example.librarymobile.R
import com.example.librarymobile.databinding.LibraryObjectListBinding

class ObjectViewHolder(private val binding: LibraryObjectListBinding):
    RecyclerView.ViewHolder(binding.root){
    fun bind(objectLibrary: ObjectLibrary) = with(binding){
        bindName(objectLibrary.name)
        bindId(objectLibrary.id)
        bindImg(objectLibrary.getType())
        bindAvaibl(objectLibrary.available)
    }
    private fun bindName(newName:String) = with(binding){
        name.text = "Объект: $newName"
    }
    private fun bindId(newId:Int) = with(binding){
        editTextId.text = "ID: $newId"
    }
    private fun bindImg(newType:Int) = with(binding){
        when(newType){
            1 -> avatar.setImageResource(R.drawable.icon_book)
            2 -> avatar.setImageResource(R.drawable.icon_newspaper)
            3 -> avatar.setImageResource(R.drawable.icon_disk)
        }
    }
    private fun bindAvaibl(newAvaibl:Boolean) = with(binding){
        if(!newAvaibl){
            editTextId.setTextColor(Color.argb(30,0,0,0))
            name.setTextColor(Color.argb(30,0,0,0))
            element.setPadding(3,3,3,3)
        }
        else{
            element.setPadding(10,10,10,10)
        }
    }
}