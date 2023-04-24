package com.redeyesncode.firestoreandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.redeyesncode.firestoreandroid.databinding.ActivityGetFireStoreBinding

class GetFireStoreActivity : AppCompatActivity() {
    var fireStoreDB: FirebaseFirestore?=null

    lateinit var binding:ActivityGetFireStoreBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGetFireStoreBinding.inflate(layoutInflater)
        setupFirestoreForAndroid()

        setContentView(binding.root)
    }
    private fun setupFirestoreForAndroid(){
        fireStoreDB = Firebase.firestore
        val docRef = fireStoreDB!!.collection("android_firestore").get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    // Now you can cas this to your own defined data and set according into recyclerviews if required.

                    Log.d("DEV_FIREBASE", "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("DEV_FIREBASE", "Error getting documents: ", exception)
            }

    }
}