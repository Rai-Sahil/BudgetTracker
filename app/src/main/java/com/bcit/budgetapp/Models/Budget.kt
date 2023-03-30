package com.bcit.budgetapp.Models

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Budget
{
    public var budgetAmount: Double = 0.0
    public val transactions = ArrayList<Transaction>()
    public val bills = ArrayList<Bill>()

    //Database
    lateinit var db: FirebaseFirestore

    public fun addTransaction(transaction: Transaction)
    {
        db = Firebase.firestore

        db.collection("Transaction")
            .add(transaction)
            .addOnSuccessListener { documentReference ->
                Log.d("ADD TRANSACTION", "Added with ${documentReference.id}")
            }
            .addOnFailureListener{ e ->
                Log.w("ADD TRANSACTION", "Failed with ", e)
            }

        transactions.add(transaction)
    }

    public fun addBill(bill: Bill)
    {
        bills.add(bill)
    }

    public fun getTotalSpent(): Double
    {
        var total = 0.0

        for(transaction: Transaction in transactions)
        {
            total += transaction.amount
        }

        return total
    }
}