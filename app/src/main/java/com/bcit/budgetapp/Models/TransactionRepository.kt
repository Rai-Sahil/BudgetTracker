package com.bcit.budgetapp.Models

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class TransactionRepository {
    //Database
    var db: FirebaseFirestore = Firebase.firestore

    fun addTransaction(transaction: Transaction){
        db.collection("Transaction")
            .add(transaction)
            .addOnSuccessListener { documentReference ->
                Log.d("ADD TRANSACTION", "Added with ${documentReference.id}")
            }
            .addOnFailureListener{ e ->
                Log.w("ADD TRANSACTION", "Failed with ", e)
            }
    }
}