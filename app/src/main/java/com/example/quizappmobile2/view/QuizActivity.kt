package com.example.quizappmobile2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.quizappmobile2.controllers.XMLParser
import controllers.RoundAPI
import persistence.XMLSerializer
import java.io.File

class QuizActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val textbox = findViewById<TextView>(R.id.textViewQuiz)
        textbox.text = "\n" +
                "Welcome to the quiz\n" +
                "--------------------------------------------------------------------------------------\n" +
                "Our quiz has multiple rounds based on different things such as History or Geography\n" +
                "To get started on the quiz please choose a round to try out\n" +
                "--------------------------------------------------------------------------------------"

        val xmlParser = XMLParser()
        xmlParser.parseXml(resources, R.xml.rounds)
        /*val inputStream = resources.openRawResource(R.xml.rounds)
        val roundAPI = RoundAPI(XMLSerializer(inputStream))*/
        //res contains input streams not files.
        //val roundAPI = RoundAPI(XMLSerializer(File("rounds.xml")))
        /*val xmlResource = resources.getXml(R.xml.rounds)
        val roundAPI = RoundAPI(XMLSerializer(xmlResource))
        */
       /* val resources = resources // Assuming you are in an Activity or a Context
        val xmlResourceId = R.xml.rounds // Replace with the actual resource ID of your XML file
        val loadedRounds = GsonHelper.loadRoundsArray(this)
        val xmlSerializer = XMLSerializer(resources, xmlResourceId, loadedRounds)
        val roundAPI = RoundAPI(xmlSerializer)*/
        val rounds = RoundAPI(XMLSerializer(File("src/main/assets/rounds.xml")))
        val result = rounds.listAllRounds()

        val textViewResult: TextView = findViewById(R.id.textView2)
        textViewResult.text = result
    }

}