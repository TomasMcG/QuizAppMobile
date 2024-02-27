package com.example.quizappmobile2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.example.quizappmobile2.persistence.GsonHelper
import com.example.quizappmobile2.view.CrudActivity
import controllers.RoundAPI
import persistence.XMLSerializer

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

        val textbox = findViewById<TextView>(R.id.textView)
        val crudButton = findViewById<Button>(R.id.CRUDbutton)
        val quizButton = findViewById<Button>(R.id.playQuizButton)

        val resources = resources // Assuming you are in an Activity or a Context
        val xmlResourceId = R.xml.rounds // Replace with the actual resource ID of your XML file
        val loadedRounds = GsonHelper.loadRoundsArray(this)
        Log.d("MainActivity", "Loaded Rounds: $loadedRounds")
        if(loadedRounds != null) {
            val xmlSerializer = XMLSerializer(resources, xmlResourceId, loadedRounds)
            val roundAPI = RoundAPI(xmlSerializer)
            val result = roundAPI.listAllRounds()
            Log.d("MainActivity", "Result from listAllRounds(): $result")

            textbox.text = result
        }
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

