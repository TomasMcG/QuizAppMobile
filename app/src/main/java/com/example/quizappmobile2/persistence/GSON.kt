package com.example.quizappmobile2.persistence

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import models.Rounds


import java.lang.reflect.Type

class GsonHelper {

    companion object {
        private const val PREF_NAME = "your_prefs"
        private const val KEY_ROUNDS_ARRAY = "rounds_array"

        private val gson = Gson()

        fun saveRoundsArray(context: Context, roundsArray: List<Rounds>) {
            val jsonArray = gson.toJson(roundsArray)

            val preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            val editor = preferences.edit()
            editor.putString(KEY_ROUNDS_ARRAY, jsonArray)
            editor.apply()
        }

        fun loadRoundsArray(context: Context): List<Rounds> {
            val preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            val jsonArray = preferences.getString(KEY_ROUNDS_ARRAY, "") ?: ""

            val type: Type = object : TypeToken<List<Rounds>>() {}.type

            return gson.fromJson(jsonArray, type)
        }
    }
}