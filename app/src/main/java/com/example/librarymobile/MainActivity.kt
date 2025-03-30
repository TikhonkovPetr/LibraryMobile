package com.example.librarymobile

import Data.DataLibrary
import Recycler.Adapter.ObjectAdapter
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import classes.ObjectLibrary
import com.example.librarymobile.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val objectAdapter = ObjectAdapter()

    private var items = mutableListOf<ObjectLibrary>()

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
        binding.generate.setOnClickListener{
            items = DataLibrary::getAllObject.invoke(DataLibrary())
            objectAdapter.setNewData(items)
        }
    }
}