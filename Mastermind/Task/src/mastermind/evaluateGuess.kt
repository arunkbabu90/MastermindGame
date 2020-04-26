package mastermind

import kotlin.math.min

data class Evaluation(val rightPosition: Int, val wrongPosition: Int)

fun evaluateGuess(secret: String, guess: String): Evaluation {
    var rightPos = 0
    var wrongPos = 0
    var cSecret = ""
    var cGuess = ""
    val cChars = mutableListOf<Char>()

    // Right positions
    for ((i, sch) in secret.withIndex())
        if (sch == guess[i]) {
            rightPos++
        } else {
            // Remove repeated letters and cache it for calculating wrong positions
            cSecret += sch
            cGuess += guess[i]
        }

    // Wrong positions
    if (cSecret.isNotEmpty() && cGuess.isNotEmpty())
        for (gcr in guess)
            if (!cChars.contains(gcr)) {
                // Prevent counting the positions of repetitions
                cChars.add(gcr)
                wrongPos += min(cSecret.count { it == gcr }, cGuess.count { it == gcr })
            }

    return Evaluation(rightPos, wrongPos)
}