package com.bcit.budgetapp.Models

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class BillRepository {
    //Database
    var db: FirebaseFirestore = Firebase.firestore

    public fun addBill(bill: Bill)
    {

        db.collection("Bills")
            .add(bill)
            .addOnSuccessListener { documentReference ->
                Log.d("ADD BILL", "Added with ${documentReference.id}")
            }
            .addOnFailureListener{ e ->
                Log.w("ADD BILL", "Failed with ", e)
            }

    }
}