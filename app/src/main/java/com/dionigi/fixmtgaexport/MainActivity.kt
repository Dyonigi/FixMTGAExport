package com.dionigi.fixmtgaexport

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
//import android.view.View
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

private lateinit var database: DatabaseReference

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fix = findViewById<Button>(R.id.fix)
        val fixedlist = findViewById<TextView>(R.id.fixedlist)
        database = Firebase.database.reference

        fix.setOnClickListener {
            val textinput = findViewById<TextInputEditText>(R.id.input)
            val input = textinput.text.toString().lines()
            fixedlist.text = ""
            input.forEach { card ->
                database.child(card).get().addOnSuccessListener{ secondName ->
                    if(secondName.exists()){
                        fixedlist.text = fixedlist.text.toString() + card + secondName.value.toString() + "\n"
                    }else{
                        fixedlist.text = fixedlist.text.toString() + card + "\n"
                    }
                }.addOnFailureListener{
                    fixedlist.text = fixedlist.text.toString() + card + "\n"
                }
            }



        }
    }
}