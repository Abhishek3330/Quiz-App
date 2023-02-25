package com.example.quizapp.activites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.R
import com.example.quizapp.adapters.OptionAdapter
import com.example.quizapp.models.Question
import com.example.quizapp.models.Quiz
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson

class QuestionActivity : AppCompatActivity() {

    private var quizzes :MutableList<Quiz>? = null
    private var questions :MutableMap<String , Question>? = null
    private var index = 1

    private lateinit var optionList: RecyclerView
    private lateinit var description: TextView
    private lateinit var btnNext: Button
    private lateinit var btnPrevious: Button
    private lateinit var btnSubmit: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)
        optionList = findViewById(R.id.optionList)
        description = findViewById(R.id.description)
        btnNext = findViewById(R.id.btnNext)
        btnPrevious = findViewById(R.id.btnPrevious)
        btnSubmit = findViewById(R.id.btnSubmit)

        setUpFirestore()
        setEventListener()

    }

    private fun setEventListener() {
        btnPrevious.setOnClickListener {
            index--
            bindView()
        }
        btnNext.setOnClickListener {
            index++
            bindView()
        }
        btnSubmit.setOnClickListener {

            val intent = Intent(this,ResultActivity::class.java)
            val json = Gson().toJson(quizzes!![0])
            intent.putExtra("QUIZ",json)
            startActivity(intent)
        }
    }

    private fun setUpFirestore() {
        val firestore = FirebaseFirestore.getInstance()
        var date = intent.getStringExtra("DATE")
        if (date != null) {
            firestore.collection("quizzes").whereEqualTo("title", date)
                .get()
                .addOnSuccessListener {
                    if (it != null && !it.isEmpty()) {
                        quizzes =  it.toObjects(Quiz::class.java)
                        questions = quizzes!![0].questions
                        bindView()
                    }
                }
        }

    }

    private fun bindView() {

        btnSubmit.visibility = View.GONE
        btnNext.visibility = View.GONE
        btnPrevious.visibility = View.GONE

        if (index == 1){
            btnNext.visibility = View.VISIBLE
        }
        else if (index == questions!!.size ){
            btnSubmit.visibility = View.VISIBLE
            btnPrevious.visibility = View.VISIBLE
        }
        else{
            btnNext.visibility = View.VISIBLE
            btnPrevious.visibility = View.VISIBLE
        }

        val question = questions!!["question$index"]
        question?.let {
            description.text = question.description
            val optionAdapter = OptionAdapter(this, question)
            optionList.layoutManager = LinearLayoutManager(this)
            optionList.adapter = optionAdapter
            optionList.setHasFixedSize(true)
        }


    }

}