package com.stefanprvanovic.paycheckmate.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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

@Composable
fun WorkCard(work: Work) {
    Column(modifier = Modifier.padding(10.dp)) {
        Text(
            text = work.customer,
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.Bold,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.fillMaxWidth()
        )
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(text = work.customerAddress, modifier = Modifier.weight(0.5f))
            Text(
                text = work.dateTime,
                textAlign = TextAlign.End,
                modifier = Modifier.weight(0.5f)
            )
        }

        Text(
            text = work.workDescription,
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.Bold,
            maxLines = 3,
            color = Color.Black,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .fillMaxWidth()
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
                withStyle(style = SpanStyle()) {
                    append("Cena: ")
                }
                withStyle(
                    style = SpanStyle(
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

@Preview(name = "Light", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Dark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DefaultPreview() {
    PayCheckMateTheme {
        Surface {
            WorkCard(
                work = Work(
                    customer = "Stefan Prvanovic",
                    workDescription = "Daleko je isto a jos dalje je i Srbija. Kada sredimo  ovu zemlju nece je niko prepoznati." +
                            " Ovi prevaranti su daleko ludi. Mada nebo je iznad nas.",
                    customerAddress = "Cara Lazara 46, Cicevac",
                    dateTime = "10:38, 12.03.2023",
                    price = "100",
                    payed = false
                )
            )
        }
    }
}