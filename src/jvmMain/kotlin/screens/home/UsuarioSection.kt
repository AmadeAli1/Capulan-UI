package screens.home

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.material.Card
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.async
import model.actores.Cliente
import repository.UserRepository

@Composable
@Preview
fun UsuarioSection() {
    Table()
}

@Composable
fun TableHeader() {
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
            Text(text = "First Name", modifier = Modifier.weight(2f))
            Text(text = "Last Name", modifier = Modifier.weight(2f))
            Text(text = "Email", modifier = Modifier.weight(3f))
            Text(text = "Regiao", modifier = Modifier.weight(1f))
            Text(text = "Type", modifier = Modifier.weight(1.5f))
        }
    }
}

@Composable
fun Table() {
    val clientes = mutableStateListOf<Cliente>()

    LaunchedEffect(key1 = Unit) {
        val get = async {
            UserRepository.findAllClientes()
        }
        clientes.addAll(get.await())
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        TableHeader()
        Spacer(modifier = Modifier.height(8.dp))
        ScrollableLazylist {
            LazyColumn(state = it, verticalArrangement = Arrangement.spacedBy(6.dp)) {
                items(items = clientes) { item ->
                    TableItem(item)
                }
            }
        }
    }
}

@Composable
private fun TableItem(cliente: Cliente) {
    Card(modifier = Modifier.fillMaxWidth().height(50.dp), elevation = 0.dp) {
        ProvideTextStyle(value = TextStyle(fontFamily = FontFamily.Monospace, fontWeight = FontWeight.W100)) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val fullname = cliente.user.nome.split(" ")
                Text(text = "${cliente.id}", modifier = Modifier.weight(0.5f))
                Text(text = fullname[0], modifier = Modifier.weight(2f))
                Text(text = fullname[1], modifier = Modifier.weight(2f))
                Text(
                    text = cliente.email,
                    modifier = Modifier.weight(3f),
                    color = Color.Blue.copy(alpha = 0.8f)
                )
                Text(text = cliente.cidade, modifier = Modifier.weight(1f))
                Text(text = cliente.user.userType.name, modifier = Modifier.weight(1.5f))
            }
        }
    }
}

@Composable
fun ScrollableLazylist(content: @Composable (LazyListState) -> Unit) {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        val state = rememberLazyListState()
        content(state)
        VerticalScrollbar(
            modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight(),
            adapter = rememberScrollbarAdapter(
                scrollState = state
            )
        )
    }

}