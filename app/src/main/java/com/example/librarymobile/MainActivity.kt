package com.example.librarymobile

import Recycler.Adapter.ObjectAdapter
import View.MainViewModel
import View.ViewModelFactory
import android.os.Bundle
import android.widget.Toast.LENGTH_SHORT
import android.widget.Toast.makeText
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import classes.Book
import classes.Disk
import classes.Newspaper
import classes.ObjectLibrary
import com.example.librarymobile.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    private val objectAdapter = ObjectAdapter()

    private var items = listOf<ObjectLibrary>()

    val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result: ActivityResult ->
        if(result.resultCode == RESULT_OK){
            val objectName = result.data?.getStringExtra(OBJECTNAME)?:""
            val objectType = result.data?.getIntExtra(OBJECTTYPE,-1)
            when(objectType){
                1 -> viewModel.updateObjects(listOf(Book(viewModel.getSizeData(),objectName,true,0,"")))
                2 -> viewModel.updateObjects(listOf(Newspaper(viewModel.getSizeData(),objectName,true,0,"")))
                3 -> viewModel.updateObjects(listOf(Disk(viewModel.getSizeData(),objectName,true,0)))
            }
        }
        else if (result.resultCode == RESULT_REDAKT_OK){
            val id = result.data?.getIntExtra(POSITION,-1)?:-1
            val name = result.data?.getStringExtra(NAME)?:"null"
            val avaible = result.data?.getBooleanExtra(AVAILABLE,false)?:false
            when(result.data?.getIntExtra(TYPE,-1)){
                1 -> viewModel.updateObjectContent(id,Book(id,name,avaible,
                    result.data?.getIntExtra(COUNTPAGES,0)?:0,
                    result.data?.getStringExtra(AUTOR)?:""))
                2 -> viewModel.updateObjectContent(id,Newspaper(id,name,avaible,
                    result.data?.getIntExtra(ISSUENUMBER,0)?:0,
                    result.data?.getStringExtra(MONTHCREATE)?:""))
                3 -> viewModel.updateObjectContent(id,Disk(id,name,avaible,
                    result.data?.getIntExtra(TYPEDISK,0)?:0))
                else -> makeText(this,"Не получилось обновить", LENGTH_SHORT).show()
            }
        }
        else if(result.resultCode == RESULT_CANCELED){
            makeText(this,"Не сохранилось", LENGTH_SHORT).show()
        }
    }
    private val binding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        with(binding.recycleView){
            layoutManager = GridLayoutManager(context, 2)
            adapter = objectAdapter
        }
        initViewModel()
        items = viewModel.getObjectsList()
        binding.addObject.setOnClickListener{
            startForResult.launch(AddedActivity.createIntent(this))
        }
    }

    private fun initViewModel(){
        val factory = ViewModelFactory()
        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]
        viewModel.objects.observe(this){ objects ->
            objectAdapter.setNewData(objects)
        }
    }
    companion object{
        const val RESULT_REDAKT_OK = 2
        const val POSITION = "position"
        const val NAME = "name"
        const val AVAILABLE = "available"
        const val ISSUENUMBER = "issueNumber"
        const val MONTHCREATE = "monthCreate"
        const val AUTOR = "autor"
        const val COUNTPAGES = "countpages"
        const val TYPE = "type"
        const val TYPEDISK = "typeDisk"
        const val OBJECTNAME = "objectName"
        const val OBJECTTYPE = "objectType"
    }
}