import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory

@Composable
fun App() {
    MaterialTheme {
        val greetingText by remember { mutableStateOf(Greeting().greet()) }

        val deckViewModel = getViewModel(Unit, viewModelFactory { DeckViewModel() })
        val currentDeck by deckViewModel.currentDeck.collectAsState()
        val currentDraw by deckViewModel.currentDraw.collectAsState()

        LaunchedEffect(deckViewModel) {
            deckViewModel.createNewDeck()
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = greetingText,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
                    .padding(8.dp)
                    .background(color = Color.LightGray, shape = RoundedCornerShape(4.dp))
            )

            Spacer(Modifier.height(16.dp))

            Button(
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                enabled = currentDeck.success,
                onClick = { deckViewModel.drawCards() }
            ) {
                Text(
                    fontSize = 24.sp,
                    text = "Draw 2 cards"
                )
            }

            CardsView(currentDraw)
        }
    }
}