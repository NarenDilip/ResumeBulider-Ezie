package com.naren.resumebuilder.ui

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.print.PrintAttributes
import android.print.PrintJob
import android.print.PrintManager
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.naren.resumebuilder.R
import com.naren.resumebuilder.localdb.model.DatabaseClient
import com.naren.resumebuilder.utils.AppPreference


/**
 * Created by schnell on 18,February,2022
 */
class PreviewResumeScreen : AppCompatActivity(), View.OnClickListener {

    private var previewResume: WebView? = null
    private var printWeb: WebView? = null

    private var printResume: FloatingActionButton? = null

    private var Logout: TextView? = null

    private var firebaseAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preview_resume_screen)

        previewResume = (findViewById(R.id.preview_resume))
        printResume = (findViewById(R.id.add_print))
        printResume?.setOnClickListener(this)
        Logout = findViewById(R.id.logout)
        Logout?.setOnClickListener(this)

        previewResume?.getSettings()?.setJavaScriptEnabled(true)
        previewResume?.setWebChromeClient(WebChromeClient())
        previewResume?.getSettings()?.setAllowFileAccess(true);

        previewResume?.clearCache(true)

        previewResume?.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY)
        previewResume?.setScrollbarFadingEnabled(false)
        previewResume?.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY)

        var resumeId = AppPreference.get(applicationContext, "ResumeId", "");

        val personInfo = DatabaseClient.getInstance(applicationContext).appDatabase.personInfoDAO()
            .getbyName(resumeId)
        val personCarrierInfo =
            DatabaseClient.getInstance(applicationContext).appDatabase.personCarrierInfoDAO()
                .getCarrierbyName(resumeId)
        val personWorkInfo =
            DatabaseClient.getInstance(applicationContext).appDatabase.personWorkInfoDAO()
                .getworkbyName(resumeId)
        val personSkillInfo =
            DatabaseClient.getInstance(applicationContext).appDatabase.personSkillInfoDAO()
                .getskillbyName(resumeId)
        val personEducationInfo =
            DatabaseClient.getInstance(applicationContext).appDatabase.personEducationInfoDAO()
                .getworkbyName(resumeId)



        previewResume?.setWebViewClient(object : WebViewClient() {

            override fun onPageFinished(view: WebView, url: String) {
                // Your custom javascript here
//                val load0data = "document.getElementById('person_image').innerHTML = '" + imagePath + "'"

                if (personInfo.size >= 1) {
                    val imagePath = "file://" + personInfo.get(0).userImage
                    val loaddata1 = "document.getElementById('person_image').src = '" + imagePath + "'"
                    previewResume?.loadUrl("javascript:(function(){$loaddata1})()")
                }


                val loaddata2 =
                    "document.getElementById('person_name').innerHTML = '" + personInfo.get(0).name + "'"
                val loaddata3 =
                    "document.getElementById('user_name').innerHTML = '" + personInfo.get(0).name + "'"
                val loaddata4 =
                    "document.getElementById('user_designation').innerHTML = '" + personWorkInfo.get(
                        0
                    ).jobTitle + "'"
                val loaddata5 =
                    "document.getElementById('person_desc').innerHTML = '" + personCarrierInfo.get(0).carrierdesc + "'"
                val loaddata6 =
                    "document.getElementById('email_address').innerHTML = '" + personInfo.get(0).emailAddress + "'"
                val loaddata7 =
                    "document.getElementById('mobilenumber').innerHTML = '" + personInfo.get(0).mobileNumber + "'"
                val loaddata8 =
                    "document.getElementById('user_address').innerHTML = '" + personInfo.get(0).residentalAddress + "'"

                val loaddata9 =
                    "document.getElementById('Skillset').innerHTML = '" + personSkillInfo.get(0).skillSet + "'"
                val loaddata10 =
                    "document.getElementById('job_title').innerHTML = '" + personWorkInfo.get(0).jobTitle + " at " + personWorkInfo.get(
                        0
                    ).companyInfo + "'"
                val loaddata11 =
                    "document.getElementById('company_name').innerHTML = '" + personWorkInfo.get(0).companyInfo + "'"
                val loaddata12 =
                    "document.getElementById('person_degree').innerHTML = '" + personEducationInfo.get(
                        0
                    ).course + " from " + personEducationInfo.get(0).school + "'"
                val loaddata13 =
                    "document.getElementById('person_college').innerHTML = '" + personEducationInfo.get(
                        0
                    ).school + "'"
                val loaddata14 =
                    "document.getElementById('year').innerHTML = '" + personEducationInfo.get(0).passYear + "'"
                val loaddata15 =
                    "document.getElementById('p_name').innerHTML = '" + personInfo.get(0).name + "'"

                if (personSkillInfo.size >= 2) {
                    val loaddata16 =
                        "document.getElementById('Skillset_two').innerHTML = '" + personSkillInfo.get(
                            1
                        ).skillSet + "'"
                    previewResume?.loadUrl("javascript:(function(){$loaddata16})()")
                }
                if (personSkillInfo.size >= 3) {
                    val loaddata17 =
                        "document.getElementById('Skillset_three').innerHTML = '" + personSkillInfo.get(
                            2
                        ).skillSet + "'"
                    previewResume?.loadUrl("javascript:(function(){$loaddata17})()")

                }

                if (personWorkInfo.size >= 2) {
                    val loaddata18 =
                        "document.getElementById('job_title_two').innerHTML = '" + personWorkInfo.get(
                            1
                        ).jobTitle + " at " + personWorkInfo.get(
                            1
                        ).companyInfo + "'"

                    val loaddata20 =
                        "document.getElementById('company_name_two').innerHTML = '" + personWorkInfo.get(
                            1
                        ).companyInfo + "'"

                    val loaddata29 =
                        "document.getElementById('job_year_two').innerHTML = '" + personWorkInfo.get(
                            1
                        ).startDate + " to " + personWorkInfo.get(
                            1
                        ).endDate + "'"

                    previewResume?.loadUrl("javascript:(function(){$loaddata18})()")
                    previewResume?.loadUrl("javascript:(function(){$loaddata29})()")
                    previewResume?.loadUrl("javascript:(function(){$loaddata20})()")

                }
                if (personWorkInfo.size >= 3) {
                    val loaddata19 =
                        "document.getElementById('job_title_three').innerHTML = '" + personWorkInfo.get(
                            2
                        ).jobTitle + " at " + personWorkInfo.get(2).companyInfo + "'"

                    val loaddata21 =
                        "document.getElementById('company_name_three').innerHTML = '" + personWorkInfo.get(
                            2
                        ).companyInfo + "'"

                    val loaddata30 =
                        "document.getElementById('job_year_three').innerHTML = '" + personWorkInfo.get(
                            2
                        ).startDate + " to " + personWorkInfo.get(
                            2
                        ).endDate + "'"

                    previewResume?.loadUrl("javascript:(function(){$loaddata19})()")
                    previewResume?.loadUrl("javascript:(function(){$loaddata21})()")
                    previewResume?.loadUrl("javascript:(function(){$loaddata30})()")
                }

                val loaddata28 =
                    "document.getElementById('job_year').innerHTML = '" + personWorkInfo.get(0).startDate + " to " + personWorkInfo.get(
                        0
                    ).endDate + "'"


                if (personEducationInfo.size >= 2) {
                    val loaddata24 =
                        "document.getElementById('person_college_two').innerHTML = '" + personEducationInfo.get(
                            1
                        ).school + "'"

                    val loaddata22 =
                        "document.getElementById('person_degree_two').innerHTML = '" + personEducationInfo.get(
                            1
                        ).course + " from " + personEducationInfo.get(1).school + "'"

                    previewResume?.loadUrl("javascript:(function(){$loaddata22})()")
                    previewResume?.loadUrl("javascript:(function(){$loaddata24})()")
                }

                if (personEducationInfo.size >= 3) {
                    val loaddata23 =
                        "document.getElementById('person_degree_three').innerHTML = '" + personEducationInfo.get(
                            2
                        ).course + " from " + personEducationInfo.get(2).school + "'"

                    val loaddata25 =
                        "document.getElementById('person_college_three').innerHTML = '" + personEducationInfo.get(
                            2
                        ).school + "'"
                    val loaddata26 =
                        "document.getElementById('year_two').innerHTML = '" + personEducationInfo.get(
                            1
                        ).passYear + "'"
                    val loaddata27 =
                        "document.getElementById('year_three').innerHTML = '" + personEducationInfo.get(
                            2
                        ).passYear + "'"


                    previewResume?.loadUrl("javascript:(function(){$loaddata23})()")

                    previewResume?.loadUrl("javascript:(function(){$loaddata25})()")
                    previewResume?.loadUrl("javascript:(function(){$loaddata26})()")
                    previewResume?.loadUrl("javascript:(function(){$loaddata27})()")
                }


                // here we execute the javascript code
//                previewResume?.loadUrl("javascript:(function(){$loaddata1,$loaddata2,$loaddata3,$loaddata4,$loaddata5,$loaddata6,$loaddata7,$loaddata8,$loaddata9,$loaddata10})()")
//                previewResume?.loadUrl("javascript:(function(){$loaddata11,$loaddata12,$loaddata13,$loaddata14,$loaddata15,$loaddata16,$loaddata17,$loaddata18,$loaddata19,$loaddata20})()")
//                previewResume?.loadUrl("javascript:(function(){$loaddata21,$loaddata22,$loaddata23,$loaddata24,$loaddata25,$loaddata26,$loaddata27,$loaddata28,$loaddata29,$loaddata30})()")


                previewResume?.loadUrl("javascript:(function(){$loaddata2})()")
                previewResume?.loadUrl("javascript:(function(){$loaddata3})()")
                previewResume?.loadUrl("javascript:(function(){$loaddata4})()")
                previewResume?.loadUrl("javascript:(function(){$loaddata5})()")
                previewResume?.loadUrl("javascript:(function(){$loaddata6})()")
                previewResume?.loadUrl("javascript:(function(){$loaddata7})()")
                previewResume?.loadUrl("javascript:(function(){$loaddata8})()")
                previewResume?.loadUrl("javascript:(function(){$loaddata9})()")
                previewResume?.loadUrl("javascript:(function(){$loaddata10})()")
                previewResume?.loadUrl("javascript:(function(){$loaddata11})()")
                previewResume?.loadUrl("javascript:(function(){$loaddata12})()")
                previewResume?.loadUrl("javascript:(function(){$loaddata13})()")
                previewResume?.loadUrl("javascript:(function(){$loaddata14})()")
                previewResume?.loadUrl("javascript:(function(){$loaddata15})()")



                previewResume?.loadUrl("javascript:(function(){$loaddata28})()")


                printWeb = previewResume;
            }
        })

        previewResume?.loadUrl("file:///android_asset/template_one/index.html");
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(applicationContext, DashboardScreen::class.java)
        startActivity(intent)
        finish()
    }

    override fun onClick(view: View) {
        if (view === printResume) {
            if (printWeb != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    // Calling createWebPrintJob()
                    PrintTheWebPage(printWeb!!);
                } else {
                    // Showing Toast message to user
                    Toast.makeText(
                        applicationContext,
                        "Not available for device below Android LOLLIPOP",
                        Toast.LENGTH_SHORT
                    ).show();
                }
            } else {
                // Showing Toast message to user
                Toast.makeText(applicationContext, "WebPage not fully loaded", Toast.LENGTH_SHORT)
                    .show();
            }
        }
        else if (view === Logout) {
            firebaseAuth = FirebaseAuth.getInstance()
            FirebaseAuth.getInstance().signOut();
            val intent = Intent(applicationContext, SplashScreen::class.java)
            startActivity(intent)
            finish()
        }
    }

    // object of print job
    var printJob: PrintJob? = null

    // a boolean to check the status of printing
    var printBtnPressed = false

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private fun PrintTheWebPage(webView: WebView) {

        // set printBtnPressed true
        printBtnPressed = true

        // Creating  PrintManager instance
        val printManager = this
            .getSystemService(Context.PRINT_SERVICE) as PrintManager

        // setting the name of job
        val jobName = getString(R.string.app_name) + " webpage" + webView.url

        // Creating  PrintDocumentAdapter instance
        val printAdapter = webView.createPrintDocumentAdapter(jobName)
        assert(printManager != null)
        printJob = printManager.print(
            jobName, printAdapter,
            PrintAttributes.Builder().build()
        )
    }

}