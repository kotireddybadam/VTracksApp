package com.itok.vtracksapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import com.google.android.material.snackbar.Snackbar
import com.itok.vtracksapp.Bean.UserLogin
import com.itok.vtracksapp.DB.DatabaseHelper

class MainActivity : AppCompatActivity() {

    val aTAG = "Activity States"
    var i = 0
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val spinner: Spinner = findViewById(R.id.srUserRole)
        ArrayAdapter.createFromResource(this, R.array.userRolesList, android.R.layout.simple_spinner_item)
                       .also { adapter ->adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                           spinner.adapter = adapter
                       }

        val userName = findViewById<TextView>(R.id.etUserName)
        val userPassword = findViewById<TextView>(R.id.etPassword)
        val userRole: Spinner = findViewById(R.id.srUserRole)
        val logIn = findViewById<Button>( R.id.btLogIn)
        val signUp = findViewById<TextView>(R.id.tvCreateAccount)

        databaseHelper= DatabaseHelper(this)

        logIn.setOnClickListener()
        {
            Log.d(aTAG, "Orientation: ${userName.text}" )
            Log.d(aTAG, "Orientation: ${userPassword.text}" )
            Log.d(aTAG, "Orientation: ${userRole.selectedItem.toString()}" )

            var uname=userName.text.toString()
            var upass=userPassword.text.toString()
            var urole=userRole.selectedItem.toString()

            if (uname.trim().isEmpty()) {       // if (check(email))
                Toast.makeText(this, "User Name cannot be empty", Toast.LENGTH_LONG).show()
            } else if (upass.trim().isEmpty()) {       // if (check(email))
                Toast.makeText(this, "Password cannot be empty", Toast.LENGTH_LONG).show()
            }

            val check = databaseHelper.loginAuthentication(uname, upass, urole)

            if (check){
                if (urole.equals("SCHOOL", true))
                {
                    intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                }
                else if (urole.equals("STAFF", true))
                {
                    intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                }
                else if (urole.equals("VPA", true))
                {
                    intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                }
            }
            else{
                Toast.makeText(this, "Please Enter valid combination of User Name, Password and Role", Toast.LENGTH_LONG).show()
            }

            Toast.makeText(this, userName.text, Toast.LENGTH_LONG)
        }

        /*signUp.setOnClickListener {
            var regIntet =Intent(this, RegisterActivity::class.java)
            startActivity(regIntet)
        }*/

        signUp.setOnClickListener {
            Log.d(aTAG, "Inside OnClick Listener-1")
            var regIntet =Intent(this, RegistrationHomeActivity::class.java)
            startActivity(regIntet)
            Log.d(aTAG, "Inside OnClick Listener-2")
        }


    }


}