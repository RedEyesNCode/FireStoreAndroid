    package com.redeyesncode.firestoreandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.redeyesncode.firestoreandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    var fireStoreDB:FirebaseFirestore ?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this@MainActivity))
        setupFirestoreForAndroid()

        binding.btnSaveFirestore.setOnClickListener {
            if(binding.edtName.text.toString().isEmpty()){
                showDialogMessage("Please enter","Alert !")
            }else if(binding.edtPassword.text.toString().isEmpty()){
                showDialogMessage("Please enter","Alert !")
            }else if(binding.edtEmail.text.toString().isEmpty()){
                showDialogMessage("Please enter","Alert !")
            }else{
                addDataToFirestore()
            }



        }
        binding.btnSaveFireStoreCustomId.setOnClickListener {
            if(binding.edtCustomIdFirebase.text.toString().isEmpty()){
                addFirestoreDataWithCustomId(binding.edtCustomIdFirebase.text.toString())
            }else{
                showDialogMessage("Please enter Id","Alert !")
            }


        }
        binding.btnGetFirestoreDATA.setOnClickListener {
            Intent(this@MainActivity,GetFireStoreActivity::class.java).apply {
                startActivity(this)
            }

        }
        setContentView(binding.root)
    }

    private fun addDataToFirestore() {
        val userInfoMap = hashMapOf(
            "name" to binding.edtName.text.toString(),
            "email" to binding.edtEmail.text.toString(),
            "password" to binding.edtPassword.text.toString()

        )

        // You can also write the name of any new collection here.


        // Add document to collection with an autogenerated id.
        fireStoreDB!!.collection("android_firestore").add(userInfoMap).addOnSuccessListener {
            showDialogMessage("Firestore Saved Document ID-> ${it.id}","Firestore Alert !")
        }.addOnFailureListener {
            showDialogMessage("onFailure ${it.message.toString()}","Firestore Alert !")

        }








    }
    private fun addFirestoreDataWithCustomId(customId: String) {
        val userInfoMapOwnId = hashMapOf(
            "name" to "Custom_ID",
            "last" to "Id_Custom",
            "email" to "redeyesncode@gmail.com",
            "customId" to customId

        )
        // Add Document to Collection with your own Id.
//        When you use set() to create a document, you must specify an ID for the document to create.
        fireStoreDB!!.collection("android_firestore").document(customId).set(userInfoMapOwnId).addOnSuccessListener {
            showDialogMessage("Firestore Saved Document ID-> ${customId}","Firestore Alert !")
        }.addOnFailureListener {
            showDialogMessage("onFailure ${it.message.toString()}","Firestore Alert !")

        }



    }

    private fun showDialogMessage(message: String,Title:String){
        val builder = AlertDialog.Builder(this)
        builder.setTitle(Title)
        builder.setMessage(message)
//builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))

        builder.setPositiveButton(android.R.string.yes) { dialog, _ ->
            dialog.dismiss()

        }


        builder.show()

    }

    private fun setupFirestoreForAndroid(){
        // Firestore contains collections and the collection contains various documents.
        // for example there will be 2 collection namely as users, orders
        // in this users will contain user related documents, and orders will contain order related documents

        fireStoreDB = Firebase.firestore







    }

}