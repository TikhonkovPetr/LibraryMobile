package Recycler.util

import androidx.recyclerview.widget.DiffUtil
import classes.ObjectLibrary

class ObjectDifUtill: DiffUtil.ItemCallback<ObjectLibrary>() {

    override fun areItemsTheSame(oldItem: ObjectLibrary, newItem: ObjectLibrary): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ObjectLibrary, newItem: ObjectLibrary): Boolean {
        return (oldItem.id == newItem.id && oldItem.name == newItem.name && oldItem.available == newItem.available)
    }

}