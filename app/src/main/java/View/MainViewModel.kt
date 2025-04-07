package View

import Data.DataLibrary
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import classes.ObjectLibrary

class MainViewModel:ViewModel() {
    private val _objects = MutableLiveData<List<ObjectLibrary>>()
    val objects:LiveData<List<ObjectLibrary>> = _objects

    init {
        _objects.value = DataLibrary().getAllObject()
    }

    fun updateObjects(list:List<ObjectLibrary>){
        val oldList = _objects.value
        _objects.value = oldList?.plus(list)?:list
    }

    fun updateObjectContent(position:Int, element:ObjectLibrary){
        val oldList = _objects.value?.toMutableList()
        oldList?.set(position-1,element)
        _objects.value = oldList
    }
    fun getObjectsList():List<ObjectLibrary>{
        return (_objects.value as List<ObjectLibrary>)
    }
    fun getSizeData(): Int {
        return _objects.value?.size?.plus(1)?:-1
    }
}