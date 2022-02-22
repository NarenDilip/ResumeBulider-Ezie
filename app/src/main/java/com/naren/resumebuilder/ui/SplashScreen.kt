package com.naren.resumebuilder.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.naren.resumebuilder.R
import com.naren.resumebuilder.utils.AppPreference
import com.naren.resumebuilder.utils.AppPreference.put

/**
 * Created by Naren on 18,February,2022
 */
class SplashScreen: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activty_splash_screen)

        AppPreference.put(applicationContext, "DeleteCmd", "Yes")

        // we used the postDelayed(Runnable, time) method
        // to send a message with a delayed time.
        Handler().postDelayed({
            val intent = Intent(this, RegisterScreen::class.java)
            startActivity(intent)
            finish()
        }, 3000) // 3000 is the delayed time in milliseconds.
    }

}