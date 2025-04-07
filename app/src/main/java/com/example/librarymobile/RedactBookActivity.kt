package com.example.librarymobile

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import classes.Book

class RedactBookActivity: Activity() {
    private lateinit var nameObject: EditText
    private lateinit var numberPages: EditText
    private lateinit var opisView:TextView
    private lateinit var autor:EditText
    private lateinit var available1: RadioButton
    private lateinit var available2: RadioButton
    private lateinit var redactButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_redact_book)

        nameObject = findViewById(R.id.nameObject)
        numberPages = findViewById(R.id.numberPages)
        available1 = findViewById(R.id.radioButtonavAilableTrue)
        available2 = findViewById(R.id.radioButtonavAilableFalse)
        redactButton = findViewById(R.id.buttonRedactObject)
        autor = findViewById(R.id.nameAutor)
        opisView = findViewById(R.id.textViewOpis)

        nameObject.setText(intent.getStringExtra(MainActivity.NAME))
        numberPages.setText(intent.getStringExtra(MainActivity.COUNTPAGES))
        autor.setText(intent.getStringExtra(MainActivity.AUTOR))

        if(intent.getBooleanExtra(MainActivity.AVAILABLE,false)){
            available1.isChecked = true
        }
        else{
            available2.isChecked = true
        }

        opisView.setText(Book(intent.getIntExtra(MainActivity.POSITION,-1),nameObject.text.toString(),available1.isChecked,numberPages.text.toString().toInt(),autor.text.toString()).getFullInfo())

        redactButton.setOnClickListener{
            val newElement = Book(intent.getIntExtra(MainActivity.POSITION,-1),nameObject.text.toString(),available1.isChecked,numberPages.text.toString().toInt(),autor.text.toString())
            val resultIntent = Intent().apply {
                putExtra(MainActivity.TYPE,1)
                putExtra(MainActivity.POSITION,newElement.id)
                putExtra(MainActivity.NAME,newElement.name)
                putExtra(MainActivity.AVAILABLE,newElement.available)
                putExtra(MainActivity.AUTOR,newElement.autor)
                putExtra(MainActivity.COUNTPAGES,newElement.countPages)
            }
            setResult(MainActivity.RESULT_REDAKT_OK,resultIntent)
            finish()
        }
    }
}