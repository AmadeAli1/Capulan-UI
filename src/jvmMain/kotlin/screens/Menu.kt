package screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import theme.backgroundChild
import theme.backgroundParent

@Composable
fun MenuScreen() {
    val focus = LocalFocusManager.current
    Row(
        modifier = Modifier.fillMaxSize().background(Color(0xFF222C41)),
    ) {
        Row(
            modifier = Modifier.weight(2f).fillMaxHeight().padding(5.dp, 5.dp),

            ) {
            Card(
                backgroundColor = Color(0xFFFFFFFF),
                modifier = Modifier.fillMaxWidth().fillMaxHeight(),
//                shape = RoundedCornerShape(15),

                ) {
                Column(
                    modifier = Modifier.padding(12.dp, 36.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(bottom = 5.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
//                        Icon(
//                            imageVector = Icons.Filled.Email, contentDescription = "",
//                            tint=Color.Red
//                        )
                        Text(
                            text = "Supreme Capulan",
                            style = MaterialTheme.typography.h5,
                            textAlign = TextAlign.Center,
                            color = Color.Black,
                            fontWeight = FontWeight.ExtraBold,
                            letterSpacing = 1.sp,
                            fontSize = 16.sp
                        )
                    }
                    Divider(
                        modifier = Modifier.fillMaxWidth().height(4.dp)
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        modifier = Modifier.padding(top = 40.dp, bottom = 20.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Email, contentDescription = "", tint = Color.Red
                        )
                        Button(
                            onClick = {},
                            colors=ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFFFFFF)),
                            border = BorderStroke(1.dp, Color(0xFFFFFFFF)),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "Encomenda",
                                style = MaterialTheme.typography.h5,
                                textAlign = TextAlign.Center,
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 1.sp,
                                fontSize = 14.sp
                            )

                        }

                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        modifier = Modifier.padding(top = 20.dp, bottom = 20.dp)

                    ) {
                        Icon(
                            imageVector = Icons.Filled.Email, contentDescription = "",
                            tint = Color.Red
                        )
                        Button(
                            onClick = {},
                            colors=ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFFFFFF)),
                            border = BorderStroke(1.dp, Color(0xFFFFFFFF)),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "Remover",
                                style = MaterialTheme.typography.h5,
                                textAlign = TextAlign.Center,
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 1.sp,
                                fontSize = 14.sp
                            )

                        }
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        modifier = Modifier.padding(top = 20.dp, bottom = 20.dp)

                    ) {
                        Icon(
                            imageVector = Icons.Filled.Email, contentDescription = "",
                            tint = Color.Red
                        )
                        Button(
                            onClick = {},
                            colors=ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFFFFFF)),
                            border = BorderStroke(1.dp, Color(0xFFFFFFFF)),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "Registrar",
                                style = MaterialTheme.typography.h5,
                                textAlign = TextAlign.Center,
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 1.sp,
                                fontSize = 14.sp
                            )

                        }
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        modifier = Modifier.padding(top = 20.dp, bottom = 20.dp)

                    ) {
                        Icon(
                            imageVector = Icons.Filled.Email, contentDescription = "", tint = Color.Red
                        )
                        Button(
                            onClick = {},
                            colors=ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFFFFFF)),
                            border = BorderStroke(1.dp, Color(0xFFFFFFFF)),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "Visualizar",
                                style = MaterialTheme.typography.h5,
                                textAlign = TextAlign.Center,
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 1.sp,
                                fontSize = 14.sp
                            )

                        }
                    }
                }
            }
//                Column(
//                    modifier = Modifier.fillMaxHeight().fillMaxWidth()
//                ) {
//                    Row(
//                        modifier = Modifier.padding(bottom=40.dp),
//                        verticalAlignment = Alignment.CenterVertically,
//                        horizontalArrangement = Arrangement.spacedBy(25.dp),
//                    ) {
//                        Text("AAA")
//                        Column(
//                            verticalArrangement = Arrangement.spacedBy(5.dp)
//                        ) {
//                            Text("Jose Manuel")
//                        }
//                    }
//                    Card(
//                        backgroundColor=Color(0xFF191B55),
//                        modifier = Modifier.fillMaxWidth().height(100.dp).padding(bottom=13.dp),
//
////                        verticalAlignment = Alignment.CenterVertically,
////                        horizontalArrangement = Arrangement.spacedBy(25.dp)
//                    ) {
//
//                    }
//                    Card(
//                        backgroundColor=Color(0xFF191B55),
//                        modifier = Modifier.fillMaxWidth().height(100.dp).padding(bottom=13.dp),
//                        shape = RoundedCornerShape(15)
////                        verticalAlignment = Alignment.CenterVertically,
////                        horizontalArrangement = Arrangement.spacedBy(25.dp)
//                    ) {
//
//                    }
//                    Card(
//                        backgroundColor=Color(0xFF191B55),
//                        modifier = Modifier.fillMaxWidth().height(100.dp).padding(bottom=13.dp),
//                        shape = RoundedCornerShape(15)
////                        verticalAlignment = Alignment.CenterVertically,
////                        horizontalArrangement = Arrangement.spacedBy(25.dp)
//                    ) {
//
//                    }
//                    Card(
//                        backgroundColor=Color(0xFF191B55),
//                        modifier = Modifier.fillMaxWidth().height(100.dp).padding(bottom=13.dp),
//                        shape = RoundedCornerShape(15)
////                        verticalAlignment = Alignment.CenterVertically,
////                        horizontalArrangement = Arrangement.spacedBy(25.dp)
//                    ) {
//
//                    }
//                }
        }
        Row(
            modifier = Modifier.weight(8f).fillMaxHeight(), verticalAlignment = Alignment.CenterVertically
        ) {

        }
    }

}


//@Composable
//fun MenuScreen() {
//    val focus = LocalFocusManager.current
//    Scaffold(
//        modifier = Modifier.fillMaxSize(),
//        topBar = {
//            TopAppBar(title = {
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.Center
//                ) {
//                    Text(
//                        buildAnnotatedString {
//                            withStyle(
//                                style = SpanStyle(
//                                    color = Color.Black,
//                                    fontWeight = FontWeight.ExtraBold,
//                                    fontSize = 24.sp
//                                )
//                            ) {
//                                append("Welcome to Capulan Enterprise")
//                            }
//                        }
//                    )
//                }
//            }
//            )
//        },
//        bottomBar = {
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                ) {
//                Row (
//                    modifier=Modifier.weight(3f).background(Color.Red).height(50.dp),
//                    verticalAlignment = Alignment.CenterVertically){
//                }
//                Row (
//                    modifier=Modifier.weight(4f).background(Color.Black).height(50.dp),
//                    verticalAlignment = Alignment.CenterVertically){
//                }
//                Row (
//                    modifier=Modifier.weight(3f).background(Color.Red).height(50.dp),
//                    verticalAlignment = Alignment.CenterVertically){
//
//                }
//            }
//        }
//    ) {
//
//    }
////    Box(
////        modifier = Modifier.fillMaxSize().background(color = backgroundParent)
////    ) {
////        Column(
////            modifier = Modifier.align(Alignment.TopCenter).fillMaxWidth().fillMaxHeight(0.1f)
////                .background(color = Color.Red)
////        ) {
////            Text()
////        }
////    }
//}
