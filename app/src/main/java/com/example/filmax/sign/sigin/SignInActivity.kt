package com.example.filmax.sign.sigin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.filmax.home.HomeActivity
import com.example.filmax.R
import com.example.filmax.sign.signup.SignUpActivity
import com.example.filmax.util.Preference
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : AppCompatActivity() {

    lateinit var  iUsername:String
    lateinit var  iPassword:String

    lateinit var mDatabase : DatabaseReference
    lateinit var preference : Preference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        preference = Preference(this)
        mDatabase = FirebaseDatabase.getInstance().getReference("User")

        preference.setValue("onboarding","1")
        if (preference.getValues("status").equals("1")){
            finishAffinity()

            var goHome = Intent(this@SignInActivity, HomeActivity::class.java)
            startActivity(goHome)
        }

        txt_login.setOnClickListener {
            var intent = Intent(this@SignInActivity, SignUpActivity::class.java)
            startActivity(intent)
        }

        btn_signin.setOnClickListener {
            iUsername = etUsername.text.toString()
            iPassword = etPassword.text.toString()

            if (iUsername.equals("")) {
                etUsername.error = "Silahkan masukkan username Anda"
                etUsername.requestFocus()
            }
            else if (iPassword.equals("")) {
                etPassword.error = "Silahkan masukkan password Anda"
                etPassword.requestFocus()
            }
            else {
                pushLogin(iUsername, iPassword)
            }
        }
    }

    private fun pushLogin(iUsername: String, iPassword: String) {
        mDatabase.child(iUsername).addValueEventListener(object : ValueEventListener{
            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@SignInActivity, databaseError.message,
                    Toast.LENGTH_LONG).show()
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {

                var user = dataSnapshot.getValue(User::class.java)
                if (user == null){
                    Toast.makeText(this@SignInActivity, "User tidak ditemukan",
                        Toast.LENGTH_LONG).show()
                } else {
                    if (user.password.equals(iPassword)){

                        preference.setValue("nama", user.nama.toString())
                        preference.setValue("user", user.username.toString())
                        preference.setValue("url", user.url.toString())
                        preference.setValue("email", user.email.toString())
                        preference.setValue("saldo", user.saldo.toString())
                        preference.setValue("status", "1")


                        var intent = Intent(this@SignInActivity, HomeActivity::class.java)
                        startActivity(intent)
                    }
                    else {
                        Toast.makeText(this@SignInActivity, "Password Anda Salah",
                            Toast.LENGTH_LONG).show()
                    }

                }
            }

        })
    }

}