package com.kp.kettestai.data

import com.kp.kettestai.questionScreen.Question
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.ArrayList

interface QuestionRepository{
    fun getQuestions(): MutableStateFlow<ArrayList<Question>>
}