package com.example.whiskr

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.whiskr.model.Note
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class CreateNoteActivity : AppCompatActivity() {

    private lateinit var etNote: EditText
    private lateinit var btnSave: Button
    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_note)

        etNote = findViewById(R.id.etNote)
        btnSave = findViewById(R.id.btnSave)

        btnSave.setOnClickListener {
            val content = etNote.text.toString().trim()
            val uid = auth.currentUser?.uid
            if (content.isNotEmpty() && uid != null) {
                val note = Note(userId = uid, content = content, timestamp = System.currentTimeMillis())
                db.collection("notes").add(note)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Note saved", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_LONG).show()
                    }
            } else {
                Toast.makeText(this, "Please enter a note", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
