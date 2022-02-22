package com.naren.resumebuilder.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.naren.resumebuilder.R

/**
 * Created by Naren on 18,February,2022
 */
class TemplateSelectionScreen : AppCompatActivity(), View.OnClickListener {

    private var template_one_view: ImageView? = null;
//    private var template_two_view: ImageView? = null;
//    private var template_three_view: ImageView? = null;
//    private var template_four_view: ImageView? = null;

    private var Logout: TextView? = null

    private var firebaseAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_template_selection_screen)

        template_one_view = findViewById(R.id.template_one);
//        template_two_view = findViewById(R.id.template_two);
//        template_three_view = findViewById(R.id.template_three);
//        template_four_view = findViewById(R.id.template_four);
        Logout = findViewById(R.id.logout)
        Logout?.setOnClickListener(this)
        template_one_view?.setOnClickListener(this)
//        template_two_view?.setOnClickListener(this)
//        template_three_view?.setOnClickListener(this)
//        template_four_view?.setOnClickListener(this)

    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(applicationContext, DashboardScreen::class.java)
        startActivity(intent)
        finish()

    }

    override fun onClick(view: View) {
        if (view === template_one_view) {
            val intent = Intent(applicationContext, CreateResumeScreen::class.java)
            startActivity(intent)
            finish()

        }else if (view === Logout) {
            firebaseAuth = FirebaseAuth.getInstance()
            FirebaseAuth.getInstance().signOut();
            val intent = Intent(applicationContext, SplashScreen::class.java)
            startActivity(intent)
            finish()
        }

//        else if (view === template_two_view) {
//            finish()
//            val intent = Intent(applicationContext, CreateResumeScreen::class.java)
//            startActivity(intent)
//        } else if (view === template_three_view) {
//            finish()
//            val intent = Intent(applicationContext, CreateResumeScreen::class.java)
//            startActivity(intent)
//        } else if (view === template_four_view) {
//            finish()
//            val intent = Intent(applicationContext, CreateResumeScreen::class.java)
//            startActivity(intent)
//        }

    }
}