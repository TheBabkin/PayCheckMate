package com.stefanprvanovic.paycheckmate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.stefanprvanovic.paycheckmate.database.Work
import com.stefanprvanovic.paycheckmate.ui.theme.PayCheckMateTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PayCheckMateTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
}

@Composable
fun WorkCard(work: Work) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(10.dp)
            .clickable { },
        elevation = 10.dp
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Text(
                text = work.customerName,
                style = MaterialTheme.typography.h4,
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth()
            )
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(text = work.customerAddress, modifier = Modifier.weight(0.5f))
                Text(
                    text = "10:36, 23.05.2023",
                    textAlign = TextAlign.End,
                    modifier = Modifier.weight(0.5f)
                )
            }

            Text(
                text = work.workDescription,
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.Bold,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .background(Color.LightGray)
                    .border(BorderStroke(width = 1.dp, color = Color.Black))
                    .padding(5.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 10.dp, 0.dp, 0.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(buildAnnotatedString {
                    withStyle(style = SpanStyle(Color.Black)) {
                        append("Cena: ")
                    }
                    withStyle(
                        style = SpanStyle(
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                    ) {
                        append(work.price.toString())
                    }
                }, modifier = Modifier.weight(0.2f))

                Text(
                    text = "Placeno: ",
                    textAlign = TextAlign.End,
                    modifier = Modifier.weight(0.2f)
                )
                val color: Color = if (work.payed) Color.Green else Color.Red
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(23.dp)
                        .clip(CircleShape)
                        .background(color)
                        .weight(0.1f)
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    PayCheckMateTheme {
        WorkCard(
            work = Work(
                customerName = "Stefan Prvanovic",
                workDescription = stringResource(id = R.string.lorem),
                customerAddress = "Cara Lazara 46, Cicevac",
                dateTime = "10:38, 12.03.2023",
                price = 100,
                payed = false
            )
        )
    }
}