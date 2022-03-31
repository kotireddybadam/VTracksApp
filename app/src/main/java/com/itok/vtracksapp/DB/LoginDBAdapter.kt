package com.itok.vtracksapp.DB

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.itok.vtracksapp.Bean.UserLogin
private val DB_NAME = "C:\\LocalData\\Learning\\BITS\\VaccinationSystemDB\\login.db"
private val DB_VERSION = 1

public class LoginDBAdapter(context: Context)
    :SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    val TAG = LoginDBAdapter::class.java.simpleName

    private val TABLE_LOGIN = "USERSLIST"
    private val COLUMN_ID = "USERID"
    private val COLUMN_USERNAME = "USERNAME"
    private val COLUMN_PASSWORD = "PASSWORD"
    private val COLUMN_USERROLE = "USERROLE"

    private val createTable = ("CREATE TABLE " + TABLE_LOGIN + "" +
            "(" + COLUMN_ID + " INTEGER PRIMARY KEY, " + COLUMN_USERNAME + " TEXT NOT NULL, "
            + COLUMN_PASSWORD + " TEXT NOT NULL," +  COLUMN_USERROLE + " TEXT NOT NULL)")
    // drop table sql query
    private val dropTable = "DROP TABLE IF EXISTS $TABLE_LOGIN"

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(createTable)
        Log.v(TAG, "Table created")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(dropTable)//Drop User Table if exist
        Log.v(TAG, "Table Dropped")
        onCreate(db)// Create tables again
    }

    fun insertUser(userLogin: UserLogin) : Int {
        val returnCheck: Int
        val db = this.writableDatabase

        val values = ContentValues()
        values.put(COLUMN_USERNAME, userLogin.username)
        values.put(COLUMN_PASSWORD, userLogin.password)
        values.put(COLUMN_USERROLE, userLogin.userrole)
        returnCheck = db.insert(TABLE_LOGIN, null, values).toInt()
        db.close()
        return returnCheck
    }

    @SuppressLint("Range")
    fun displayUser(userID: String): UserLogin{

        // array of columns to fetch
        val columns = arrayOf(COLUMN_ID, COLUMN_USERNAME, COLUMN_PASSWORD, COLUMN_USERROLE)
        // selection argument
        val selectionArgs = arrayOf(userID)
        val db = this.readableDatabase
        println(userID)
        // query the user table
        val cursor = db.query(
            TABLE_LOGIN,                //Table to query
            columns,                    //columns to return
            "$COLUMN_ID = ?",  //columns for the WHERE clause
            selectionArgs,             //The values for the WHERE clause
            null,               //group the rows
            null,                //filter by row groups
            null                //The sort order
        )
        var user = UserLogin(username = "",password = "", userrole="")
        if (cursor != null && cursor.count > 0) {
            cursor.moveToFirst()

            user = UserLogin(
                username = cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME)),
                password = cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD)),
                userrole = cursor.getString(cursor.getColumnIndex(COLUMN_USERROLE)))
            cursor.close()
            db.close()

        }
        return user

    }
    fun updateUser(userLogin: UserLogin): Int{
        val db = this.writableDatabase
        var returnCheck: Int = -1
        val values = ContentValues()
        values.put(COLUMN_USERNAME, userLogin.username)
        values.put(COLUMN_PASSWORD, userLogin.password)
        // updating row
        returnCheck = db.update(
            TABLE_LOGIN, values, "$COLUMN_ID = ?",
            arrayOf(userLogin.username.toString())
        )

        db.close()
        return returnCheck
    }
    fun deleteUser(userID: String): Int {
        val db = this.writableDatabase
        var returnCheck: Int = -1
        // delete user record by id
        returnCheck = db.delete(TABLE_LOGIN, "$COLUMN_ID = ?", arrayOf(userID))
        db.close()
        return returnCheck
    }
}