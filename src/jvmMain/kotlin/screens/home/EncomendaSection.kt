package screens.home

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import navigation.Screen

@Composable
@Preview
fun EncomendaSection(navigate: MutableState<Screen>) {

}

@Composable
private fun TableHeader() {
    ProvideTextStyle(
        value = TextStyle(
            fontWeight = FontWeight.W600,
            fontSize = 18.sp,
            fontFamily = FontFamily.Monospace
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = "Id", modifier = Modifier.weight(0.5f))
            Text(text = "Client Name", modifier = Modifier.weight(2f))
            Text(text = "Product", modifier = Modifier.weight(2f))
            Text(text = "State", modifier = Modifier.weight(2f))
            Text(text = "price", modifier = Modifier.weight(0.5f))
            Spacer(modifier = Modifier.weight(0.2f))
            Spacer(modifier = Modifier.weight(0.2f))
        }
    }
}
