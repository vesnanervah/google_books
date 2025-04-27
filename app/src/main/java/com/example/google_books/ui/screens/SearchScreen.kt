package com.example.google_books.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SearchScreen(modifier: Modifier = Modifier, onSearchSubmit: (search: String) -> Unit) {
    var value by remember { mutableStateOf("")  }
    Column(modifier, Arrangement.Center, Alignment.CenterHorizontally) {
        Text("Let's find some books!")
        Spacer(Modifier.padding(8.dp))
        TextField(value, { value = it })
        Spacer(Modifier.padding(8.dp))
        Button({
            if (value.isEmpty()){
                // TODO: wrap screen in form and show errors after unsuccessful validation
            } else {
                onSearchSubmit(value)
            }
        })  {
            Text("Submit")
        }
    }
}