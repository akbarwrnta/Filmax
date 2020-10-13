package com.example.filmax.sign.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.filmax.R
import com.example.filmax.sign.sigin.SignInActivity
import com.example.filmax.sign.sigin.User
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    lateinit var sUsername: String
    lateinit var sPassword: String
    lateinit var sNama: String
    lateinit var sEmail: String

    lateinit var mDatabaseReference: DatabaseReference
    lateinit var mFirebaseInstance :FirebaseDatabase
    lateinit var mDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        mFirebaseInstance = FirebaseDatabase.getInstance()
        mDatabase = FirebaseDatabase.getInstance().getReference()
        mDatabaseReference = mFirebaseInstance.getReference("User")

        btn_signup.setOnClickListener {
            sUsername = etUsername.text.toString()
            sPassword = etPassword.text.toString()
            sNama = etNama.text.toString()
            sEmail = etEmail.text.toString()

            if (sUsername.equals("")) {
                etUsername.error = "Silahkan isi username Anda"
                etUsername.requestFocus()
            } else if (sPassword.equals("")) {
                etPassword.error = "Silahkan isi password Anda"
                etPassword.requestFocus()
            } else if (sNama.equals("")) {
                etNama.error = "Silahkan isi Nama Anda"
                etNama.requestFocus()
            } else if (sEmail.equals("")) {
                etEmail.error = "Silahkan isi Email Anda"
                etEmail.requestFocus()
            } else {
                saveUsername(sUsername, sPassword, sNama, sEmail)
            }

            btn_back.setOnClickListener {
                var intent = Intent(this@SignUpActivity, SignInActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun saveUsername(sUsername: String, sPassword: String, sNama: String, sEmail: String) {
        var user = User()
        user.email = sEmail
        user.username = sUsername
        user.nama = sNama
        user.password = sPassword

        if (sUsername != null){
            checkingUsername(sUsername, user)
        }
    }

    private fun checkingUsername(sUsername: String, data: User) {
        mDatabaseReference.child(sUsername).addValueEventListener(object : ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var user = dataSnapshot.getValue(User::class.java)
                if (user == null){
                    mDatabaseReference.child(sUsername).setValue(data)

                    var goSignupPhotoscreen = Intent (this@SignUpActivity,
                        SignUpPhotoActivity::class.java).putExtra("nama", data.nama)
                    startActivity(goSignupPhotoscreen)
                } else {
                    Toast.makeText(this@SignUpActivity,"User sudah digunakan",
                        Toast.LENGTH_LONG).show()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@SignUpActivity,""+databaseError.message,
                Toast.LENGTH_LONG).show()
            }

        })
    }
}


