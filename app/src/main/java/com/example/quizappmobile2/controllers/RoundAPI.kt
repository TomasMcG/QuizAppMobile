package controllers

import models.Rounds
import persistence.Serializer
import utils.Utilities.formatListString


/**
 * Class representing the API for managing Rounds.
 *
 * This class provides methods to manipulate Rounds, including adding, deleting, updating, and listing rounds.
 * It also supports loading and storing rounds using a provided serializer.
 *
 * @property serializerType The type of serializer to use for reading and writing rounds.
 */
class RoundAPI(serializerType: Any) {

    private var serializer =  serializerType
    private var rounds = ArrayList<Rounds>()
    private var lastId = 1

    /**
     * Generates and returns a unique identifier for a round.
     *
     * @return A unique identifier for a round.
     */
    private fun getId() = lastId++

    /**
     * Adds a round to the list of rounds.
     *
     * @param roundToAdd The round to be added.
     * @return `true` if the round is added successfully, `false` otherwise.
     */
    fun add(roundToAdd: Rounds): Boolean {
        roundToAdd.roundId = getId()
        return rounds.add(roundToAdd)
    }

    /**
     * Returns a string representation of all stored rounds.
     *
     * @return A string containing the details of all stored rounds.
     */
    fun listAllRounds(): String =
        if (rounds.isEmpty())
            "No rounds stored"
        else
            rounds.toString()

    /**
     * Returns the number of rounds stored.
     *
     * @return The number of rounds stored.
     */
    fun numberOfRounds(): Int =  rounds.size

    /**
     * Returns the number of completed rounds.
     *
     * @return The number of completed rounds.
     */
    fun numberOfCompletedRounds(): Int =  rounds.count { round: Rounds -> round.isCompleted }

    /**
     * Returns the number of incomplete rounds.
     *
     * @return The number of incomplete rounds.
     */
    fun numberOfIncompleteRounds(): Int =  rounds.count { round: Rounds -> !round.isCompleted }

    /**
     * Deletes a round by its identifier.
     *
     * @param idToDelete The identifier of the round to be deleted.
     * @return The deleted round, or `null` if the round does not exist.
     */
    fun deleteRound(idToDelete: Int): Rounds? {
        val roundToDelete = rounds.find { it.roundId == idToDelete }
        return if (roundToDelete != null) {
            rounds.remove(roundToDelete)
            roundToDelete
        } else null
    }

    /**
     * Checks if a given round identifier is valid.
     *
     * @param roundId The round identifier to check.
     * @return `true` if the round identifier is valid, `false` otherwise.
     */
    fun isValidIndex(roundId: Int): Boolean {
        val validRoundIds = rounds.map { it.roundId }
        return roundId in validRoundIds
    }

    /**
     * Finds and returns a round by its identifier.
     *
     * @param roundId The identifier of the round to find.
     * @return The found round, or `null` if no round is found.
     */
    fun findRounds(roundId: Int): Rounds? {
        return rounds.find { it.roundId == roundId }
    }

    /**
     * Updates the title of a round.
     *
     * @param roundToEdit The round to be edited.
     * @param newTitle The new title for the round.
     */
    fun updateRoundTitle(roundToEdit: Rounds?, newTitle: String) {
        roundToEdit?.roundTitle = newTitle
    }

    /**
     * Updates the identifier of a round.
     *
     * @param roundToEdit The round to be edited.
     * @param newRoundId The new identifier for the round.
     */
    fun updateRoundId(roundToEdit: Rounds?, newRoundId: Int) {
        roundToEdit?.roundId = newRoundId
    }

    /**
     * Updates the number of questions attempted in a round.
     *
     * @param roundToEdit The round to be edited.
     * @param newNoQuestionsAttempted The new number of questions attempted.
     */
    fun updateQuestionsAttempted(roundToEdit: Rounds?, newNoQuestionsAttempted: Int) {
        roundToEdit?.questionsAttempted = newNoQuestionsAttempted
    }

    /**
     * Marks a round as complete.
     *
     * @param roundToEdit The round to be marked as complete.
     */
    fun setRoundToComplete(roundToEdit: Rounds) {
        roundToEdit.isCompleted = true
    }

    /**
     * Marks a round as incomplete.
     *
     * @param roundToEdit The round to be marked as incomplete.
     */
    fun setRoundToIncomplete(roundToEdit: Rounds) {
        roundToEdit.isCompleted = false
    }

    /**
     * Returns a string representation of all completed rounds.
     *
     * @return A string containing the details of all completed rounds.
     */
    fun listCompletedRounds(): String =
        formatListString(rounds.filter { round -> round.isCompleted } as ArrayList<Any>)

    /**
     * Returns a string representation of all incomplete rounds.
     *
     * @return A string containing the details of all incomplete rounds.
     */
    fun listIncompleteRounds(): String =
        formatListString(rounds.filter { round -> !round.isCompleted } as ArrayList<Any>)

    /**
     * Loads rounds from the serializer.
     *
     * @throws Exception if there is an issue during the loading process.
     */
   /*
    @Throws(Exception::class)

    fun load() {
        rounds = serializer.read() as ArrayList<Rounds>
    }




    /**
     * Stores rounds using the serializer.
     *
     * @throws Exception if there is an issue during the storing process.
     */
    @Throws(Exception::class)
    fun store() {
        serializer.write(rounds)
    }
    */


    fun getRounds(): List<Rounds> {
        return rounds.toList()  // Create a copy of the list to prevent external modification
    }
}
