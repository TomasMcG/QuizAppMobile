package com.example.quizappmobile2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.example.quizappmobile2.persistence.GsonHelper
import com.example.quizappmobile2.view.CrudActivity
import controllers.RoundAPI

import persistence.XMLSerializer
import java.io.FileOutputStream
import java.io.InputStream

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*setContent {
            QuizAppMobile2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }*/
        setContentView(R.layout.activity_main)


        val crudButton = findViewById<Button>(R.id.CRUDbutton)
        val quizButton = findViewById<Button>(R.id.playQuizButton)
        val fileName = "rounds.xml"

     /*   val inputStream: InputStream = resources.assets.open(fileName)
        val outputStream: FileOutputStream = openFileOutput("example.txt", Context.MODE_PRIVATE)
        lateinit var rounds: RoundAPI
        try {
            val xmlSerializer = XMLSerializer(inputStream, outputStream)
             rounds = RoundAPI(xmlSerializer.read())
        } finally {
            inputStream.close()
            outputStream.close()
        }*/

        val resources = resources // Assuming you are in an Activity or a Context
        val xmlResourceId = R.xml.rounds // Replace with the actual resource ID of your XML file
        val loadedRounds = GsonHelper.loadRoundsArray(this)
        val xmlSerializer = XMLSerializer(resources, xmlResourceId, loadedRounds)
        val roundAPI = RoundAPI(xmlSerializer)


       /* val resources = resources // Assuming you are in an Activity or a Context
        val xmlResourceId = R.xml.rounds // Replace with the actual resource ID of your XML file
        val loadedRounds = GsonHelper.loadRoundsArray(this)
        Log.d("MainActivity", "Loaded Rounds: $loadedRounds")
        if(loadedRounds != null) {
            val xmlSerializer = XMLSerializer(resources, xmlResourceId, loadedRounds)
            val roundAPI = RoundAPI(xmlSerializer)
            val result = roundAPI.listAllRounds()
            Log.d("MainActivity", "Result from listAllRounds(): $result")

            textbox.text = result
        }*/
        //val inputStream: InputStream = resources.assets.open("rounds.xml")
        //val outputStream: OutputStream = resources.assets.open("rounds.xml")/* obtain output stream from somewhere */
        //val rounds = XMLSerializer(inputStream).read()

//        val rounds = RoundAPI(XMLSerializer(File("src/main/assets/rounds.xml")))
        val textViewResult: TextView = findViewById(R.id.textView)
        textViewResult.text = roundAPI.listAllRounds()

        quizButton.setOnClickListener{
//        textbox.text = "button was pressed."

            val intent = Intent(this@MainActivity, QuizActivity::class.java)
            startActivity(intent)

        }

        crudButton.setOnClickListener {
            val intent = Intent(this@MainActivity,CrudActivity::class.java)
            startActivity(intent)
        }
    }
}
/*
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    QuizAppMobile2Theme {
        Greeting("Android")
    }
}*/

