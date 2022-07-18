package helpers

import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.awt.Cursor

/**
 * @see LazyListState
 * @see ScrollableLazylist
 * @author Amade Ali
 * <p>Coluna com scrollvertical para uma lista dinamica de items
 */
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


/**
 * @author Amade Ali
 * <p>TabLayout para navegacao entre menus
 */
@Composable
fun TabList(
    onSelect: (MarkupEnum) -> Unit,
    tabItems: Array<out MarkupEnum>,
) {
    var selectedIndex by remember { mutableStateOf(0) }

    TabRow(
        selectedTabIndex = selectedIndex,
        tabs = {
            tabItems.forEachIndexed { index, tabItem ->
                Tab(
                    text = { Text(text = tabItem.getName()) },
                    selected = selectedIndex == index,
                    onClick = {
                        selectedIndex = index
                        onSelect(tabItem)
                    })
            }
        },
        divider = {},
    )
}

private val customColor = Color(0xFFF8F9FC)

@Composable
fun InputText(
    modifier: Modifier = Modifier,
    label: String,
    isError: Boolean? = null,
    onValueChange: (String) -> Unit
) {
    var text by remember { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = {
                text = it
                onValueChange(text)
            },
            label = {
                Text(text = label)
            },
            trailingIcon = {
                if (isError != null) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        tint = if (isError) Color.Red else Color.Green,
                        contentDescription = null
                    )
                }
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedLabelColor = Color.Unspecified,
                unfocusedLabelColor = Color.Unspecified
            ), modifier = modifier
        )
    }
}

@Composable
fun CustomButton(
    text: String,
    backgroundColor: Color? = null,
    textColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults
            .outlinedButtonColors(backgroundColor = backgroundColor ?: LocalContentColor.current)
    ) {
        Text(text = text, color = textColor, fontWeight = FontWeight.SemiBold)
    }
}

@Composable
fun Dropdown(
    modifier: Modifier = Modifier,
    markupEnum: Array<out MarkupEnum>,
    onSelect: (MarkupEnum) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var labeltext by remember { mutableStateOf(markupEnum[0]) }
    var selected by remember { mutableStateOf(0) }

    val icon by derivedStateOf {
        if (expanded) {
            Icons.Filled.ArrowDropUp
        } else {
            Icons.Filled.ArrowDropDown
        }
    }

    Box {
        Row(
            modifier = Modifier.width(200.dp),
            verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                value = labeltext.getName(),
                label = { Text(text = "Genre") },
                onValueChange = {},
                trailingIcon = {
                    IconButton(onClick = {
                        expanded = !expanded
                    }) { Icon(imageVector = icon, null) }
                }, readOnly = true,
                modifier = Modifier
                    .pointerHoverIcon(icon = PointerIcon(cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)))
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = {
                    expanded = false
                }, modifier = Modifier.width(200.dp)
            ) {
                Column(modifier = Modifier.width(200.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    markupEnum.forEachIndexed { index, regiao ->
                        ClickableText(
                            text = AnnotatedString(
                                text = regiao.getName(), spanStyle = SpanStyle(
                                    color = if (selected == index) Color.Blue else LocalContentColor.current,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp
                                )
                            )
                        ) {
                            selected = index
                            onSelect(regiao)
                            labeltext = regiao
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun InputSenha(
    modifier: Modifier = Modifier,
    label: String,
    onValueChange: (String) -> Unit
) {
    var senha by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }

    val iconEyes = if (showPassword) Icons.Outlined.Visibility else Icons.Outlined.VisibilityOff

    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        OutlinedTextField(
            value = senha,
            onValueChange = {
                senha = it
                onValueChange(senha)
            },
            label = {
                Text(text = label)
            },
            trailingIcon = {
                IconButton(
                    onClick = { showPassword = !showPassword }, modifier = Modifier.pointerHoverIcon(
                        icon = PointerIcon(
                            Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)
                        )
                    )
                ) {
                    Icon(imageVector = iconEyes, null)
                }
            },
            visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedLabelColor = Color.Unspecified,
                unfocusedLabelColor = Color.Unspecified
            ), modifier = modifier
        )
    }
}






















