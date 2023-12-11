package models

import kotlinx.serialization.Serializable

@Serializable
data class Card(
    val code: String,
    val image: String,
    val images: CardImages,
    val value: String,
    val suit: String
)

@Serializable
data class CardImages(
    val svg: String,
    val png: String
)

//{
//    "code": "6H",
//    "image": "https://deckofcardsapi.com/static/img/6H.png",
//    "images": {
//        "svg": "https://deckofcardsapi.com/static/img/6H.svg",
//        "png": "https://deckofcardsapi.com/static/img/6H.png"
//    },
//    "value": "6",
//    "suit": "HEARTS"
//}