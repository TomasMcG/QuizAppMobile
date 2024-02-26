package com.example.quizappmobile2.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.quizappmobile2.QuizActivity
import models.Rounds
import com.example.quizappmobile2.R
import controllers.RoundAPI
import org.w3c.dom.Text
import persistence.XMLSerializer

class Rounds : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rounds)
        val resources = resources // Assuming you are in an Activity or a Context
        val xmlResourceId = R.xml.rounds // Replace with the actual resource ID of your XML file

        val xmlSerializer = XMLSerializer(resources, xmlResourceId)
        val roundAPI = RoundAPI(xmlSerializer)


        val textbox = findViewById<EditText>(R.id.roundIdBox)
        roundAPI.add(Rounds(roundTitle = "example"))




        val listBox = findViewById<TextView>(R.id.listRoundsBox)


        val addRoundButton = findViewById<Button>(R.id.addRoundButton)
        addRoundButton.setOnClickListener{
            val roundTitle = textbox.getText().toString()
            roundAPI.add(Rounds(roundTitle = roundTitle))
        }

        val listRoundButton = findViewById<Button>(R.id.listRoundsButton)
        listRoundButton.setOnClickListener{

            listBox.text = roundAPI.listAllRounds()
        }
    }
}