package com.itok.vtracksapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.itok.vtracksapp.Bean.SchoolInfo
import com.itok.vtracksapp.Bean.StaffInfo
import com.itok.vtracksapp.Bean.UserLogin
import com.itok.vtracksapp.DB.DatabaseHelper

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [StaffRegFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StaffRegFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_staff_reg, container, false)

        val lFirstName = view.findViewById<TextView>(R.id.etStFirstName)
        val lLastName = view.findViewById<TextView>(R.id.etStLastName)
        val lSchoolName = view.findViewById<TextView>(R.id.etStSchoolName)
        val lUserName = view.findViewById<TextView>(R.id.etStUserName)
        val lPassword = view.findViewById<TextView>(R.id.etStPassword)
        val lConfPassword = view.findViewById<TextView>(R.id.etStConfPassword)
        val lRegButton = view.findViewById<Button>(R.id.btStRegister)
        val lResetButton = view.findViewById<Button>(R.id.btStReset)

        databaseHelper = DatabaseHelper(this.requireContext())

        lRegButton.setOnClickListener()
        {
            val staffCreation = StaffInfo( firstName = lFirstName.text.toString(),
                lastName = lLastName.text.toString(),
                schoolName = lSchoolName.text.toString(),
                Address= "")
            val userCreation = UserLogin( username = lUserName.text.toString(),
                password = lPassword.text.toString(),
                userrole = "STAFF")

            databaseHelper.addUser(userCreation)
            databaseHelper.addStaff(staffCreation)
            Toast.makeText(this.requireContext(), "Registration Successful!", Toast.LENGTH_LONG).show()

        }
        lResetButton.setOnClickListener()
        {

        }

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment StaffRegFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            StaffRegFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}