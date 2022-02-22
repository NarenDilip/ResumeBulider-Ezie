package com.naren.resumebuilder.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.naren.resumebuilder.R

/**
 * Created by Naren on 18,February,2022
 */
class LoginScreen : AppCompatActivity(), View.OnClickListener {

    private var btn_signup: TextView? = null
    private var til_signup_email: TextInputLayout? = null
    private var til_signup_password: TextInputLayout? = null
    private var et_signup_email: TextInputEditText? = null
    private var et_signup_password: TextInputEditText? = null
    private var tv_login: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)

        btn_signup = findViewById(R.id.signup)
        tv_login = findViewById(R.id.btn_login)
        et_signup_email = findViewById(R.id.et_login_email)
        til_signup_email = findViewById(R.id.til_login_email)
        til_signup_password = findViewById(R.id.til_login_password)
        et_signup_password = findViewById(R.id.et_login_password)

        btn_signup?.setOnClickListener(this);
        tv_login?.setOnClickListener(this);

    }

    override fun onClick(view: View) {
        if (view === btn_signup) {
            val intent = Intent(applicationContext, RegisterScreen::class.java)
            startActivity(intent)
            finish()
        }
        if (view === tv_login) {
            val intent = Intent(applicationContext, DashboardScreen::class.java)
            startActivity(intent)
            finish()

        }
    }
}