package com.bcit.budgetapp.Models

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class BudgetRepository {

    //Database
    var db: FirebaseFirestore = Firebase.firestore

    fun addBudget(budget: Budget){
        db.collection("Budget")
            .add(budget)
            .addOnSuccessListener { documentReference ->
                Log.d("ADD BUDGET", "Added with ${documentReference.id}")
            }
            .addOnFailureListener{ e ->
                Log.w("ADD BUDGET", "Failed with ", e)
            }
    }
}