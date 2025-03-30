package Recycler.util

import androidx.recyclerview.widget.DiffUtil
import classes.ObjectLibrary

class ObjectDifUtill(private val oldList:List<ObjectLibrary>,private val newList:List<ObjectLibrary>)
    : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

}