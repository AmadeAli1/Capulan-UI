package screens

import `object`.Account
import `object`.MessageValidation
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.mouseClickable
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import theme.backgroundChild
import theme.backgroundParent
import java.awt.Cursor

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun LoginScreen(login: Account = Account()) {
    val focus = LocalFocusManager.current
    Box(
        modifier = Modifier.fillMaxSize().background(color = backgroundParent),
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize().mouseClickable(enabled = true) {
                focus.clearFocus()
            }
        ) {
            Card(backgroundColor = backgroundChild.copy(alpha = 0.6f)) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(64.dp)
                ) {
                    Text(
                        text = "Supreme Capulan",
                        style = MaterialTheme.typography.h5,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colors.background,
                        fontWeight = FontWeight.ExtraBold,
                        letterSpacing = 1.sp, fontSize = 27.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    CodigoInput(login.codigoValidationMessage) { codigo ->
                        login.addCodigo(codigo)
                    }

                    PasswordInput(login.passwordValidationMessage) { senha ->
                        login.addPassword(senha)
                    }
                    ButtonLogin {
                        login.login()
                    }
                }
            }
        }
    }
}


@Composable
private fun CodigoInput(validation: MessageValidation, onValueChange: (String) -> Unit) {
    var code by remember { mutableStateOf("") }
    val clearText = derivedStateOf {
        code.isNotBlank()
    }.value
    OutlinedTextField(
        value = code,
        onValueChange = { value ->
            code = value
            onValueChange(value)
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = backgroundParent),
        modifier = Modifier.width(310.dp),
        label = { Text(text = "Codigo") },
        trailingIcon = {
            if (clearText) {
                IconButton(
                    onClick = { code = "" }, modifier = Modifier.pointerHoverIcon(
                        icon = PointerIcon(
                            Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)
                        )
                    )
                ) {
                    Icon(imageVector = Icons.Default.Clear, null)
                }
            }
        },
        isError = !validation.status
    )
}

@Composable
private fun PasswordInput(validation: MessageValidation, onValueChange: (String) -> Unit) {
    var senha by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }

    val iconEyes = if (showPassword) Icons.Outlined.Visibility else Icons.Outlined.VisibilityOff

    OutlinedTextField(
        value = senha,
        onValueChange = { value ->
            senha = value
            onValueChange(value)
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(focusedBorderColor = backgroundParent),
        modifier = Modifier.width(310.dp),
        label = { Text(text = "Senha") },
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
        isError = !validation.status
    )
}

@Composable
private fun ButtonLogin(function: () -> Unit) {

    OutlinedButton(
        onClick = {
//            function()
            function.invoke()
        }, modifier = Modifier.width(310.dp)
            .pointerHoverIcon(
                icon = PointerIcon(
                    Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)
                )
            ),
        colors = ButtonDefaults.buttonColors(backgroundColor = backgroundParent)
    ) {
        Text(
            buildAnnotatedString {
                withStyle(style = SpanStyle(color = backgroundChild)) {
                    append("Entrar")
                }
            }
        )

    }
}


@Composable
private fun meuLoginJose() {
    var click = remember { mutableStateOf(false) }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize().background(color = Color.Red)
    ) {
        Column(
            modifier = Modifier.background(color = Color.Gray),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                "JOSE",
                onValueChange = {},
                colors = TextFieldDefaults.outlinedTextFieldColors(focusedLabelColor = Color.Red)
            )
            meuButton(click)
            if (click.value) {
                BoxJose()
            }
        }
    }
}

@Composable
private fun meuButton(click: MutableState<Boolean>) {
    Button(onClick = {
        click.value = !click.value
    }) {
        Text("Click")
    }
}

@Composable
private fun BoxJose() {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize(0.5f).background(color = Color.Blue)) {

        }
        Column(
            modifier = Modifier.fillMaxSize(0.5f)
                .background(color = Color.Red).align(Alignment.TopEnd)
        ) {

        }

        Column(
            modifier = Modifier.height(50.dp).width(50.dp)
                .background(color = Color.Gray).align(Alignment.BottomStart)
        ) {

        }

    }
}

