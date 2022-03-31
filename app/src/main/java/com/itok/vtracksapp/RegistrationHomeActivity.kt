package com.itok.vtracksapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class RegistrationHomeActivity : AppCompatActivity() {

    val aTAG = "Activity States"
    var i = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration_home)

        Log.d(aTAG, "Test Home-1" )
        val hrspinner: Spinner = findViewById(R.id.spRoleSel)
        ArrayAdapter.createFromResource(this, R.array.userRolesList, android.R.layout.simple_spinner_item)
            .also { adapter ->adapter.setDropDownViewResource(R.layout.spinner_list)
                hrspinner.adapter = adapter
            }

        Log.d(aTAG, "Test Home-2" )
        val selectedRole: Spinner = findViewById(R.id.spRoleSel)
        var urole = selectedRole.selectedItem.toString()

        Log.d(aTAG, "Test Home-3" )
        supportFragmentManager.beginTransaction().
        apply {
            replace(R.id.regDataFragment, SchoolRegFragment.newInstance("",""))
            commit()
            addToBackStack(null)
        }
        Log.d(aTAG, "Test Home-4" )
        Log.d(aTAG, "Test Home-4 ${urole}" )
        /* Log.d(aTAG, "Test Home-5" )
         Log.d(aTAG, "Test Home-1:: ${urole}" )*/
        selectedRole.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectRole = adapterView?.getItemAtPosition(position).toString()
                Toast.makeText(this@RegistrationHomeActivity, "You selected ${selectRole} ", Toast.LENGTH_LONG).show()
                if (selectRole.equals("SCHOOL", true))
                {
                    supportFragmentManager.beginTransaction().
                    apply {
                        replace(R.id.regDataFragment, SchoolRegFragment.newInstance("",""))
                        commit()
                        addToBackStack(null)
                    }
                }
                else if (selectRole.equals("STAFF", true))
                {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.regDataFragment, StaffRegFragment.newInstance("",""))
                        commit()
                        addToBackStack(null)
                    }
                }
                else if (selectRole.equals("VPA", true))
                {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.regDataFragment, VacProvRegFragment.newInstance("",""))
                        commit()
                        addToBackStack(null)
                    }
                }
            }
        }
         /*selectedRole.setOnClickListener()
         {
             urole = selectedRole.selectedItem.toString()
             Log.d(aTAG, "Test Home-6" )
             if (urole.equals("SCHOOL", true))
             {
                 supportFragmentManager.beginTransaction().
                 apply {
                     replace(R.id.regDataFragment, SchoolRegFragment.newInstance("",""))
                     commit()
                     addToBackStack(null)
                 }
             }
             else if (urole.equals("STAFF", true))
             {
                 supportFragmentManager.beginTransaction().apply {
                     replace(R.id.regDataFragment, StaffRegFragment.newInstance("",""))
                     commit()
                     addToBackStack(null)
                 }
             }
             else if (urole.equals("VPA", true))
             {
                 supportFragmentManager.beginTransaction().apply {
                     replace(R.id.regDataFragment, VacProvRegFragment.newInstance("",""))
                     commit()
                     addToBackStack(null)
                 }
             }
         }*/
    }
}