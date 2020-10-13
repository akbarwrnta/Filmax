package com.example.filmax.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.filmax.R
import kotlinx.android.synthetic.main.activity_onboardingtwo.*

class OnboardingtwoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboardingtwo)

        btn_next2.setOnClickListener {
            var intent = Intent(this@OnboardingtwoActivity, OnboardingthreeActivity::class.java)
            startActivity(intent)
        }
    }
}