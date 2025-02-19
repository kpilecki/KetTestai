package com.kp.kettestai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.coroutineScope
import com.kp.kettestai.questionScreen.QuestionScreen
import com.kp.kettestai.ui.theme.KetTestaiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = MainActivityViewModel()
        enableEdgeToEdge()
        setContent {
            KetTestaiTheme {
                val questions = viewModel.questions.collectAsState(context = this.lifecycle.coroutineScope.coroutineContext)
                for( question in questions.value ){
                    QuestionScreen(question)
                }

            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KetTestaiTheme {

    }
}