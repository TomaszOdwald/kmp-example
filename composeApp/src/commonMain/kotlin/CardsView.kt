import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import models.Draw

@Composable
fun CardsView(draw: Draw) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (card in draw.cards) {
            KamelImage(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp),
                resource = asyncPainterResource(data = card.image),
                contentDescription = card.code,
                contentScale = ContentScale.FillWidth
            )
        }
    }
} // Unable to match the desired swap behavior