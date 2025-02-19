package com.kp.kettestai.data



import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.kp.kettestai.questionScreen.Answer
import com.kp.kettestai.questionScreen.Question
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.ArrayList


class QuestionRepositoryImpl : QuestionRepository {
    val firestore = Firebase.firestore
    val questionsFlow = MutableStateFlow(ArrayList<Question>())

    override fun getQuestions(): MutableStateFlow<ArrayList<Question>> {
        val list = ArrayList<Question>()
//        loadDummyData()
        firestore.collection("questions")
            .get()
            .addOnSuccessListener { result ->
                    for (document in result) {
                        list.add(document.toObject<Question>())
                    }
                questionsFlow.value = list
            }.addOnFailureListener{ error -> Log.d("MyApp",error.message.toString() )}

        return questionsFlow
    }

    fun loadDummyData(){
        if (!firestore.collection("questions").get().isSuccessful){
            val question = Question(
                "Įvažiuodami į sankryžą, kurioje eismas vyksta ratu, visada privalote:",
                listOf(
                    Answer("Sukti į dešinę", true),
                    Answer("Sukti į kairę", false),
                    Answer("Rodyti posūkio į dešinę signalą", false)
                    ),
                image = "gs://ket-testai-v1.firebasestorage.app/question_images/1.jfif"
            )
            firestore.collection("questions")
                .add(question)
                .addOnSuccessListener { documentReference ->
                    Log.d(TAG, "DocumentSnapshot written with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error adding document", e)
                }
        }
    }
}
