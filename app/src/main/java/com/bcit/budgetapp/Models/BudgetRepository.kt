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

    fun getBudget(userUniqueID: String, category: TransactionCategory, onComplete: (budget: Budget?) -> Unit) {
        db.collection("Budget")
            .whereEqualTo("userUniqueID", userUniqueID)
            .whereEqualTo("category", category)
            .get()
            .addOnSuccessListener { querySnapshot ->
                if (querySnapshot.documents.isNotEmpty()) {
                    val document = querySnapshot.documents[0]
                    val budget = document.toObject(Budget::class.java)
                    onComplete(budget)
                } else {
                    onComplete(null)
                }
            }
            .addOnFailureListener { e ->
                Log.w("GET BUDGET", "Failed with ", e)
                onComplete(null)
            }
    }

}