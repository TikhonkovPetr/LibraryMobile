package com.example.librarymobile

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import classes.Newspaper

class RedactNewspaperActivity:Activity() {
    private lateinit var nameObject: EditText
    private lateinit var issueNumber: EditText
    private lateinit var month: EditText
    private lateinit var available1: RadioButton
    private lateinit var available2: RadioButton
    private lateinit var redactButton: Button
    private lateinit var opisView:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_redact_newspaper)

        nameObject = findViewById(R.id.nameObject)
        issueNumber = findViewById(R.id.issueNumber)
        available1 = findViewById(R.id.radioButtonavAilableTrue)
        available2 = findViewById(R.id.radioButtonavAilableFalse)
        redactButton = findViewById(R.id.buttonRedactObject)
        month = findViewById(R.id.month)

        nameObject.setText(intent.getStringExtra(MainActivity.NAME))
        issueNumber.setText(intent.getIntExtra(MainActivity.ISSUENUMBER,-1))
        month.setText(intent.getStringExtra(MainActivity.ISSUENUMBER))

        if(intent.getBooleanExtra(MainActivity.AVAILABLE,false)){
            available1.isChecked = true
        }
        else{
            available2.isChecked = true
        }

        opisView.setText(Newspaper(intent.getIntExtra(MainActivity.POSITION,-1),nameObject.text.toString(),available1.isChecked,issueNumber.text.toString().toInt(),month.text.toString()).getFullInfo())

        redactButton.setOnClickListener{
            val newElement = Newspaper(intent.getIntExtra(MainActivity.POSITION,-1),nameObject.text.toString(),available1.isChecked,issueNumber.text.toString().toInt(),month.text.toString())
            val resultIntent = Intent().apply {
                putExtra(MainActivity.TYPE,2)
                putExtra(MainActivity.POSITION,newElement.id)
                putExtra(MainActivity.NAME,newElement.name)
                putExtra(MainActivity.AVAILABLE,newElement.available)
                putExtra(MainActivity.MONTHCREATE,newElement.monthCreate)
                putExtra(MainActivity.ISSUENUMBER,newElement.issueNumber)
            }
            setResult(MainActivity.RESULT_REDAKT_OK,resultIntent)
            finish()
        }
    }
}