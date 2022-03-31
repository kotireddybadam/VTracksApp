package com.itok.vtracksapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Layout
import android.widget.*
import com.google.android.material.snackbar.Snackbar
import com.itok.vtracksapp.Bean.UserLogin
import com.itok.vtracksapp.DB.DatabaseHelper

class RegisterActivity : AppCompatActivity() {

    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val spinner: Spinner = findViewById(R.id.srRegUserRole)
        ArrayAdapter.createFromResource(this, R.array.userRolesList, android.R.layout.simple_spinner_item)
            .also { adapter ->adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinner.adapter = adapter
            }

        val userName = findViewById<TextView>(R.id.etRegUserName)
        val userPassword = findViewById<TextView>(R.id.etRegPassword)
        val userConfPassword = findViewById<TextView>(R.id.etRegConfirmPassword)
        val userRole: Spinner = findViewById(R.id.srRegUserRole)
        val register = findViewById<Button>( R.id.btRegSignUp)
        val logIn = findViewById<TextView>(R.id.tvRegGoToLogIn)
        //val registrationLayout = findViewById<LinearLayout>(R.id.registrationLayout)

        databaseHelper = DatabaseHelper(this)

        register.setOnClickListener()
        {
            var uname=userName.text.toString()
            var upass=userPassword.text.toString()
            var uconfpass=userConfPassword.text.toString()
            var urole=userRole.selectedItem.toString()


            val userCreation = UserLogin(username = uname, password = upass, userrole = urole)
            if (uname.trim().isEmpty()) {       // if (check(email))
                Toast.makeText(this, "User Name cannot be empty", Toast.LENGTH_LONG).show()
            } else if (upass.trim().isEmpty()) {       // if (check(email))
                Toast.makeText(this, "Password cannot be empty", Toast.LENGTH_LONG).show()
            }
            else if (uconfpass.trim().isEmpty()) {       // if (check(email))
                Toast.makeText(this, "Password cannot be empty", Toast.LENGTH_LONG).show()
            } else if (urole.trim().isEmpty()) {       // if (check(email))
                Toast.makeText(this, "Please select Role", Toast.LENGTH_LONG).show()
            }
            else if (upass != uconfpass) {
                Toast.makeText(this, "Password mismatch.", Toast.LENGTH_LONG).show()
            }
            else
            {
                databaseHelper.addUser(userCreation)
                Toast.makeText(this, "Registration Successful!", Toast.LENGTH_LONG).show()
                // automatically move to login page after 1.5 seconds
                Handler(Looper.getMainLooper()).postDelayed({
                    var i= Intent(this, MainActivity::class.java)
                    startActivity(i)
                },1500)
            }

        }


    }
}