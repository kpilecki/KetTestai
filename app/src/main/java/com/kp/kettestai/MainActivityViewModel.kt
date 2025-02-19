package com.kp.kettestai

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import com.kp.kettestai.data.QuestionRepositoryImpl
import com.kp.kettestai.questionScreen.Question

class MainActivityViewModel : ViewModel() {
    private val questionRepository = QuestionRepositoryImpl()
    val questions = questionRepository.getQuestions()


}