package com.example.filmax.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.filmax.R
import com.example.filmax.sign.sigin.SignInActivity
import kotlinx.android.synthetic.main.activity_onboardingthree.*

class OnboardingthreeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboardingthree)

        btn_next3.setOnClickListener{
            var intent = Intent(this@OnboardingthreeActivity, SignInActivity::class.java)
            startActivity(intent)
        }
    }
}