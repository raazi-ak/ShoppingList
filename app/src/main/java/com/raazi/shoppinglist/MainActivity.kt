package com.raazi.shoppinglist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.raazi.shoppinglist.ui.theme.ShoppingListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShoppingListTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var sItems by remember{ mutableStateOf(listOf<ShoppingList>()) }
                    Column(modifier= Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center){
                        androidx.compose.material3.Button(onClick = { /*TODO*/ }, modifier= Modifier.align(Alignment.CenterHorizontally)) {
                            Text(text = "Add Item")

                        }
                        LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)){
                            items(sItems){

                            }
                        }
                    }
                }
            }
        }
    }
}

data class ShoppingList(val id:Int,
                        var name:String,
                        var quantity:Int,
                        var isEditing:Boolean = false)





