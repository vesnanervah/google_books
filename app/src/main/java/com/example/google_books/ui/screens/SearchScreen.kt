package com.example.google_books.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
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
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SearchScreen(modifier: Modifier = Modifier, onSearchSubmit: (search: String) -> Unit) {
    val focusManager = LocalFocusManager.current
    var value by remember { mutableStateOf("")  }
    var showError by remember { mutableStateOf(false) }

    Column(modifier, Arrangement.Center, Alignment.CenterHorizontally) {
        TextField(
            value,
            { value = it },
            isError = showError,
            singleLine = true,
            placeholder = {
                Text("Let's find some books!")
            },
            supportingText = {
                if (showError) {
                    Text("Try search something else")
                }
            },
            keyboardActions = KeyboardActions(
                onDone = { submit(value, focusManager, { showError = true }, onSearchSubmit) }
            ),
        )
        Spacer(Modifier.padding(8.dp))
        Button({
            submit(value, focusManager, { showError = true }, onSearchSubmit)
        })  {
            Text("Search")
        }
    }
}

private fun submit( value: String, focusManager: FocusManager, onErrorOccured: () -> Unit, onSearchSubmit: (search: String) -> Unit) {
    focusManager.clearFocus()
    if (value.isEmpty()){
        onErrorOccured()
    } else {
        onSearchSubmit(value)
    }
}