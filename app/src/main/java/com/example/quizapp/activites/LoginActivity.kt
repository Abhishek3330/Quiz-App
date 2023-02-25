package com.example.quizapp.activites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.quizapp.R
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var et_EmailAddress: EditText
    private lateinit var et_Password: EditText
    private lateinit var btnSignUp :TextView
    private lateinit var btnLogin: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        firebaseAuth = FirebaseAuth.getInstance()
        et_EmailAddress = findViewById(R.id.et_EmailAddress)
        et_Password = findViewById(R.id.et_Password)
        btnSignUp = findViewById(R.id.btnSignUp)
        btnLogin = findViewById(R.id.btnLogin)



        btnLogin.setOnClickListener {
            login()
        }
       btnSignUp.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
           finish()
        }


    }

    private fun login() {
        val email = et_EmailAddress.text.toString()
        val password: String = et_Password.text.toString()

        if (email.isBlank() || password.isBlank()) {

            Toast.makeText(this, "Please fill correct details", Toast.LENGTH_SHORT).show()
            return
        }
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    Toast.makeText(this, "Successful", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()

                } else {
                    Toast.makeText(this, "Wrong Email Or Password", Toast.LENGTH_SHORT).show()
                }
            }
    }
}