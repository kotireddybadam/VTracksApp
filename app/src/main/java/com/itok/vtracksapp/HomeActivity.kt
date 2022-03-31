package com.itok.vtracksapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menuitem, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
            R.id.menuUsersData -> Toast.makeText(this, "menu Staff Data", Toast.LENGTH_LONG).show()
            R.id.menuStudentsData -> Toast.makeText(this, "menu Students Data", Toast.LENGTH_LONG).show()
            R.id.menuAddUpdateStudentsData -> Toast.makeText(this, "menu Add/Update Students Data", Toast.LENGTH_LONG).show()
        }
        return super.onOptionsItemSelected(item)
    }
}