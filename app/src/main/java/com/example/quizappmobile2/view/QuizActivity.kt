package com.example.quizappmobile2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.quizappmobile2.persistence.GsonHelper
import controllers.RoundAPI
import models.Questions
import models.Rounds
import persistence.XMLSerializer

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

        /*val xmlParser = XMLParser()
        xmlParser.parseXml(resources, R.xml.rounds)
        val inputStream = resources.openRawResource(R.xml.rounds)
        val roundAPI = RoundAPI(XMLSerializer(inputStream))*/
        //res contains input streams not files.
        //val roundAPI = RoundAPI(XMLSerializer(File("rounds.xml")))
        /*val xmlResource = resources.getXml(R.xml.rounds)
        val roundAPI = RoundAPI(XMLSerializer(xmlResource)
         */



        val resources = resources // Assuming you are in an Activity or a Context
        val xmlResourceId = R.xml.rounds // Replace with the actual resource ID of your XML file
        val loadedRounds = GsonHelper.loadRoundsArray(this)
        val xmlSerializer = XMLSerializer(resources, xmlResourceId, loadedRounds)
        val roundAPI = RoundAPI(xmlSerializer)
        fillData(roundAPI)

        val chooseRoundBox = findViewById<TextView>(R.id.enterRoundIdBox)
        val chosenRound = roundAPI.findRounds(chooseRoundBox.parseInt)
        val chooseRoundButton = findViewById<Button>(R.id.selectRound)
        val correctAnswer = findViewById<TextView>(R.id.correctAnswerBox)
        val correctAnswerButton = findViewById<Button>(R.id.confirmButton)

        val questionsBox = findViewById<TextView>(R.id.QuestionTextAndId)
        val possibleQuestionAnswers = findViewById<TextView>(R.id.PossibleAnswersBox)

        val result = roundAPI.listAllRounds()
        val textViewResult: TextView = findViewById(R.id.textView2)
        textViewResult.text = result
    }

    fun fillData(roundAPI: RoundAPI){
        roundAPI.add(Rounds(roundId= 1, roundTitle = "Geography Round"))
        roundAPI.add(Rounds(roundId= 2,roundTitle = "television Round"))
        var round = roundAPI.findRounds(1)
        // val chosenRound = askUserToChooseRoundByTitle()
        val possibleAnswers:List<String> = listOf("Waterford","Tipperary","Limerick")
        round?.addQuestion(Questions(1, "Which of the following counties is coastal:", possibleAnswers, "Waterford", "Easy"))


        round = roundAPI.findRounds(2)
        round?.addQuestion(Questions(2, "What city is the capital of Ireland:", listOf("Dublin City","Cork City","Waterford City"), "Dublin City", "Easy"))

    }

}