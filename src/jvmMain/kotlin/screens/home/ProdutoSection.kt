package screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.Text
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
import model.produto.Produto
import navigation.Screen
import repository.ProdutoRepository

@Composable
fun ProdutoSection(navigate: MutableState<Screen>) {
    Column(modifier = Modifier.fillMaxSize()) {
        ProdutoList()
    }
}

@Composable
private fun ProdutoList(produtoRepository: ProdutoRepository = ProdutoRepository()) {
    val produtos = mutableStateListOf<Produto>()

    LaunchedEffect(key1 = Unit) {
        val get = async {
            produtoRepository.findAllProdutos()
        }
        produtos.addAll(get.await())
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        TableStockHeader()
        Spacer(modifier = Modifier.height(8.dp))
        ScrollableLazylist {
            LazyColumn(state = it, verticalArrangement = Arrangement.spacedBy(6.dp)) {
                items(items = produtos) { item ->
                    TabelaItemProduto(item)
                }
            }
        }

    }
}

@Composable
private fun TableStockHeader() {
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
            Text(text = "ProdutoID", modifier = Modifier.weight(1f))
            Text(text = "Nome", modifier = Modifier.weight(1f))
            Text(text = "Price", modifier = Modifier.weight(1f))
            Text(text = "Quantidade", modifier = Modifier.weight(1f))
            Text(text = "Categoria", modifier = Modifier.weight(0.5f))
        }
    }
}

@Composable
private fun TabelaItemProduto(item: Produto) {
    val scope = rememberCoroutineScope()
    Card(modifier = Modifier.fillMaxWidth().height(50.dp).padding(bottom = 2.dp, top = 2.dp), elevation = 2.dp) {
        ProvideTextStyle(value = TextStyle(fontFamily = FontFamily.Monospace, fontWeight = FontWeight.W100)) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "${item.id}", modifier = Modifier.weight(1f))
                Text(text = item.nome!!, modifier = Modifier.weight(1f))
                Text(text = item.preco.toString(), modifier = Modifier.weight(1f))
                Text(text = item.quantidade.toString(), modifier = Modifier.weight(1f))//TODO() estado
                Text(text = item.categoriaType!!.name, modifier = Modifier.weight(0.5f))
            }
        }
    }
}

