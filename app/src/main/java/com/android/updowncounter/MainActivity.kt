package com.android.updowncounter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.updowncounter.ui.theme.UpDownCounterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UpDownCounterTheme {
                // A surface container using the 'background' color from the theme
                Content()
            }
        }
    }
}

@Composable
fun Content() {
    var moneyCount by rememberSaveable {
        mutableStateOf(0)
    }
    Surface(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(), color = Color.Green.copy(alpha = 0.6f)) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "$$moneyCount",
                    style = TextStyle(
                        color = if (moneyCount>=0) Color.White else Color.Red,
                        fontSize = 60.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                )

                Spacer(modifier = Modifier.height(30.dp))
                
                Row {
                    CreateCounter("+" , moneyCount ){
                        moneyCount = it + 1
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    CreateCounter("-" , moneyCount){
                        moneyCount = it -1
                    }

                }

                
            }
    }
}

@Composable
fun CreateCounter(text : String , moneyCount : Int = 0, updateMoneyCount : (Int) -> Unit) {
    Card(modifier = Modifier
        .padding(5.dp)
        .size(120.dp)
        .clip(shape = CircleShape)
        .clickable { updateMoneyCount(moneyCount) },
        backgroundColor = Color.White,
        elevation = 4.dp
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                text = text,
                fontSize = 60.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Green
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    UpDownCounterTheme {

    }
}