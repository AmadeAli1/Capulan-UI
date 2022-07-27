package screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Shop
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import helpers.TabItem
import helpers.TabItem.*
import helpers.TabList
import navigation.Screen
import repository.UserRepository
import java.awt.Cursor


@Composable
fun HomeScreen(navigate: MutableState<Screen>) {
    val items = UserRepository.currentUser!!.jobArea.tabs
    var tabSelected by remember { mutableStateOf(items[0]) }

    Column(
        modifier = Modifier.fillMaxSize()
            .padding(vertical = 24.dp, horizontal = 32.dp)
    ) {
        Header()
        Spacer(modifier = Modifier.height(32.dp))
        Divider()
        Spacer(modifier = Modifier.height(16.dp))
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
            Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth(0.45f)) {
                TabList(
                    onSelect = { tabSelected = it as TabItem },
                    tabItems = items
                )
            }
            when (tabSelected) {
                Admins -> AdminSection()
                Utilizadores -> UsuarioSection(navigate = navigate)
                Produtos -> {
                    ProdutoSection(navigate = navigate)
                }
                Stocks -> {
                    StockSection(navigate = navigate)
                }
                Encomendas -> {
                    EncomendaSection(navigate)
                }
                else -> {

                }
            }
        }
    }
}

@Composable
private fun SearchBar(modifier: Modifier = Modifier, onSearch: (String) -> Unit) {
    var search by remember { mutableStateOf("") }
    TextField(
        value = search,
        onValueChange = {
            search = it
            onSearch(it)
        },
        placeholder = { Text(text = "Search") },
        leadingIcon = { Icon(imageVector = Icons.Outlined.Search, contentDescription = null) },
        colors = TextFieldDefaults.textFieldColors(
            focusedLabelColor = Color.Unspecified,
            unfocusedLabelColor = Color.Unspecified,
            unfocusedIndicatorColor = Color.Unspecified, focusedIndicatorColor = Color.Unspecified
        ), modifier = modifier, shape = RectangleShape
    )
}

@Composable
private fun Header() {
    Row(
        modifier = Modifier.fillMaxWidth()
            .height(50.dp),
        verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(100.dp)) {
            Logo()
            SearchBar(modifier = Modifier.width(400.dp), onSearch = {})
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            HeaderEnd()
        }
    }
}

@Composable
private fun Logo() {
    Row(
        modifier = Modifier.height(60.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Icon(imageVector = Icons.Default.Shop, contentDescription = null, modifier = Modifier.size(50.dp))
        Text(
            text = "Capulan",
            fontFamily = FontFamily.Monospace,
            fontSize = 24.sp,
            maxLines = 1,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun HeaderEnd() {
    val modifier = Modifier.pointerHoverIcon(
        icon = PointerIcon(
            Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)
        )
    )
    IconButton(
        onClick = {}, modifier = modifier
    ) {
        Icon(imageVector = Icons.Outlined.Notifications, contentDescription = null)
    }
    IconButton(
        onClick = {}, modifier = modifier
    ) {
        Icon(imageVector = Icons.Outlined.Settings, contentDescription = null)
    }
    Spacer(modifier = Modifier.width(10.dp))
    Divider(thickness = 1.dp, modifier = Modifier.height(33.dp).width(1.dp), color = Color.LightGray)
    Spacer(modifier = Modifier.width(10.dp))
    Icon(imageVector = Icons.Outlined.AccountCircle, contentDescription = null, modifier = Modifier.size(40.dp))
    Spacer(modifier = Modifier.width(10.dp))
    Text(text = UserRepository.currentUser!!.nome)
}

