package com.example.quizappmobile2.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.quizappmobile2.MainActivity
import com.example.quizappmobile2.QuizActivity
import com.example.quizappmobile2.R

class CrudActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crud)
        val questionButton = findViewById<Button>(R.id.buttonQuestions)
        val roundButton = findViewById<Button>(R.id.buttonRounds)
        val backButton = findViewById<Button>(R.id.buttonBack)
        //have 3 buttons choose rounds or questions or back to main

        backButton.setOnClickListener{
//        textbox.text = "button was pressed."

            val intent = Intent(this@CrudActivity, MainActivity::class.java)
            startActivity(intent)

        }

        roundButton.setOnClickListener{
//        textbox.text = "button was pressed."

            val intent = Intent(this@CrudActivity, Rounds::class.java)
            startActivity(intent)

        }
    }
}