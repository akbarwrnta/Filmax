package com.example.filmax.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.filmax.R
import com.example.filmax.sign.sigin.SignInActivity
import com.example.filmax.util.Preference
import kotlinx.android.synthetic.main.activity_onboardingone.*

class OnboardingoneActivity : AppCompatActivity() {

    lateinit var preference : Preference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboardingone)

        preference = Preference(this)

        if (preference.getValues("onboarding").equals("1")){
            preference.setValue("onboarding", "1")
            finishAffinity()

            var intent = Intent(this@OnboardingoneActivity, SignInActivity::class.java)
            startActivity(intent)
        }

        btn_next1.setOnClickListener {
            preference.setValue("onboarding", "1")
            finishAffinity()

            var intent = Intent(this@OnboardingoneActivity, OnboardingtwoActivity::class.java)
            startActivity(intent)
        }
    }
}