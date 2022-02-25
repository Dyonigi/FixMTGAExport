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
        val cardListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                val post = dataSnapshot.value
                // ...
            }

            override fun onCancelled(error: DatabaseError) {
                val post = ""
            }
        }
        database.addListenerForSingleValueEvent(cardListener)

        fix.setOnClickListener {
            val textinput = findViewById<TextInputEditText>(R.id.input)
            val input = textinput.text.toString().lines()
            var list = ""
            input.forEach {
                var remainingName = ""
                database.child(it).get().addOnSuccessListener{
                    remainingName = it.get().toString()
                }.addOnFailureListener{
                    remainingName = ""
                }
                list = list + "\n" + it
            }
            fixedlist.text = list



        }
    }
}