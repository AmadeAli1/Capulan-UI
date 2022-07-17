package helpers

import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

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