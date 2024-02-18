package com.raazi.shoppinglist

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
        mutableStateOf("")
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
                    item ->
                if(item.isEditing){
                    ShoppingListEditor(item = item, onEditComplete = {
                        editedName, editedQuantity ->
                        sItems = sItems.map( it.copy(isEditing = false)
                        val editedItem = sItems.find(it.id == item.id )}
                    })
                }
                else {
                    ShoppingListItem(item = , onEditClick = { /*TODO*/ }) {
                        
                    }
                }
            }
        }
    }

    if (showDialog){
        Box {
            AlertDialog(modifier = Modifier.padding(16.dp),
                onDismissRequest = { showDialog = false },
                confirmButton = {
                                Row(modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(4.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween){
                                    Button(onClick = {
                                        if (itemName.isNotBlank()){
                                            val newItem = ShoppingList(
                                                id = sItems.size + 1,
                                                name = itemName,
                                                quantity = itemQty.toInt()
                                            )
                                            sItems+=newItem
                                            showDialog = false
                                            itemName=""
                                            itemQty = ""

                                        }
                                    }) {
                                        Icon(Icons.Default.Add, contentDescription ="Add item to list" )

                                        Text(text = "Add")

                                    }
                                    Button(onClick = { showDialog=false }) {
                                        Icon(Icons.Default.Close , contentDescription ="Cancel Action" )
                                        Text(text = "Cancel")
                                    }                                }
                },
                title = { Text("Add List Item") },
                text = {
                    Column {
                        OutlinedTextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            value = itemName,
                            label = { Text("Item Name") },
                            singleLine = true,
                            onValueChange = {
                                itemName = it
                            })
                        OutlinedTextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            value = itemQty,
                            label = { Text("Item Qty.") },
                            singleLine = true,
                            onValueChange = {
                                itemQty = it
                            })

                    }

                })
        }
    }
}

@Composable
fun ShoppingListEditor(item: ShoppingList, onEditComplete: (String, Int) -> Unit){
    var editedName by remember {
        mutableStateOf(item.name)
    }
    var editedQuantity by remember {
        mutableStateOf(item.quantity.toString())
    }
    var isEditing by remember {
        mutableStateOf(item.isEditing)
    }

    Row(modifier= Modifier
        .fillMaxWidth()
        .background(Color.White)
        .padding(8.dp), horizontalArrangement = Arrangement.SpaceEvenly) {
        Column {
            BasicTextField(
                value = editedName,
                onValueChange = { editedName = it },
                singleLine = true,
                modifier= Modifier
                    .wrapContentSize()
                    .padding(8.dp))
            BasicTextField(
                value = editedQuantity,
                onValueChange = { editedQuantity = it },
                singleLine = true,
                modifier= Modifier
                    .wrapContentSize()
                    .padding(8.dp))

        }
        Button(onClick = { isEditing=false
                            onEditComplete(editedName, editedQuantity.toIntOrNull()?:1)}
                            ) {
            Text("Save")
        }
    }

}

@Composable
fun ShoppingListItem(
    item:ShoppingList,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
){

    Row(modifier= Modifier.run {
        padding(8.dp)
            .fillMaxWidth()
            .border(
                BorderStroke(2.dp, Color.Cyan),
                shape = RoundedCornerShape(15.dp)

            )
    }) {
        Text(text = item.name, modifier=Modifier.padding(8.dp))
        Text(text = "Qty. ${item.quantity.toString()}", modifier = Modifier.padding(8.dp))
    }
    Row(modifier=Modifier.padding(8.dp)){
        IconButton(onClick = onEditClick) {
            Icon(imageVector = Icons.Default.Edit, contentDescription = null)
        }
        IconButton(onClick = onDeleteClick) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = null)
        }

    }
}

data class ShoppingList(val id:Int,
                        var name:String,
                        var quantity:Int,
                        var isEditing:Boolean = false)



