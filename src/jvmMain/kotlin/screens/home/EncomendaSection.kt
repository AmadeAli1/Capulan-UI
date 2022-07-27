package screens.home

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import helpers.ScrollableLazylist
import kotlinx.coroutines.async
import model.produto.Encomenda
import navigation.Screen
import repository.ProdutoRepository

@Composable
@Preview
fun EncomendaSection(navigate: MutableState<Screen>) {
    Column(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            Button(onClick = {

            }) {
                Icon(imageVector = Icons.Default.LocalShipping, contentDescription = null)
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = "Encomenda")
            }
        }
        EncomendaList()
    }
}

@Composable
private fun TableEncomendaHeader() {
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
            Text(text = "Encomenda ID", modifier = Modifier.weight(1f))
            Text(text = "Client Name", modifier = Modifier.weight(1f))
            Text(text = "Product", modifier = Modifier.weight(1f))
            Text(text = "Estado", modifier = Modifier.weight(1f))
            Text(text = "price", modifier = Modifier.weight(0.5f))
            Text(text = "Date", modifier = Modifier.weight(0.5f))
            Text(text = "Quantidade", modifier = Modifier.weight(0.5f))
        }
    }
}

@Composable
fun EncomendaList(produtoRepository: ProdutoRepository = ProdutoRepository()) {
    val encomendas = mutableStateListOf<Encomenda>()

    LaunchedEffect(key1 = Unit) {
        val get = async {
            produtoRepository.findAllEncomendas()
        }
        encomendas.addAll(get.await())
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        TableEncomendaHeader()
        Spacer(modifier = Modifier.height(8.dp))
        ScrollableLazylist {
            LazyColumn(state = it, verticalArrangement = Arrangement.spacedBy(6.dp)) {
                items(items = encomendas) { item ->
                    TableItemEncomenda(item)
                }
            }
        }

    }
}

@Composable
private fun TableItemEncomenda(item: Encomenda) {
    val scope = rememberCoroutineScope()
    Card(modifier = Modifier.fillMaxWidth().height(50.dp).padding(bottom = 2.dp, top = 2.dp), elevation = 2.dp) {
        ProvideTextStyle(value = TextStyle(fontFamily = FontFamily.Monospace, fontWeight = FontWeight.W100)) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "${item.idEncomenda}", modifier = Modifier.weight(1f))
                Text(text = item.cliente, modifier = Modifier.weight(1f))
                Text(text = item.nome, modifier = Modifier.weight(1f))
                Text(text = item.estado.name, modifier = Modifier.weight(1f))//TODO() estado
                Text(text = item.preco.toString(), modifier = Modifier.weight(0.5f))
                Text(text = "${item.date}", modifier = Modifier.weight(0.5f))
                Text(text = item.quantidade.toString(), modifier = Modifier.weight(0.5f))
            }
        }
    }
}