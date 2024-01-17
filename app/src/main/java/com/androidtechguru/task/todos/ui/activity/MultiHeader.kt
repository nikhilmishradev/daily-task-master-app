@file:OptIn(ExperimentalFoundationApi::class, ExperimentalFoundationApi::class,
    ExperimentalMaterial3Api::class
)

package com.androidtechguru.task.todos.ui.activity

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


data class ListItem(val header: String, val content: String)

val listData = listOf(
    ListItem("Header 1", "Item 1"),
    ListItem("Header 1", "Item 2"),
    ListItem("Header 2", "Item 3"),
    ListItem("Header 2", "Item 4"),
    ListItem("Header 2", "Item 5"),
    ListItem("Header 3", "Item 6"),
    ListItem("Header 3", "Item 7"),
    ListItem("Header 4", "Item 7"),
    ListItem("Header 4", "Item 8"),
    ListItem("Header 4", "Item 8"),
    ListItem("Header 4", "Item 8"),
    ListItem("Header 4", "Item 8"),
    ListItem("Header 4", "Item 8"),
    ListItem("Header 4", "Item 8"),
    ListItem("Header 4", "Item 8"),
    ListItem("Header 4", "Item 8"),
    ListItem("Header 4", "Item 8"),
    ListItem("Header 4", "Item 8"),
    ListItem("Header 4", "Item 8"),
    ListItem("Header 3", "Item 8"),
    // Add more items as needed
)

@Preview(showBackground = true)
@Composable
fun HeaderView(){
    MultipleLists()
}

@Composable
fun MultipleLists() {
    val listState = rememberLazyListState()
    val groupItems = listData.groupBy { it.header }

    LazyColumn(
        state = listState,
        modifier = Modifier.fillMaxWidth()
    ) {
        // Input field for filtering items
//        SearchBar()
        // Create separate lists with headers
        stickyHeader {
            Text(
                text = "HEADER...",
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                style = MaterialTheme.typography.h6
            )
        }
        item {

            listData.groupBy { it.header }.forEach { (header, items) ->
                Text(
                    text = header,
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.h6
                )
                Divider(color = Color.Gray, thickness = 1.dp)
                ListWithHeader(listState, items)
            }
        }
    }
}

@Composable
fun SearchBar() {
    var searchQuery by remember { mutableStateOf(TextFieldValue()) }

    TextField(
        value = searchQuery,
        onValueChange = { searchQuery = it },
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        textStyle = MaterialTheme.typography.body1,
        placeholder = { Text(text = "Search...") }
    )
}

@Composable
fun ListWithHeader(listState: LazyListState, items: List<ListItem>, text:String="") {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        items.forEach {

            Text(
                text = it.content,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                style = MaterialTheme.typography.body1
            )
        }
    }
}
