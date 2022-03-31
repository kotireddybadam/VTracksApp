package com.itok.vtracksapp.DB

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.itok.vtracksapp.Bean.SchoolInfo
import com.itok.vtracksapp.Bean.StaffInfo
import com.itok.vtracksapp.Bean.UserLogin
import com.itok.vtracksapp.Bean.VaccinationProvidersInfo

class DatabaseHelper(context: Context): SQLiteOpenHelper(context, dbname, null, dbversion)
{
    companion object{
        private val dbname="first.db"
        private val dbversion=2
    }
    override fun onCreate(p0: SQLiteDatabase?) {
        p0!!.execSQL("create table USERSLIST(USERID Integer Primary Key AutoIncrement, USERNAME Text,PASSWORD Text,USERROLE Text)")
        p0!!.execSQL("create table schoolInfo (SchoolId Integer Primary Key AutoIncrement, " +
                         "SchoolName Text, mailId Text, contactNo Text, Address Text)")
        p0!!.execSQL("create table staffInfo (StaffId Integer Primary Key AutoIncrement, " +
                "FirstName Text, lastName Text, SchoolName Text, Address Text)")
        p0!!.execSQL("create table VaccinationProvidersInfo (VaccId Integer Primary Key AutoIncrement, " +
                "ProviderName Text, MailId Text, ContactNumber Text, Address Text)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0!!.execSQL("drop table if exists USERSLIST")
        p0!!.execSQL("drop table if exists schoolInfo")
        p0!!.execSQL("drop table if exists staffInfo")
        p0!!.execSQL("drop table if exists VaccinationProvidersInfo")
        onCreate(p0)
    }

    fun addUser(user: UserLogin){
        val db=this.writableDatabase

        val values=ContentValues()
        values.put("USERNAME",user.username)
        values.put("PASSWORD",user.password)
        values.put("USERROLE",user.userrole)

        db.insert("USERSLIST", null, values)
        db.close()
    }

    fun addSchool(school: SchoolInfo){
        val db=this.writableDatabase

        val values=ContentValues()
        values.put("schoolname",school.schoolName)
        values.put("mailId",school.mailId)
        values.put("contactNo",school.contactNo)
        values.put("Address",school.Address)

        db.insert("schoolInfo", null, values)
        db.close()
    }

    fun addStaff(staff: StaffInfo){
        val db=this.writableDatabase

        val values=ContentValues()
        values.put("FirstName",staff.firstName)
        values.put("lastName",staff.lastName)
        values.put("SchoolName",staff.schoolName)
        values.put("Address",staff.Address)

        db.insert("staffInfo", null, values)
        db.close()
    }

    fun addVacProvider(vacProvider: VaccinationProvidersInfo){
        val db=this.writableDatabase

        val values=ContentValues()
        values.put("ProviderName",vacProvider.ProviderName)
        values.put("MailId",vacProvider.MailId)
        values.put("ContactNumber",vacProvider.ContactNumber)
        values.put("Address",vacProvider.Address)

        db.insert("VaccinationProvidersInfo", null, values)
        db.close()
    }

    fun loginAuthentication(email:String, password:String, role:String): Boolean {
        val columns= arrayOf("USERID", "USERNAME", "PASSWORD", "USERROLE")
        val db=this.readableDatabase

        val where="USERNAME=? and PASSWORD=?"

        val selectionArgs= arrayOf(email,password)

        val cursor=db.query("USERSLIST", columns, where, selectionArgs,null, null, null, null)
        val countRows=cursor.count
        cursor.close()
        db.close()

        return countRows>0

    }
}