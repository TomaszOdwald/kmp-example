package models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Draw(
    val success: Boolean,
    @SerialName("deck_id") val deckId: String,
    val cards: List<Card>,
    val remaining: Int
)

val emptyDraw = Draw(
    success = false,
    deckId = "",
    cards = emptyList(),
    remaining = 0
)

//{
//    "success": true,
//    "deck_id": "kxozasf3edqu",
//    "cards": [
//        ...
//    ],
//    "remaining": 50
//}