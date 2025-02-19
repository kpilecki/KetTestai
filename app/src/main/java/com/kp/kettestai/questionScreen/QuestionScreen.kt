package com.kp.kettestai.questionScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kp.kettestai.ui.theme.KetTestaiTheme

@Composable
fun QuestionScreen(question: Question){
    Column {
        Row(modifier = Modifier
            .weight(1f, fill = true)
            .background(color = MaterialTheme.colorScheme.surfaceContainer)
        ) {
            Text(text = question.questionText, textAlign = TextAlign.Center, color = MaterialTheme.colorScheme.onSurface)
        }
        HorizontalDivider(color = Color.Gray)
        Column {
            question.answers.forEach({ QuestionAnswer(it.answerText) })
        }
    }

}

@Composable
fun QuestionAnswer(answerText : String = ""){
    var isChecked by rememberSaveable { mutableStateOf(false) }
    val backgroundColor = if (isChecked) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primaryContainer
    val textColor = if (isChecked) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onPrimaryContainer

    Row(
        modifier = Modifier
            .padding(10.dp)
            .background( backgroundColor, RoundedCornerShape(10.dp))
            .border(width = 1.dp, color = MaterialTheme.colorScheme.outline, shape = RoundedCornerShape(10.dp))
            .clickable { isChecked = !isChecked }

    ) {
        Text(text = answerText,
            modifier = Modifier
                .weight(1f, fill = true)
                .padding(5.dp),
            color = textColor
        )
        Checkbox(checked = isChecked, onCheckedChange = {isChecked = !isChecked}
            , colors = CheckboxDefaults.colors())
    }
}


@Preview(showBackground = true, widthDp = 400, heightDp = 800)
@Composable
fun QuestionPreview(){
    KetTestaiTheme {
        QuestionScreen(Question(
            questionText = "Kodėl pravažiavęs (-usi) posūkį turite įsitikinti, kad posūkio signalas išjungtas?",
            answers = listOf(
            Answer("Kad negadinčiau posūkio indikatoriaus perjungiklio (rėlės)", false),
            Answer("Kad neklaidinčiau kitų eismo dalyvių", false),
            Answer("Kad neakinčiau kitų eismo dalyvių", false),
            Answer("Kad nesekinčiau akumuliatoriaus", true)),
            image = ""
        ))
    }
}
