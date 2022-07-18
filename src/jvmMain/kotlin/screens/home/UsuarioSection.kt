package screens.home


import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.mouseClickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import helpers.*
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import model.actores.Cliente
import model.actores.Empregado
import model.actores.Genre
import model.actores.UserType
import model.location.Region
import navigation.Screen
import repository.UserRepository
import screens.viewmodels.ClienteViewModel

@Composable
@Preview
fun UsuarioSection(navigate: MutableState<Screen>) {
    Container(navigate = navigate)
}

@Composable
private fun ClienteTableHeader() {
    ProvideTextStyle(
        value = TextStyle(
            fontWeight = FontWeight.W600, fontSize = 18.sp, fontFamily = FontFamily.Monospace
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = "Id", modifier = Modifier.weight(0.5f))
            Text(text = "First Name", modifier = Modifier.weight(2f))
            Text(text = "Last Name", modifier = Modifier.weight(2f))
            Text(text = "Email", modifier = Modifier.weight(2f))
            Text(text = "Regiao", modifier = Modifier.weight(0.5f))
            Text(text = "Type", modifier = Modifier.weight(0.5f))
            Spacer(modifier = Modifier.weight(0.2f))
            Spacer(modifier = Modifier.weight(0.2f))
        }
    }
}

@Composable
private fun Container(navigate: MutableState<Screen>) {
    val scope = rememberCoroutineScope()
    var currentView by remember { mutableStateOf(UserType.CLIENTE) }

    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(modifier = Modifier.wrapContentWidth(Alignment.Start).width(300.dp)) {
                ChooseUser {
                    if (it == UserType.CLIENTE) {
                        scope.launch {
                            if (currentView != it) {
                                currentView = it
                            }
                        }
                    } else {
                        scope.launch {
                            if (currentView != it) {
                                currentView = it
                            }
                        }
                    }
                }
            }
            Button(onClick = {
                navigate.value = Screen.SAVECLIENTE
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
                Text(text = if (currentView == UserType.CLIENTE) "Cliente" else "Funcionario")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        when (currentView) {
            UserType.CLIENTE -> ShowClientes()
            else -> ShowEmpregados()
        }

    }
}

@Composable
private fun ShowEmpregados() {
    val funcionarios = mutableStateListOf<Empregado>()

    LaunchedEffect(key1 = Unit) {
        val get = async {
            UserRepository.findAllEmpregados()
        }
        funcionarios.addAll(get.await())
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        EmpregadoTableHeader()
        Spacer(modifier = Modifier.height(8.dp))
        ScrollableLazylist {
            LazyColumn(state = it, verticalArrangement = Arrangement.spacedBy(6.dp)) {
                items(items = funcionarios) { item ->
                    TableItemEmpregado(item)
                }
            }
        }

    }
}

@Composable
private fun ShowClientes() {
    val clientes = mutableStateListOf<Cliente>()

    LaunchedEffect(key1 = Unit) {
        val get = async {
            UserRepository.findAllClientes()
        }
        clientes.addAll(get.await())
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        ClienteTableHeader()
        Spacer(modifier = Modifier.height(8.dp))
        ScrollableLazylist {
            LazyColumn(state = it, verticalArrangement = Arrangement.spacedBy(6.dp)) {
                items(items = clientes) { item ->
                    TableItemCliente(item)
                }
            }
        }

    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ShowFormCliente(
    viewModel: ClienteViewModel = ClienteViewModel(),
    onSave: (Boolean) -> Unit,
    onBack: (Screen) -> Unit
) {
    val focus = LocalFocusManager.current
    val scope = rememberCoroutineScope()
    Column(modifier = Modifier.fillMaxSize()) {

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
                    text = "Cliente information",
                    style = MaterialTheme.typography.h4,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace
                )

            }

            Surface(
                elevation = 3.dp,
                shape = RoundedCornerShape(2),
                modifier = Modifier.width(630.dp).height(500.dp).align(Alignment.Center)
            ) {
                Column(
                    modifier = Modifier.width(630.dp).height(500.dp).padding(32.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    InputText(modifier = Modifier.fillMaxWidth(),
                        label = "Firstname",
                        onValueChange = { viewModel.onChangeFirstname(it) })
                    InputText(modifier = Modifier.fillMaxWidth(),
                        label = "LastName",
                        onValueChange = { viewModel.onChangeLastname(it) })

                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        InputText(modifier = Modifier, label = "Email", onValueChange = { viewModel.onChangeEmail(it) })
                        InputSenha(
                            modifier = Modifier,
                            label = "Senha",
                            onValueChange = { viewModel.onChangeSenha(it) })
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Dropdown(markupEnum = Genre.values()) {
                            viewModel.onChangeSexo(it as Genre)
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        InputText(modifier = Modifier.fillMaxWidth(),
                            label = "PostalCode",
                            onValueChange = { viewModel.onChangePostalCode(it) })
                    }


                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        InputText(modifier = Modifier, label = "BI Code", onValueChange = { viewModel.onChangeBi(it) })
                        InputText(
                            modifier = Modifier,
                            label = "Cidade",
                            onValueChange = { viewModel.onChangeCity(it) })
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = {
                        scope.launch {
                            viewModel.save()
                            onSave(false)
                        }
                    }, modifier = Modifier.align(Alignment.CenterHorizontally)) {
                        Text(text = "Gravar")
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(imageVector = Icons.Default.Save, contentDescription = null)
                    }
                }
            }


        }
    }

}

@Composable
private fun Padding(all: Dp, content: @Composable (Modifier) -> Unit) {
    content(Modifier.padding(all))
}

@Composable
private fun EmpregadoTableHeader() {
    ProvideTextStyle(
        value = TextStyle(
            fontWeight = FontWeight.W600, fontSize = 18.sp, fontFamily = FontFamily.Monospace
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = "Id", modifier = Modifier.weight(0.5f))
            Text(text = "First Name", modifier = Modifier.weight(2f))
            Text(text = "Last Name", modifier = Modifier.weight(2f))
            Text(text = "JobArea", modifier = Modifier.weight(2f))
            Text(text = "Salario", modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.weight(0.2f))
            Spacer(modifier = Modifier.weight(0.2f))
        }
    }
}

@Composable
private fun TableItemEmpregado(item: Empregado) {
    Card(modifier = Modifier.fillMaxWidth().height(50.dp).padding(bottom = 2.dp, top = 2.dp), elevation = 2.dp) {
        ProvideTextStyle(value = TextStyle(fontFamily = FontFamily.Monospace, fontWeight = FontWeight.W100)) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val fullname = item.nome.split(" ")
                Text(text = "${item.id}", modifier = Modifier.weight(0.5f))
                Text(text = fullname[0], modifier = Modifier.weight(2f))
                Text(text = fullname[1], modifier = Modifier.weight(2f))
                Text(text = item.jobArea.name, modifier = Modifier.weight(2f))
                Text(text = "${item.salario} MT", modifier = Modifier.weight(1f))

                IconButton(onClick = {}, modifier = Modifier.weight(0.2f)) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = null)
                }

                IconButton(onClick = {}, modifier = Modifier.weight(0.2f)) {
                    Icon(imageVector = Icons.Default.Update, contentDescription = null)
                }

            }
        }
    }
}

@Composable
private fun ChooseUser(onSelected: (UserType) -> Unit) {
    if (UserRepository.currentUser!!.jobArea != UserType.FUNCIONARIO) {
        TabList(onSelect = {
            onSelected(it as UserType)
        }, tabItems = arrayOf(UserType.CLIENTE, UserType.FUNCIONARIO))
    } else {
        TabList(onSelect = {
            onSelected(it as UserType)
        }, tabItems = arrayOf(UserType.CLIENTE))
    }
}

@Composable
private fun TableItemCliente(cliente: Cliente) {
    Card(modifier = Modifier.fillMaxWidth().height(50.dp).padding(bottom = 2.dp, top = 2.dp), elevation = 2.dp) {
        ProvideTextStyle(value = TextStyle(fontFamily = FontFamily.Monospace, fontWeight = FontWeight.W100)) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val fullname = cliente.nome.split(" ")
                Text(text = "${cliente.id}", modifier = Modifier.weight(0.5f))
                Text(text = fullname[0], modifier = Modifier.weight(2f))
                Text(text = fullname[1], modifier = Modifier.weight(2f))
                Text(
                    text = cliente.email, modifier = Modifier.weight(2f), color = Color.Blue.copy(alpha = 0.8f)
                )
                Text(text = cliente.cidade, modifier = Modifier.weight(0.5f))
                Text(text = cliente.userType.name, modifier = Modifier.weight(0.5f))

                IconButton(onClick = {}, modifier = Modifier.weight(0.2f)) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = null)
                }

                IconButton(onClick = {}, modifier = Modifier.weight(0.2f)) {
                    Icon(imageVector = Icons.Default.Update, contentDescription = null)
                }
            }
        }
    }
}
