import dev.icerock.moko.mvvm.viewmodel.ViewModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import models.Deck
import models.Draw
import models.emptyDeck
import models.emptyDraw

class DeckViewModel: ViewModel() {
    companion object {
        private const val DECK_API_URL = "https://www.deckofcardsapi.com/api/deck"
        private const val NEW_DECK_URL = "${DECK_API_URL}/new/shuffle/?deck_count=1"

        private fun getDrawURL(deckId: String, count: Int): String = "${DECK_API_URL}/${deckId}/draw/?count=${count}"
    }

    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json()
        }
    }

    private val _currentDeck = MutableStateFlow(emptyDeck)
    val currentDeck = _currentDeck.asStateFlow()

    private val _currentDraw = MutableStateFlow(emptyDraw)
    val currentDraw = _currentDraw.asStateFlow()

    override fun onCleared() {
        super.onCleared()
        httpClient.close()
    }

    fun createNewDeck() {
        viewModelScope.launch {
            _currentDeck.value = getNewDeck()
        }
    }

    fun drawCards() {
        _currentDraw.value = emptyDraw
        viewModelScope.launch {
            _currentDraw.value = drawTwoCards()
        }
    }

    private suspend fun getNewDeck(): Deck = httpClient.get(NEW_DECK_URL).body()

    private suspend fun drawTwoCards(): Draw = httpClient.get(getDrawURL(_currentDeck.value.deckId, 2)).body()
}