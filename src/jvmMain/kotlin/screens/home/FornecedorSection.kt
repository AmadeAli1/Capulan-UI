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
import helpers.InputText
import helpers.ScrollableLazylist
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import model.produto.Fornecedor
import navigation.Screen
import repository.ProdutoRepository
import screens.viewmodels.FornecedorViewModel

@Composable
fun FornecedorSection(navigate: MutableState<Screen>) {
    Column(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp), horizontalArrangement = Arrangement.End) {
            Button(onClick = {
                navigate.value = Screen.SAVEFORNECEDOR
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = "Fornecedor")
            }
        }
        FornecedorList()
    }
}


@Composable
fun FornecedorList(repository: ProdutoRepository = ProdutoRepository()) {
    val stocks = mutableStateListOf<Fornecedor>()
    LaunchedEffect(key1 = Unit) {
        val get = async {
            repository.findAllFornecedores()
        }
        stocks.addAll(get.await())
    }
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        TableStockHeader()
        Spacer(modifier = Modifier.height(8.dp))
        ScrollableLazylist {
            LazyColumn(state = it, verticalArrangement = Arrangement.spacedBy(6.dp)) {
                items(items = stocks) { item ->
                    TabelaItemFornecedor(item)
                }
            }
        }

    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FornecedorForm(
    viewModel: FornecedorViewModel = FornecedorViewModel(),
    onBack: (Screen) -> Unit,
) {
    val focus = LocalFocusManager.current
    val scope = rememberCoroutineScope()

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
            modifier = Modifier.width(500.dp).height(350.dp).align(Alignment.Center)
        ) {
            Column(
                modifier = Modifier.width(500.dp).height(350.dp).padding(32.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                InputText(label = "Empresa", onValueChange = {
                    viewModel.onChangeEmpresa(it)
                })

                InputText(label = "Email", onValueChange = {
                    viewModel.onChangeEmail(it)
                })

                InputText(label = "Contacto", onValueChange = {
                    viewModel.onChangeContacto(it)
                })

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
            Text(text = "FornecedorId", modifier = Modifier.weight(1f))
            Text(text = "Empresa", modifier = Modifier.weight(1f))
            Text(text = "Contacto", modifier = Modifier.weight(1f))
            Text(text = "Email", modifier = Modifier.weight(0.5f))
        }
    }
}


@Composable
private fun TabelaItemFornecedor(item: Fornecedor) {
    val scope = rememberCoroutineScope()
    Card(modifier = Modifier.fillMaxWidth().height(50.dp).padding(bottom = 2.dp, top = 2.dp), elevation = 2.dp) {
        ProvideTextStyle(value = TextStyle(fontFamily = FontFamily.Monospace, fontWeight = FontWeight.W100)) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "${item.id}", modifier = Modifier.weight(1f))
                Text(text = item.nomeEmpresa!!, modifier = Modifier.weight(1f))
                Text(text = item.contacto!!, modifier = Modifier.weight(1f))//TODO() estado
                Text(text = item.email!!, modifier = Modifier.weight(0.5f))
            }
        }
    }
}