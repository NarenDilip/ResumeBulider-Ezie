package com.naren.resumebuilder.ui

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.naren.resumebuilder.R
import com.naren.resumebuilder.localdb.model.*
import com.naren.resumebuilder.utils.AppPreference
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by Naren on 18,February,2022
 */
class EditResumeScreen : AppCompatActivity(), View.OnClickListener {

    private var personalinfolayer: ConstraintLayout? = null
    private var workinfolayer: ConstraintLayout? = null
    private var skill_info_layer: ConstraintLayout? = null
    private var eduction_info_layer: ConstraintLayout? = null
    private var carrier_info_layer: TextInputLayout? = null
    private var complete_resume: FloatingActionButton? = null

    private var personName: TextInputEditText? = null
    private var personNumber: TextInputEditText? = null
    private var personEmailAddress: TextInputEditText? = null
    private var personContactAddress: TextInputEditText? = null
    private var personImage: ImageView? = null
    private var personCarrierObj: TextInputEditText? = null

    private var personWorkCmpNameOne: TextInputEditText? = null
    private var personWorkJobTitleOne: TextInputEditText? = null
    private var personWorkJobStartDateOne: TextInputEditText? = null
    private var personWorkJobEndDateOne: TextInputEditText? = null
    private var personWorkJobDecOne: TextInputEditText? = null

    private var personWorkCmpNameTwo: TextInputEditText? = null
    private var personWorkJobTitleTwo: TextInputEditText? = null
    private var personWorkJobStartDateTwo: TextInputEditText? = null
    private var personWorkJobEndDateTwo: TextInputEditText? = null
    private var personWorkJobDecTwo: TextInputEditText? = null

    private var personWorkCmpNameThree: TextInputEditText? = null
    private var personWorkJobTitleThree: TextInputEditText? = null
    private var personWorkJobStartDateThree: TextInputEditText? = null
    private var personWorkJobEndDateThree: TextInputEditText? = null
    private var personWorkJobDecThree: TextInputEditText? = null

    private var personSkillSetOne: TextInputEditText? = null
    private var personSkillSetTwo: TextInputEditText? = null
    private var personSkillSetThree: TextInputEditText? = null

    private var levelOneExpertOne: Button? = null
    private var levelTwoExpertOne: Button? = null
    private var levelThreeExpertOne: Button? = null
    private var levelFourExpertOne: Button? = null

    private var levelOneExpertTwo: Button? = null
    private var levelTwoExpertTwo: Button? = null
    private var levelThreeExpertTwo: Button? = null
    private var levelFourExpertTwo: Button? = null

    private var levelOneExpertThree: Button? = null
    private var levelTwoExpertThree: Button? = null
    private var levelThreeExpertThree: Button? = null
    private var levelFourExpertThree: Button? = null

    private var personDegreeOne: TextInputEditText? = null
    private var personSchoolOne: TextInputEditText? = null
    private var personScoreOne: TextInputEditText? = null
    private var personPassYear: TextInputEditText? = null

    private var personDegreeTwo: TextInputEditText? = null
    private var personSchoolTwo: TextInputEditText? = null
    private var personScoreTwo: TextInputEditText? = null
    private var personPassTwo: TextInputEditText? = null

    private var personDegreeThree: TextInputEditText? = null
    private var personSchoolThree: TextInputEditText? = null
    private var personScoreThree: TextInputEditText? = null
    private var personPassThree: TextInputEditText? = null

    private var personal_info_banner: TextView? = null
    private var carrier_info_banner: TextView? = null
    private var work_info_banner: TextView? = null
    private var skill_info_banner: TextView? = null
    private var education_info_banner: TextView? = null

    var photoFile: File? = null
    val CAPTURE_IMAGE_REQUEST = 1
    var mCurrentPhotoPath: String? = null
    var resumeId : String? = null

    var selectedSkillSetOne: String? = "1"
    var selectedSkillSetTwo: String? = "1"
    var selectedSkillSetThree: String? = "1"

    private var Logout: TextView? = null

    private var firebaseAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_resume_screen)

        personalinfolayer = (findViewById(R.id.personal_details))
        personal_info_banner = (findViewById(R.id.personal_info_banner))
        workinfolayer = (findViewById(R.id.work_info_layer))
        skill_info_layer = (findViewById(R.id.skill_info_layer))
        eduction_info_layer = (findViewById(R.id.eduction_info_layer))

        carrier_info_banner = (findViewById(R.id.carrier_info_banner))
        work_info_banner = (findViewById(R.id.work_info_banner))
        carrier_info_layer = (findViewById(R.id.til_carrier_obj))
        skill_info_banner = (findViewById(R.id.skill_info_banner))
        education_info_banner = (findViewById(R.id.education_info_banner))
        complete_resume = (findViewById(R.id.complete_resume))

        personName = (findViewById(R.id.form_personal_et_name))
        personNumber = (findViewById(R.id.form_person_number))
        personEmailAddress = (findViewById(R.id.et_email_address))
        personContactAddress = (findViewById(R.id.et_contact_address))
        personImage = (findViewById(R.id.person_image))
        personCarrierObj = (findViewById(R.id.et_carrier_obj))

        personWorkCmpNameOne = (findViewById(R.id.et_company_name))
        personWorkJobTitleOne = (findViewById(R.id.et_job_title))
        personWorkJobStartDateOne = (findViewById(R.id.form_exp_et_sdate))
        personWorkJobEndDateOne = (findViewById(R.id.form_exp_et_edate))
        personWorkJobDecOne = (findViewById(R.id.et_job_description))

        personWorkCmpNameTwo = (findViewById(R.id.two_et_company_name))
        personWorkJobTitleTwo = (findViewById(R.id.two_et_job_title))
        personWorkJobStartDateTwo = (findViewById(R.id.two_form_exp_et_sdate))
        personWorkJobEndDateTwo = (findViewById(R.id.two_form_exp_et_edate))
        personWorkJobDecTwo = (findViewById(R.id.two_et_job_description))

        personWorkCmpNameThree = (findViewById(R.id.three_et_company_name))
        personWorkJobTitleThree = (findViewById(R.id.three_et_job_title))
        personWorkJobStartDateThree = (findViewById(R.id.three_form_exp_et_sdate))
        personWorkJobEndDateThree = (findViewById(R.id.three_form_exp_et_edate))
        personWorkJobDecThree = (findViewById(R.id.three_et_job_description))

        personSkillSetOne = (findViewById(R.id.et_skillset))
        personSkillSetTwo = (findViewById(R.id.et_skillset_two))
        personSkillSetThree = (findViewById(R.id.et_skillset_three))

        levelOneExpertOne = (findViewById(R.id.level_one))
        levelTwoExpertOne = (findViewById(R.id.level_two))
        levelThreeExpertOne = (findViewById(R.id.level_three))
        levelFourExpertOne = (findViewById(R.id.level_four))

        levelOneExpertTwo = (findViewById(R.id.o_level_one))
        levelTwoExpertTwo = (findViewById(R.id.o_level_two))
        levelThreeExpertTwo = (findViewById(R.id.o_level_three))
        levelFourExpertTwo = (findViewById(R.id.o_level_four))

        levelOneExpertThree = (findViewById(R.id.t_level_one))
        levelTwoExpertThree = (findViewById(R.id.t_level_two))
        levelThreeExpertThree = (findViewById(R.id.t_level_three))
        levelFourExpertThree = (findViewById(R.id.t_level_four))

        personDegreeOne = (findViewById(R.id.form_edu_et_degree))
        personSchoolOne = (findViewById(R.id.form_edu_et_university))
        personScoreOne = (findViewById(R.id.form_edu_et_grade))
        personPassYear = (findViewById(R.id.form_edu_et_year))

        personDegreeTwo = (findViewById(R.id.form_edu_et_degree_two))
        personSchoolTwo = (findViewById(R.id.form_edu_et_university_two))
        personScoreTwo = (findViewById(R.id.form_edu_et_grade_two))
        personPassTwo = (findViewById(R.id.form_edu_et_year_two))

        personDegreeThree = (findViewById(R.id.form_edu_et_degree_three))
        personSchoolThree = (findViewById(R.id.form_edu_et_university_three))
        personScoreThree = (findViewById(R.id.form_edu_et_grade_three))
        personPassThree = (findViewById(R.id.form_edu_et_year_three))

        personal_info_banner?.setOnClickListener(this)
        carrier_info_banner?.setOnClickListener(this)
        work_info_banner?.setOnClickListener(this)
        skill_info_banner?.setOnClickListener(this)
        education_info_banner?.setOnClickListener(this)
        complete_resume?.setOnClickListener(this)

        levelOneExpertOne?.setOnClickListener(this)
        levelTwoExpertOne?.setOnClickListener(this)
        levelThreeExpertOne?.setOnClickListener(this)
        levelFourExpertOne?.setOnClickListener(this)

        levelOneExpertTwo?.setOnClickListener(this)
        levelTwoExpertTwo?.setOnClickListener(this)
        levelThreeExpertTwo?.setOnClickListener(this)
        levelFourExpertTwo?.setOnClickListener(this)

        levelOneExpertThree?.setOnClickListener(this)
        levelTwoExpertThree?.setOnClickListener(this)
        levelThreeExpertThree?.setOnClickListener(this)
        levelFourExpertThree?.setOnClickListener(this)
        personImage?.setOnClickListener(this)
        Logout = findViewById(R.id.logout)
        Logout?.setOnClickListener(this)


        resumeId = AppPreference.get(applicationContext, "ResumeId", "");

        val personInfo = DatabaseClient.getInstance(applicationContext).appDatabase.personInfoDAO()
            .getbyName(resumeId)

        personName?.setText(personInfo.get(0).name)
        personNumber?.setText(personInfo.get(0).mobileNumber)
        personEmailAddress?.setText(personInfo.get(0).emailAddress)
        personContactAddress?.setText(personInfo.get(0).residentalAddress)

        val myBitmap = BitmapFactory.decodeFile(personInfo.get(0).userImage)
        personImage?.setImageBitmap(myBitmap)

        val personCarrierInfo =
            DatabaseClient.getInstance(applicationContext).appDatabase.personCarrierInfoDAO()
                .getCarrierbyName(resumeId)

        personCarrierObj?.setText(personCarrierInfo.get(0).carrierdesc)

        val personWorkInfo =
            DatabaseClient.getInstance(applicationContext).appDatabase.personWorkInfoDAO()
                .getworkbyName(resumeId)

        if (personInfo.size >= 1) {
            if (personInfo.get(0).name.isNotEmpty()) {
                personWorkCmpNameOne?.setText(personWorkInfo.get(0).companyInfo)
                personWorkJobTitleOne?.setText(personWorkInfo.get(0).jobTitle)
                personWorkJobStartDateOne?.setText(personWorkInfo.get(0).startDate)
                personWorkJobEndDateOne?.setText(personWorkInfo.get(0).endDate)
                personWorkJobDecOne?.setText(personWorkInfo.get(0).jobDescription)
            }
        }
        if (personInfo.size >= 2) {
            if (personInfo.get(1).name.isNotEmpty()) {
                personWorkCmpNameTwo?.setText(personWorkInfo.get(1).companyInfo)
                personWorkJobTitleTwo?.setText(personWorkInfo.get(1).jobTitle)
                personWorkJobStartDateTwo?.setText(personWorkInfo.get(1).startDate)
                personWorkJobEndDateTwo?.setText(personWorkInfo.get(1).endDate)
                personWorkJobDecTwo?.setText(personWorkInfo.get(1).jobDescription)
            }
        }
        if (personInfo.size >= 3) {
            if (personInfo.get(2).name.isNotEmpty()) {
                personWorkCmpNameThree?.setText(personWorkInfo.get(2).companyInfo)
                personWorkJobTitleThree?.setText(personWorkInfo.get(2).jobTitle)
                personWorkJobStartDateThree?.setText(personWorkInfo.get(2).startDate)
                personWorkJobEndDateThree?.setText(personWorkInfo.get(2).endDate)
                personWorkJobDecThree?.setText(personWorkInfo.get(2).jobDescription)
            }
        }

        val personSkillInfo =
            DatabaseClient.getInstance(applicationContext).appDatabase.personSkillInfoDAO()
                .getskillbyName(resumeId)

        if (personSkillInfo.size >= 1) {
            personSkillSetOne?.setText(personSkillInfo.get(0).skillSet)
        }
        if (personSkillInfo.size >= 2) {
            personSkillSetTwo?.setText(personSkillInfo.get(1).skillSet)
        }
        if (personSkillInfo.size >= 3) {
            personSkillSetThree?.setText(personSkillInfo.get(2).skillSet)
        }

        val personEducationInfo =
            DatabaseClient.getInstance(applicationContext).appDatabase.personEducationInfoDAO()
                .getworkbyName(resumeId)

        if(personEducationInfo.size >= 1) {
            personDegreeOne?.setText(personEducationInfo.get(0).course)
            personSchoolOne?.setText(personEducationInfo.get(0).school)
            personScoreOne?.setText(personEducationInfo.get(0).score)
            personPassYear?.setText(personEducationInfo.get(0).passYear)
        }
        if(personEducationInfo.size >= 2) {
            personDegreeTwo?.setText(personEducationInfo.get(1).course)
            personSchoolTwo?.setText(personEducationInfo.get(1).school)
            personScoreTwo?.setText(personEducationInfo.get(1).score)
            personPassTwo?.setText(personEducationInfo.get(1).passYear)
        }
        if(personEducationInfo.size >= 3) {
            personDegreeThree?.setText(personEducationInfo.get(2).course)
            personSchoolThree?.setText(personEducationInfo.get(2).school)
            personScoreThree?.setText(personEducationInfo.get(2).score)
            personPassThree?.setText(personEducationInfo.get(2).passYear)
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
        val intent = Intent(applicationContext, DashboardScreen::class.java)
        startActivity(intent)
    }

    override fun onClick(view: View) {
        if (view === personal_info_banner) {
            if (personalinfolayer?.visibility == View.VISIBLE) {
                personalinfolayer?.visibility = View.GONE
            } else {
                personalinfolayer?.visibility = View.VISIBLE
            }
        } else if (view === carrier_info_banner) {
            if (carrier_info_layer?.visibility == View.VISIBLE) {
                carrier_info_layer?.visibility = View.GONE
            } else {
                carrier_info_layer?.visibility = View.VISIBLE
            }
        } else if (view === work_info_banner) {
            if (workinfolayer?.visibility == View.VISIBLE) {
                workinfolayer?.visibility = View.GONE
            } else {
                workinfolayer?.visibility = View.VISIBLE
            }
        } else if (view === skill_info_banner) {
            if (skill_info_layer?.visibility == View.VISIBLE) {
                skill_info_layer?.visibility = View.GONE
            } else {
                skill_info_layer?.visibility = View.VISIBLE
            }
        } else if (view === education_info_banner) {
            if (eduction_info_layer?.visibility == View.VISIBLE) {
                eduction_info_layer?.visibility = View.GONE
            } else {
                eduction_info_layer?.visibility = View.VISIBLE
            }
        }else if (view === Logout) {
            firebaseAuth = FirebaseAuth.getInstance()
            FirebaseAuth.getInstance().signOut();
            val intent = Intent(applicationContext, SplashScreen::class.java)
            startActivity(intent)
            finish()
        }
        else if (view === complete_resume) {
            if (personName?.text.toString().isNotEmpty()) {
                if (personNumber?.text.toString().isNotEmpty()) {
                    if (personEmailAddress?.text.toString().isNotEmpty()) {
                        if (personContactAddress?.text.toString().isNotEmpty()) {
                            if (personCarrierObj?.text.toString().isNotEmpty()) {
                                if (personWorkCmpNameOne?.text.toString().isNotEmpty()) {
                                    if (personWorkJobTitleOne?.text.toString().isNotEmpty()) {
                                        if (personWorkJobStartDateOne?.text.toString()
                                                .isNotEmpty()
                                        ) {
                                            if (personWorkJobEndDateOne?.text.toString()
                                                    .isNotEmpty()
                                            ) {
                                                if (personWorkJobDecOne?.text.toString()
                                                        .isNotEmpty()
                                                ) {
                                                    if (personSkillSetOne?.text.toString()
                                                            .isNotEmpty()
                                                    ) {
                                                        if (personDegreeOne?.text.toString()
                                                                .isNotEmpty()
                                                        ) {
                                                            if (personSchoolOne?.text.toString()
                                                                    .isNotEmpty()
                                                            ) {
                                                                if (personPassYear?.text.toString()
                                                                        .isNotEmpty()
                                                                ) {
                                                                    callPreviewResume(resumeId)
                                                                } else {
                                                                    personPassYear?.setError("Invalid Entry")
                                                                }
                                                            } else {
                                                                personSchoolOne?.setError("Invalid Entry")
                                                            }
                                                        } else {
                                                            personDegreeOne?.setError("Invalid Entry")
                                                        }
                                                    } else {
                                                        personSkillSetOne?.setError("Invalid Entry")
                                                    }
                                                } else {
                                                    personWorkJobDecOne?.setError("Invalid Entry")
                                                }
                                            } else {
                                                personWorkJobEndDateOne?.setError("Invalid Entry")
                                            }
                                        } else {
                                            personWorkJobStartDateOne?.setError("Invalid Entry")
                                        }
                                    } else {
                                        personWorkJobTitleOne?.setError("Invalid Entry")
                                    }
                                } else {
                                    personWorkCmpNameOne?.setError("Invalid Entry")
                                }
                            } else {
                                personCarrierObj?.setError("Invalid Entry")
                            }
                        } else {
                            personContactAddress?.setError("Invalid Entry")
                        }
                    } else {
                        personEmailAddress?.setError("Invalid Entry")
                    }
                } else {
                    personNumber?.setError("Invalid Entry")
                }
            } else {
                personName?.setError("Invalid Entry")
            }

        } else if (view === levelOneExpertOne) {
            selectedSkillSetOne = "1"
            levelOneExpertOne?.setBackgroundColor(resources.getColor(R.color.button_grey))
            levelTwoExpertOne?.setBackgroundColor(resources.getColor(R.color.white))
            levelThreeExpertOne?.setBackgroundColor(resources.getColor(R.color.white))
            levelFourExpertOne?.setBackgroundColor(resources.getColor(R.color.white))
        } else if (view === levelTwoExpertOne) {
            selectedSkillSetOne = "2"
            levelOneExpertOne?.setBackgroundColor(resources.getColor(R.color.white))
            levelTwoExpertOne?.setBackgroundColor(resources.getColor(R.color.button_grey))
            levelThreeExpertOne?.setBackgroundColor(resources.getColor(R.color.white))
            levelFourExpertOne?.setBackgroundColor(resources.getColor(R.color.white))
        } else if (view === levelThreeExpertOne) {
            selectedSkillSetOne = "3"
            levelOneExpertOne?.setBackgroundColor(resources.getColor(R.color.white))
            levelTwoExpertOne?.setBackgroundColor(resources.getColor(R.color.white))
            levelThreeExpertOne?.setBackgroundColor(resources.getColor(R.color.button_grey))
            levelFourExpertOne?.setBackgroundColor(resources.getColor(R.color.white))

        } else if (view === levelFourExpertOne) {
            selectedSkillSetOne = "4"
            levelOneExpertOne?.setBackgroundColor(resources.getColor(R.color.white))
            levelTwoExpertOne?.setBackgroundColor(resources.getColor(R.color.white))
            levelThreeExpertOne?.setBackgroundColor(resources.getColor(R.color.white))
            levelFourExpertOne?.setBackgroundColor(resources.getColor(R.color.button_grey))
        } else if (view === levelOneExpertTwo) {
            selectedSkillSetTwo = "1"
            levelOneExpertTwo?.setBackgroundColor(resources.getColor(R.color.button_grey))
            levelTwoExpertTwo?.setBackgroundColor(resources.getColor(R.color.white))
            levelThreeExpertTwo?.setBackgroundColor(resources.getColor(R.color.white))
            levelFourExpertTwo?.setBackgroundColor(resources.getColor(R.color.white))
        } else if (view === levelTwoExpertTwo) {
            selectedSkillSetTwo = "2"
            levelOneExpertTwo?.setBackgroundColor(resources.getColor(R.color.white))
            levelTwoExpertTwo?.setBackgroundColor(resources.getColor(R.color.button_grey))
            levelThreeExpertTwo?.setBackgroundColor(resources.getColor(R.color.white))
            levelFourExpertTwo?.setBackgroundColor(resources.getColor(R.color.white))
        } else if (view === levelThreeExpertTwo) {
            selectedSkillSetTwo = "3"
            levelOneExpertTwo?.setBackgroundColor(resources.getColor(R.color.white))
            levelTwoExpertTwo?.setBackgroundColor(resources.getColor(R.color.white))
            levelThreeExpertTwo?.setBackgroundColor(resources.getColor(R.color.button_grey))
            levelFourExpertTwo?.setBackgroundColor(resources.getColor(R.color.white))
        } else if (view === levelFourExpertTwo) {
            selectedSkillSetTwo = "4"
            levelOneExpertTwo?.setBackgroundColor(resources.getColor(R.color.white))
            levelTwoExpertTwo?.setBackgroundColor(resources.getColor(R.color.white))
            levelThreeExpertTwo?.setBackgroundColor(resources.getColor(R.color.white))
            levelFourExpertTwo?.setBackgroundColor(resources.getColor(R.color.button_grey))

        } else if (view === levelOneExpertThree) {
            selectedSkillSetThree = "1"
            levelOneExpertThree?.setBackgroundColor(resources.getColor(R.color.button_grey))
            levelTwoExpertThree?.setBackgroundColor(resources.getColor(R.color.white))
            levelThreeExpertThree?.setBackgroundColor(resources.getColor(R.color.white))
            levelFourExpertThree?.setBackgroundColor(resources.getColor(R.color.white))
        } else if (view === levelTwoExpertThree) {
            selectedSkillSetThree = "2"
            levelOneExpertThree?.setBackgroundColor(resources.getColor(R.color.white))
            levelTwoExpertThree?.setBackgroundColor(resources.getColor(R.color.button_grey))
            levelThreeExpertThree?.setBackgroundColor(resources.getColor(R.color.white))
            levelFourExpertThree?.setBackgroundColor(resources.getColor(R.color.white))
        } else if (view === levelThreeExpertThree) {
            selectedSkillSetThree = "3"
            levelOneExpertThree?.setBackgroundColor(resources.getColor(R.color.white))
            levelTwoExpertThree?.setBackgroundColor(resources.getColor(R.color.white))
            levelThreeExpertThree?.setBackgroundColor(resources.getColor(R.color.button_grey))
            levelFourExpertThree?.setBackgroundColor(resources.getColor(R.color.white))
        } else if (view === levelFourExpertThree) {
            selectedSkillSetThree = "4"
            levelOneExpertThree?.setBackgroundColor(resources.getColor(R.color.white))
            levelTwoExpertThree?.setBackgroundColor(resources.getColor(R.color.white))
            levelThreeExpertThree?.setBackgroundColor(resources.getColor(R.color.white))
            levelFourExpertThree?.setBackgroundColor(resources.getColor(R.color.button_grey))
        } else if (view == personImage) {
            captureImage()
        }
    }

    private fun callPreviewResume(resumeId: String?) {

        val rnd = Random()
        val number = rnd.nextInt(999999)

//        AppPreference.put(applicationContext, "ResumeId", number.toString());

        var personInfo = PersonInfo()
        personInfo.name = personName?.text.toString()
        personInfo.mobileNumber = personNumber?.text.toString()
        personInfo.emailAddress = personEmailAddress?.text.toString()
        personInfo.residentalAddress = personContactAddress?.text.toString()
        personInfo.resumeNo =resumeId
        personInfo.userImage = mCurrentPhotoPath

        DatabaseClient.getInstance(applicationContext).appDatabase.personInfoDAO()
            .updatepersonInfo(personName?.text.toString(),personNumber?.text.toString(), personEmailAddress?.text.toString(),personContactAddress?.text.toString(), mCurrentPhotoPath,resumeId.toString());

        var personCarrierInfo = PersonCarrierInfo()
        personCarrierInfo.name = personName?.text.toString()
        personCarrierInfo.resumeNo = resumeId.toString()
        personCarrierInfo.carrierdesc = personCarrierObj?.text.toString()

        DatabaseClient.getInstance(applicationContext).appDatabase.personCarrierInfoDAO()
            .updatePersonCarrierInfo(personName?.text.toString(), personCarrierObj?.text.toString(),resumeId.toString());

        var personWorkInfo = PersonWorkInfo()
        personWorkInfo.name = personName?.text.toString()
        personWorkInfo.resumeNo = resumeId.toString()
        personWorkInfo.companyInfo = personWorkCmpNameOne?.text.toString()
        personWorkInfo.jobTitle = personWorkJobTitleOne?.text.toString()
        personWorkInfo.startDate = personWorkJobStartDateOne?.text.toString()
        personWorkInfo.endDate = personWorkJobEndDateOne?.text.toString()
        personWorkInfo.jobDescription = personWorkJobDecOne?.text.toString()

        DatabaseClient.getInstance(applicationContext).appDatabase.personWorkInfoDAO()
            .updatepersonWorkInfo(personName?.text.toString(),personWorkCmpNameOne?.text.toString(),personWorkJobTitleOne?.text.toString(),personWorkJobStartDateOne?.text.toString(),personWorkJobEndDateOne?.text.toString(),personWorkJobDecOne?.text.toString(),resumeId.toString());

        var personWorkInfo_two = PersonWorkInfo()
        personWorkInfo_two.name = personName?.text.toString()
        personWorkInfo_two.resumeNo = resumeId.toString()
        personWorkInfo_two.companyInfo = personWorkCmpNameTwo?.text.toString()
        personWorkInfo_two.jobTitle = personWorkJobTitleTwo?.text.toString()
        personWorkInfo_two.startDate = personWorkJobStartDateTwo?.text.toString()
        personWorkInfo_two.endDate = personWorkJobEndDateTwo?.text.toString()
        personWorkInfo_two.jobDescription = personWorkJobDecTwo?.text.toString()

        DatabaseClient.getInstance(applicationContext).appDatabase.personWorkInfoDAO()
            .updatepersonWorkInfo(personName?.text.toString(),personWorkCmpNameTwo?.text.toString(),personWorkJobTitleTwo?.text.toString(),personWorkJobStartDateTwo?.text.toString(),personWorkJobEndDateTwo?.text.toString(),personWorkJobDecTwo?.text.toString(),resumeId.toString());

        var personWorkInfo_three = PersonWorkInfo()
        personWorkInfo_three.name = personName?.text.toString()
        personWorkInfo_three.resumeNo = resumeId.toString()
        personWorkInfo_three.companyInfo = personWorkCmpNameThree?.text.toString()
        personWorkInfo_three.jobTitle = personWorkJobTitleThree?.text.toString()
        personWorkInfo_three.startDate = personWorkJobStartDateThree?.text.toString()
        personWorkInfo_three.endDate = personWorkJobEndDateThree?.text.toString()
        personWorkInfo_three.jobDescription = personWorkJobDecThree?.text.toString()

        DatabaseClient.getInstance(applicationContext).appDatabase.personWorkInfoDAO()
            .updatepersonWorkInfo(personName?.text.toString(),personWorkCmpNameThree?.text.toString(),personWorkJobTitleThree?.text.toString(),personWorkJobStartDateThree?.text.toString(),personWorkJobEndDateThree?.text.toString(),personWorkJobDecThree?.text.toString(),resumeId.toString());

        var personSkillInfo = PersonSkillInfo();
        personSkillInfo.name = personName?.text.toString()
        personSkillInfo.resumeNo = resumeId.toString()
        personSkillInfo.skillSet = personSkillSetOne?.text.toString()
        personSkillInfo.skillrate = selectedSkillSetOne

        DatabaseClient.getInstance(applicationContext).appDatabase.personSkillInfoDAO()
            .updatepersonSkillInfo(personName?.text.toString(),personSkillSetOne?.text.toString(),selectedSkillSetOne,resumeId.toString())

        var personSkillInfo_two = PersonSkillInfo();
        personSkillInfo_two.name = personName?.text.toString()
        personSkillInfo_two.resumeNo = resumeId.toString()
        personSkillInfo_two.skillSet = personSkillSetTwo?.text.toString()
        personSkillInfo_two.skillrate = selectedSkillSetTwo

        DatabaseClient.getInstance(applicationContext).appDatabase.personSkillInfoDAO()
            .updatepersonSkillInfo(personName?.text.toString(),personSkillSetTwo?.text.toString(),selectedSkillSetTwo,resumeId.toString())

        var personSkillInfo_three = PersonSkillInfo();
        personSkillInfo_three.name = personName?.text.toString()
        personSkillInfo_three.resumeNo = resumeId.toString()
        personSkillInfo_three.skillSet = personSkillSetThree?.text.toString()
        personSkillInfo_three.skillrate = selectedSkillSetThree

        DatabaseClient.getInstance(applicationContext).appDatabase.personSkillInfoDAO()
            .updatepersonSkillInfo(personName?.text.toString(),personSkillSetThree?.text.toString(),selectedSkillSetThree,resumeId.toString())

        var personEducationInfo = PersonEducationInfo();
        personEducationInfo.name = personName?.text.toString()
        personEducationInfo.resumeNo = resumeId.toString()
        personEducationInfo.course = personDegreeOne?.text.toString()
        personEducationInfo.school = personSchoolOne?.text.toString()
        personEducationInfo.score = personScoreOne?.text.toString()
        personEducationInfo.passYear = personPassYear?.text.toString()

        DatabaseClient.getInstance(applicationContext).appDatabase.personEducationInfoDAO()
            .updatepersonEducationInfo(personName?.text.toString(),personDegreeOne?.text.toString(),personSchoolOne?.text.toString(),personScoreOne?.text.toString(),personPassYear?.text.toString(),resumeId.toString())

        var personEducationInfo_two = PersonEducationInfo();
        personEducationInfo_two.name = personName?.text.toString()
        personEducationInfo_two.resumeNo = resumeId.toString()
        personEducationInfo_two.course = personDegreeTwo?.text.toString()
        personEducationInfo_two.school = personSchoolTwo?.text.toString()
        personEducationInfo_two.score = personScoreTwo?.text.toString()
        personEducationInfo_two.passYear = personPassTwo?.text.toString()

        DatabaseClient.getInstance(applicationContext).appDatabase.personEducationInfoDAO()
            .updatepersonEducationInfo(personName?.text.toString(),personDegreeTwo?.text.toString(),personSchoolTwo?.text.toString(),personScoreTwo?.text.toString(),personPassTwo?.text.toString(),resumeId.toString())

        var personEducationInfo_three = PersonEducationInfo();
        personEducationInfo_three.name = personName?.text.toString()
        personEducationInfo_three.resumeNo = resumeId.toString()
        personEducationInfo_three.course = personDegreeThree?.text.toString()
        personEducationInfo_three.school = personSchoolThree?.text.toString()
        personEducationInfo_three.score = personScoreThree?.text.toString()
        personEducationInfo_three.passYear = personPassThree?.text.toString()

        DatabaseClient.getInstance(applicationContext).appDatabase.personEducationInfoDAO()
            .updatepersonEducationInfo(personName?.text.toString(),personDegreeThree?.text.toString(),personSchoolThree?.text.toString(),personScoreThree?.text.toString(),personPassThree?.text.toString(),resumeId.toString())


        val intent = Intent(applicationContext, PreviewResumeScreen::class.java)
        startActivity(intent)
        finish()
    }

    private fun captureImage() {

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE),
                0
            )
        } else {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if (takePictureIntent.resolveActivity(packageManager) != null) {
                // Create the File where the photo should go
                try {
                    photoFile = createImageFile()
                    // Continue only if the File was successfully created
                    if (photoFile != null) {
                        val photoURI = FileProvider.getUriForFile(
                            this,
                            "com.naren.resumebuilder.fileprovider",
                            photoFile!!
                        )
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                        startActivityForResult(takePictureIntent, CAPTURE_IMAGE_REQUEST)
                    }
                } catch (ex: Exception) {
                    // Error occurred while creating the File
                    displayMessage(baseContext, ex.message.toString())
                }

            } else {
                displayMessage(baseContext, "Null")
            }
        }

    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        // Create an image file name
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageFileName = "JPEG_" + timeStamp + "_"
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val image = File.createTempFile(
            imageFileName, /* prefix */
            ".jpg", /* suffix */
            storageDir      /* directory */
        )

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.absolutePath
        return image
    }

    private fun displayMessage(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CAPTURE_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            val myBitmap = BitmapFactory.decodeFile(photoFile!!.absolutePath)
            personImage?.setImageBitmap(myBitmap)
        } else {
            displayMessage(baseContext, "Request cancelled or something went wrong.")
        }
    }
}