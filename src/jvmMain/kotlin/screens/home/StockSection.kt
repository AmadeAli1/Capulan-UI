package screens.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.mouseClickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import helpers.Dropdown
import helpers.InputText
import helpers.ScrollableLazylist
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import model.produto.Categoria
import model.produto.CategoriaType
import model.produto.Stock
import navigation.Screen
import repository.ProdutoRepository
import screens.viewmodels.StockViewModel

@Composable
fun StockSection(navigate: MutableState<Screen>) {
    Column(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            Button(onClick = {
                navigate.value = Screen.SAVESTOCK
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = "Stock")
            }
        }
        StockList()
    }
}

@Composable
private fun StockList(produtoRepository: ProdutoRepository = ProdutoRepository()) {
    val stocks = mutableStateListOf<Stock>()

    LaunchedEffect(key1 = Unit) {
        val get = async {
            produtoRepository.findAllStocks()
        }
        stocks.addAll(get.await())
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        TableStockHeader()
        Spacer(modifier = Modifier.height(8.dp))
        ScrollableLazylist {
            LazyColumn(state = it, verticalArrangement = Arrangement.spacedBy(6.dp)) {
                items(items = stocks) { item ->
                    TabelaItemStock(item)
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
            Text(text = "StockId", modifier = Modifier.weight(1f))
            Text(text = "Price", modifier = Modifier.weight(1f))
            Text(text = "Quantidade", modifier = Modifier.weight(1f))
            Text(text = "Fornecedor", modifier = Modifier.weight(0.5f))
        }
    }
}

@Composable
private fun TabelaItemStock(item: Stock) {
    val scope = rememberCoroutineScope()
    Card(modifier = Modifier.fillMaxWidth().height(50.dp).padding(bottom = 2.dp, top = 2.dp), elevation = 2.dp) {
        ProvideTextStyle(value = TextStyle(fontFamily = FontFamily.Monospace, fontWeight = FontWeight.W100)) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "${item.id}", modifier = Modifier.weight(1f))
                Text(text = item.preco.toString(), modifier = Modifier.weight(1f))
                Text(text = item.quantidade.toString(), modifier = Modifier.weight(1f))//TODO() estado
                Text(text = item.fornecedor!!.toString(), modifier = Modifier.weight(0.5f))
            }
        }
    }
}


/**
 *navigate: MutableState<Screen>
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StockForm(
    repository: ProdutoRepository = ProdutoRepository(),
    viewModel: StockViewModel = StockViewModel(),
    onBack: (Screen) -> Unit,
) {
    val scope = rememberCoroutineScope()
    val categorias = mutableListOf<Categoria>()
    val focus = LocalFocusManager.current

    LaunchedEffect(key1 = Unit) {
        val get = async {
            repository.findAllCategorias()
        }
        categorias.addAll(get.await())
        println(categorias.size)
    }

    Box(modifier = Modifier.fillMaxSize().mouseClickable(enabled = true) {
        focus.clearFocus()
    }) {
        IconButton(
            onClick = { onBack(Screen.HOME) },
            modifier = Modifier.size(80.dp).align(Alignment.TopStart).padding(start = 32.dp)
        ) {
            Icon(imageVector = Icons.Default.ArrowBack, null)
        }

        Column(
            modifier = Modifier.fillMaxWidth().align(alignment = Alignment.TopCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "Stock and Product",
                style = MaterialTheme.typography.h4,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace
            )
        }

        Surface(
            elevation = 3.dp,
            shape = RoundedCornerShape(2),
            modifier = Modifier.width(630.dp).height(450.dp).align(Alignment.Center)
        ) {
            Column(
                modifier = Modifier.width(630.dp).height(450.dp).padding(32.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    InputText(label = "Quantidade", onValueChange = {
                        viewModel.onChangeQuantity(it.toInt())
                    })
                    InputText(label = "Stock Price", onValueChange = {
                        viewModel.onChangePrice(it.toInt())
                    })
                }


                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    InputText(label = "Fornecedor", onValueChange = {
                        viewModel.onChangeFornecedor(it.toInt())
                    })

                    Dropdown(label = "Categories", onSelect = { murkupEnum ->
                        println(murkupEnum)

                        println("CAT:: "+categorias.size)

                        val id = categorias.filter { it.categoriaType.name == murkupEnum.getName() }[0].id
                        viewModel.onChangeCategoria(id)

                    }, markupEnum = CategoriaType.values())

                }

                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    InputText(label = "ProdutoName", onValueChange = {
                        viewModel.onChangeProdutoNome(it)
                    })
                    InputText(label = "Produto Price", onValueChange = {
                        viewModel.onChangeProdutoPrice(it.toLong())
                    })

                }


                Button(onClick = {
                    scope.launch {
                        val status = viewModel.save()
                        if (status) {
                            onBack(Screen.HOME)
                        }
                    }
                }) {
                    Text(text = "Save")
                }

            }
        }

    }


}
