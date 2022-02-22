package com.naren.resumebuilder.ui

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.naren.resumebuilder.R
import java.util.regex.Pattern

/**
 * Created by Naren on 18,February,2022
 */
class RegisterScreen : AppCompatActivity(), View.OnClickListener {

    private var btn_signup: Button? = null
    private var til_signup_email: TextInputLayout? = null
    private var til_signup_password: TextInputLayout? = null
    private var et_signup_email: TextInputEditText? = null
    private var et_signup_password: TextInputEditText? = null
    private var tv_login: TextView? = null

//    private val progressDialog: ProgressDialog? = null
    private var firebaseAuth: FirebaseAuth? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_screen)

        btn_signup = findViewById(R.id.btn_signup_login)
        tv_login = findViewById(R.id.login)
        et_signup_email = findViewById(R.id.et_signup_login_email)
        til_signup_email = findViewById(R.id.til_signup_login_email)
        til_signup_password = findViewById(R.id.til_signup_login_password)
        et_signup_password = findViewById(R.id.et_signup_login_password)

        btn_signup?.setOnClickListener(this)
        tv_login?.setOnClickListener(this)

        firebaseAuth = FirebaseAuth.getInstance()

        if (firebaseAuth?.getCurrentUser() != null) {
            startActivity(Intent(applicationContext, DashboardScreen::class.java))
            finish()
        }
    }

    override fun onClick(view: View) {
        if (view === btn_signup) {
            registerUser()
        }
        if (view === tv_login) {
            val intent = Intent(applicationContext, LoginScreen::class.java)
            startActivity(intent)
            finish()

        }
    }

    private fun registerUser() {
        val email = et_signup_email!!.text.toString().trim { it <= ' ' }
        val password = et_signup_password!!.text.toString().trim { it <= ' ' }
        val emailRegex =
            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$"
        val pat = Pattern.compile(emailRegex)
        if (TextUtils.isEmpty(email)) {
            til_signup_email!!.error = "Email is required"
            return
        } else {
            til_signup_email!!.error = null
        }
        if (!pat.matcher(email).matches()) {
            Toast.makeText(this, "Please enter a valid Email", Toast.LENGTH_SHORT).show()
            return
        }
        if (TextUtils.isEmpty(password)) {
            til_signup_password!!.error = "Password is required"
            return
        } else {
            til_signup_password!!.error = null
        }
        if (password.length < 6) {
            Toast.makeText(this, "Password must be atleast 6 chars", Toast.LENGTH_SHORT).show()
            return
        }
//        progressDialog!!.setMessage("Registering user...")
//        progressDialog.show()
//        }

//        FirebaseAuth.getInstance().signOut();

        firebaseAuth!!.createUserWithEmailAndPassword(email, password).addOnCompleteListener(
            this
        ) { task ->
//            progressDialog.dismiss()
            if (task.isSuccessful) {

                startActivity(Intent(applicationContext, LoginScreen::class.java))
                finish()
            } else {
                Toast.makeText(
                    applicationContext,
                    "Failed to register...Try again",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        }
    }
}