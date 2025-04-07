package com.example.librarymobile

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast.LENGTH_SHORT
import android.widget.Toast.makeText

class AddedActivity:Activity() {
    private lateinit var nameObject:EditText
    private lateinit var type:RadioGroup
    private lateinit var saveButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_object)

        nameObject = findViewById(R.id.nameObject)
        type = findViewById(R.id.type)
        saveButton = findViewById(R.id.buttonAddObject)

        saveButton.setOnClickListener{
            val flag = checkAll()
            if(flag) {
                val resultIntent = Intent().apply{
                    putExtra(MainActivity.OBJECTNAME,nameObject.text.toString())
                    putExtra(MainActivity.OBJECTTYPE,getTypeToInt())
                }
                setResult(RESULT_OK,resultIntent)
            }
            else{
                makeText(this,"Не записалось", LENGTH_SHORT).show()
                setResult(RESULT_CANCELED)
            }
            finish()
        }
    }

    private fun checkAll():Boolean{
        var flag = true
        when (type.checkedRadioButtonId){
            R.id.radioButtonBook -> makeText(this,"Создать объект книги", LENGTH_SHORT).show()
            R.id.radioButtonNewspaper -> makeText(this,"Создать объект газеты", LENGTH_SHORT).show()
            R.id.radioButtonDisk ->makeText(this,"Создать объект диска", LENGTH_SHORT).show()
            else -> {
                makeText(this,"Обязательно выберите тип нового объекта", LENGTH_SHORT).show()
                flag = false
            }
        }
        if(nameObject.text==null || nameObject.text.toString()==""){
            makeText(this,"Обязательно введите имя", LENGTH_SHORT).show()
            flag = false
        }
        else flag = true
        return flag
    }

    private fun getTypeToInt():Int{
        when (type.checkedRadioButtonId){
            R.id.radioButtonBook -> return 1
            R.id.radioButtonNewspaper -> return 2
            R.id.radioButtonDisk ->return 3
            else -> return -1
        }
    }

    companion object{
        fun createIntent(context: Context):Intent{
            return Intent(context,AddedActivity::class.java)
        }
    }
}