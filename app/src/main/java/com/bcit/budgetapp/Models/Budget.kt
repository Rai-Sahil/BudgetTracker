package com.bcit.budgetapp.Models

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Budget(val userUniqueID: String, val amount: Double, val category: TransactionCategory)
{

}