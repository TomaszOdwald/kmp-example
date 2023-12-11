package models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Deck(
    val success: Boolean,
    @SerialName("deck_id") val deckId: String,
    val shuffled: Boolean,
    val remaining: Int
)

val emptyDeck = Deck(
    success = false,
    deckId = "",
    shuffled = false,
    remaining = 0
)

//{
//    "success": true,
//    "deck_id": "3p40paa87x90",
//    "shuffled": true,
//    "remaining": 52
//}