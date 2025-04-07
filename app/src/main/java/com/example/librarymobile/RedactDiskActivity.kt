package com.example.librarymobile

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import classes.Disk

class RedactDiskActivity:Activity() {
    private lateinit var nameObject: EditText
    private lateinit var opisView: TextView
    private lateinit var available1: RadioButton
    private lateinit var available2: RadioButton
    private lateinit var typeDVD: RadioButton
    private lateinit var typeCD: RadioButton
    private lateinit var redactButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_redact_disk)

        nameObject = findViewById(R.id.nameObject)
        available1 = findViewById(R.id.radioButtonavAilableTrue)
        available2 = findViewById(R.id.radioButtonavAilableFalse)
        typeDVD = findViewById(R.id.radioButtonDVD)
        typeCD = findViewById(R.id.radioButtonCD)
        redactButton = findViewById(R.id.buttonRedactObject)
        opisView = findViewById(R.id.textViewOpis)

        nameObject.setText(intent.getStringExtra(MainActivity.NAME))

        if(intent.getBooleanExtra(MainActivity.AVAILABLE,false)){
            available1.isChecked = true
        }
        else{
            available2.isChecked = true
        }
        var typeDisk = intent.getIntExtra(MainActivity.TYPEDISK,-1)
        if(intent.getIntExtra(MainActivity.TYPEDISK,-1)==0){
            typeDVD.isChecked = true
        }
        else if((intent.getIntExtra(MainActivity.TYPEDISK,-1)==1)){
            typeCD.isChecked = true
        }
        opisView.setText(Disk(intent.getIntExtra(MainActivity.POSITION,-1),nameObject.text.toString(),available1.isChecked,typeDisk).getFullInfo())

        redactButton.setOnClickListener{
            var newElement = Disk(-1,"",false,0)
            if(typeDVD.isChecked){
                newElement = Disk(intent.getIntExtra(MainActivity.POSITION,-1),nameObject.text.toString(),available1.isChecked,0)
            }
            else if(typeCD.isChecked)
            {
                newElement = Disk(intent.getIntExtra(MainActivity.POSITION,-1),nameObject.text.toString(),available1.isChecked,1)
            }
            val resultIntent = Intent().apply {
                putExtra(MainActivity.TYPE,3)
                putExtra(MainActivity.POSITION,newElement.id)
                putExtra(MainActivity.NAME,newElement.name)
                putExtra(MainActivity.AVAILABLE,newElement.available)
                putExtra(MainActivity.TYPEDISK,newElement.typeDiscID)
            }
            setResult(MainActivity.RESULT_REDAKT_OK,resultIntent)
            finish()
        }
    }
}