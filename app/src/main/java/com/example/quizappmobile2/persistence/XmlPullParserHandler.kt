import android.util.Xml
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import java.io.InputStream

data class Round(
    var roundId: Int = 0,
    var roundTitle: String = "",
    var questionsAttempted: Int = 0,
    var questions: List<Question> = emptyList(),
    var isCompleted: Boolean = false,
    var lastQuestionId: Int = 0
)

data class Question(
    var questionId: Int = 0,
    var questionText: String = "",
    var possibleAnswers: List<String> = emptyList(),
    var correctAnswer: String = "",
    var difficulty: String = ""
)

class XmlParser {
    fun parseXml(inputStream: InputStream): List<Round> {
        val roundsList = mutableListOf<Round>()
        var currentRound: Round? = null
        var currentQuestion: Question? = null
        var possibleAnswers: MutableList<String>? = null

        try {
            val parser = Xml.newPullParser()
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false)
            parser.setInput(inputStream, null)

            var eventType = parser.eventType
            while (eventType != XmlPullParser.END_DOCUMENT) {
                val tagName = parser.name

                when (eventType) {
                    XmlPullParser.START_TAG -> {
                        if ("models.Rounds" == tagName) {
                            currentRound = Round()
                            possibleAnswers = mutableListOf()
                        } else if ("models.Questions" == tagName) {
                            currentQuestion = Question()
                        }
                    }

                    XmlPullParser.TEXT -> {
                        val text = parser.text
                        if (currentQuestion != null) {
                            handleQuestionText(tagName, text, currentQuestion!!, possibleAnswers!!)
                        } else if (currentRound != null) {
                            handleRoundText(tagName, text, currentRound!!)
                        }
                    }

                    XmlPullParser.END_TAG -> {
                        if ("models.Rounds" == tagName) {
                            roundsList.add(currentRound!!)
                            currentRound = null
                        } else if ("models.Questions" == tagName) {
                            if (currentRound != null) {
                                currentRound!!.questions = currentRound!!.questions + currentQuestion!!
                                currentQuestion = null
                                possibleAnswers = null
                            }
                        }
                    }
                }

                eventType = parser.next()
            }

        } catch (e: XmlPullParserException) {
            e.printStackTrace()
        } catch (e: java.io.IOException) {
            e.printStackTrace()
        } finally {
            try {
                inputStream.close()
            } catch (e: java.io.IOException) {
                e.printStackTrace()
            }
        }

        return roundsList
    }

    private fun handleRoundText(tagName: String, text: String, currentRound: Round) {
        when (tagName) {
            "roundId" -> currentRound.roundId = text.toInt()
            "roundTitle" -> currentRound.roundTitle = text
            "questionsAttempted" -> currentRound.questionsAttempted = text.toInt()
            "isCompleted" -> currentRound.isCompleted = text.toBoolean()
            "lastQuestionId" -> currentRound.lastQuestionId = text.toInt()
        }
    }

    private fun handleQuestionText(tagName: String, text: String, currentQuestion: Question, possibleAnswers: MutableList<String>) {
        when (tagName) {
            "questionId" -> currentQuestion.questionId = text.toInt()
            "questionText" -> currentQuestion.questionText = text
            "possibleAnswers" -> {
                // Do nothing here, as we handle possibleAnswers in START_TAG
            }
            "string" -> possibleAnswers.add(text)
            "correctAnswer" -> currentQuestion.correctAnswer = text
            "difficulty" -> currentQuestion.difficulty = text
        }
    }
}
