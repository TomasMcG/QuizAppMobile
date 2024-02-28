package com.example.quizappmobile2.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import models.Rounds
import com.example.quizappmobile2.R
import com.example.quizappmobile2.persistence.GsonHelper
import controllers.RoundAPI
import persistence.XMLSerializer

class Rounds : AppCompatActivity() {

    private lateinit var roundAPI: RoundAPI
    private lateinit var listBox: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rounds)}}

        // Initialize RoundAPI with loaded data from Gson
        /*val loadedRounds = GsonHelper.loadRoundsArray(this)
        roundAPI = RoundAPI(XMLSerializer(resources, R.xml.rounds, loadedRounds))

        val textbox = findViewById<EditText>(R.id.roundIdBox)

        val addRoundButton = findViewById<Button>(R.id.addRoundButton)
        addRoundButton.setOnClickListener {
            val roundTitle = textbox.text.toString()
            roundAPI.add(Rounds(roundTitle = roundTitle))

            // Save the updated list to Gson
            Log.d("RoundsActivity", "Saving rounds: ${roundAPI.getRounds()}")
            GsonHelper.saveRoundsArray(this, roundAPI.getRounds()) }

        listBox = findViewById<TextView>(R.id.listRoundsBox)

        val listRoundButton = findViewById<Button>(R.id.listRoundsButton)
        listRoundButton.setOnClickListener {
            listBox.text = roundAPI.listAllRounds()
        }
    }

    override fun onResume() {
        super.onResume()

        // Load the list of rounds from Gson when entering the screen
        val loadedRounds = GsonHelper.loadRoundsArray(this)
        roundAPI = RoundAPI(XMLSerializer(resources, R.xml.rounds, loadedRounds))
    }
}


   /* override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rounds)

        // Initialize RoundAPI with loaded data from Gson
        val loadedRounds = GsonHelper.loadRoundsArray(this)
        roundAPI = RoundAPI(XMLSerializer(resources, R.xml.rounds, loadedRounds))

        val textbox = findViewById<EditText>(R.id.roundIdBox)

        val addRoundButton = findViewById<Button>(R.id.addRoundButton)
        addRoundButton.setOnClickListener {
            val roundTitle = textbox.text.toString()
            roundAPI.add(Rounds(roundTitle = roundTitle))

            // Save the updated list to Gson
            GsonHelper.saveRoundsArray(this, roundAPI.getAllRounds())
        }

        listBox = findViewById<TextView>(R.id.listRoundsBox)

        val listRoundButton = findViewById<Button>(R.id.listRoundsButton)
        listRoundButton.setOnClickListener {
            listBox.text = roundAPI.listAllRounds()
        }
    }

    override fun onResume() {
        super.onResume()

        // Load the list of rounds from Gson when entering the screen
        val loadedRounds = GsonHelper.loadRoundsArray(this)
        roundAPI = RoundAPI(XMLSerializer(resources, R.xml.rounds, loadedRounds))
    }
}*/

    /*class RoundsActivity : AppCompatActivity() {


        private val gsonHelper = GsonHelper
        private lateinit var roundsList: MutableList<Rounds>

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_rounds)

            val textbox = findViewById<EditText>(R.id.roundIdBox)
            val listBox = findViewById<TextView>(R.id.listRoundsBox)

            val addRoundButton = findViewById<Button>(R.id.addRoundButton)
            addRoundButton.setOnClickListener {
                val roundTitle = textbox.text.toString()

                // Add the new round to the in-memory list
                roundsList.add(Rounds(roundTitle = roundTitle))

                // Save the updated rounds list
                gsonHelper.saveRoundsArray(this, roundsList)

                // Update the UI
                updateUI()
            }

            val listRoundButton = findViewById<Button>(R.id.listRoundsButton)
            listRoundButton.setOnClickListener {
                // Update the UI
                updateUI()
            }

            // Load rounds list during onCreate
            roundsList = gsonHelper.loadRoundsArray(this).toMutableList()

            // Update the UI
            updateUI()
        }

        private fun updateUI() {
            val listBox = findViewById<TextView>(R.id.listRoundsBox)

            // Display the list of rounds in the TextView
            listBox.text = roundsList.joinToString("\n") { it.roundTitle }
        }
    }}*/