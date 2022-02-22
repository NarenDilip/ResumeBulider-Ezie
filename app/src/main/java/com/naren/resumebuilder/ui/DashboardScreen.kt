package com.naren.resumebuilder.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.naren.resumebuilder.R
import com.naren.resumebuilder.adapter.ResumeListAdapter
import com.naren.resumebuilder.basepojo.PersonResumeData
import com.naren.resumebuilder.localdb.model.DatabaseClient
import com.naren.resumebuilder.utils.AppDialogs
import com.naren.resumebuilder.utils.AppPreference
import com.naren.resumebuilder.utils.AppPreference.put

/**
 * Created by schnell on 18,February,2022
 */
class DashboardScreen : AppCompatActivity(), View.OnClickListener {

    private var CreateResume: FloatingActionButton? = null
    private var Logout: TextView? = null

    //    private var ResumeList: RecyclerView? = null
    private var adapter: RecyclerView.Adapter<*>? = null

    //    private val layoutManager: RecyclerView.LayoutManager? = null
    private var recyclerView: RecyclerView? = null
    private var courseModelArrayList: ArrayList<PersonResumeData>? = null

    private var firebaseAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard_resume_screen)

        CreateResume = findViewById(R.id.create_resume)
        recyclerView = findViewById(R.id.resumesList)
        Logout = findViewById(R.id.logout)

        CreateResume?.setOnClickListener(this)
        Logout?.setOnClickListener(this)


        val layoutManager = LinearLayoutManager(this)
        recyclerView?.layoutManager = layoutManager
        recyclerView?.itemAnimator = DefaultItemAnimator()

        var resumeId = AppPreference.get(applicationContext, "ResumeId", "");
        var actioncmd = AppPreference.get(applicationContext, "DeleteCmd", "")

        if (actioncmd.equals("Yes")) {
            DatabaseClient.getInstance(applicationContext).appDatabase.personInfoDAO()
                .DeletepersonInfo(resumeId)
            DatabaseClient.getInstance(applicationContext).appDatabase.personEducationInfoDAO()
                .DeletepersonEducationInfo(resumeId)
            DatabaseClient.getInstance(applicationContext).appDatabase.personCarrierInfoDAO()
                .DeletePersonCarrierInfo(resumeId)
            DatabaseClient.getInstance(applicationContext).appDatabase.personSkillInfoDAO()
                .DeletepersonSkillInfo(resumeId)
            DatabaseClient.getInstance(applicationContext).appDatabase.personWorkInfoDAO()
                .DeletepersonWorkInfo(resumeId)

            AppPreference.put(applicationContext, "DeleteCmd", "No")
        }

        val details =
            DatabaseClient.getInstance(applicationContext).appDatabase.personInfoDAO().details;
        courseModelArrayList = ArrayList()

        for (i in 0 until details.size) {
            if (details.get(i).userImage != null) {
                courseModelArrayList!!.add(
                    PersonResumeData(
                        details.get(i).name,
                        details.get(i).emailAddress,
                        details.get(i).userImage,
                        details.get(i).resumeNo
                    )
                )
            } else {
                courseModelArrayList!!.add(
                    PersonResumeData(
                        details.get(i).name,
                        details.get(i).emailAddress,
                        "",
                        details.get(i).resumeNo
                    )
                )
            }
        }

        adapter = ResumeListAdapter(
            this,
            courseModelArrayList
        )
        recyclerView?.setLayoutManager(LinearLayoutManager(applicationContext));
        recyclerView?.adapter = adapter;
        adapter?.notifyDataSetChanged()

    }



    override fun onClick(view: View) {
        if (view === CreateResume) {
            val intent = Intent(applicationContext, TemplateSelectionScreen::class.java)
            startActivity(intent)
            finish()
        } else if (view === Logout) {

            firebaseAuth = FirebaseAuth.getInstance()
            FirebaseAuth.getInstance().signOut();
            val intent = Intent(applicationContext, SplashScreen::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()

//        val l = object : AppDialogs.ConfirmListener {
//            override fun yes() {
//                AppPreference.clearAll(applicationContext)
                val intent = Intent(applicationContext, SplashScreen::class.java);
                startActivity(intent)
                finish()
//            }
//        }
//
//        AppDialogs.confirmAction(
//            c = this!!,
//            text = "Sure you want to Exit from Ezie CV",
//            l = l
//        )

    }
}