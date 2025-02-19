package com.kp.kettestai.questionScreen

data class Question(
    val questionText: String = "",
    val answers: List<Answer> = emptyList(),
    val image: String = ""
)
