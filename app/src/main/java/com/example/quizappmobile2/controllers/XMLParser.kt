package com.example.quizappmobile2.controllers

import android.content.res.Resources
import org.xmlpull.v1.XmlPullParserFactory
import java.io.InputStreamReader

class XMLParser {

    fun parseXml(resources: Resources, xmlResourceId: Int) {
        try {
            val parserFactory = XmlPullParserFactory.newInstance()
            val parser = parserFactory.newPullParser()

            val inputStream = resources.openRawResource(xmlResourceId)
            parser.setInput(InputStreamReader(inputStream))

            // Now you can parse the XML using the XmlPullParser
            // For example:
            while (parser.eventType != org.xmlpull.v1.XmlPullParser.END_DOCUMENT) {
                when (parser.eventType) {
                    org.xmlpull.v1.XmlPullParser.START_TAG -> {
                        val tagName = parser.name
                        // Handle start tags
                    }
                    org.xmlpull.v1.XmlPullParser.TEXT -> {
                        val text = parser.text
                        // Handle text content
                    }
                    org.xmlpull.v1.XmlPullParser.END_TAG -> {
                        val tagName = parser.name
                        // Handle end tags
                    }
                }
                parser.next()
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
