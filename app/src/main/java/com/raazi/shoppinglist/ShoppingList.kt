package com.raazi.shoppinglist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingListApp() {
    var showDialog by remember { mutableStateOf(false) }
    var sItems by remember { mutableStateOf(listOf<ShoppingList>()) }
    var itemName by remember {
        mutableStateOf("")
    }
    var itemQty by remember {
        mutableIntStateOf(0)
    }
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
        Button(onClick = { showDialog = true},
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Add Item")

        }
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)) {
            items(sItems) {

            }
        }
    }

    if (showDialog){
       AlertDialog(modifier= Modifier.padding(16.dp),
           onDismissRequest = { showDialog=false },
           confirmButton = { /*TODO*/ },
           title = {Text("Add List Item")},
           text = {
               OutlinedTextField(
                   modifier= Modifier.padding(8.dp),
                   value = itemName ,
                   singleLine = true,
                   onValueChange = {
                   itemName=it
               } )

       })
    }
}

data class ShoppingList(val id:Int,
                        var name:String,
                        var quantity:Int,
                        var isEditing:Boolean = false)



