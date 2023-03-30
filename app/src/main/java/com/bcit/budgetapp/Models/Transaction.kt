package com.bcit.budgetapp.Models

import java.util.*

enum class TransactionCategory
{
    ENTERTAINMENT,
    GROCERIES,
    TRANSPORTATION,
    CLOTHING,
    ELECTRICITY,
    HOUSING,
    WATER,
    PHONE,
    GAS
}

open class Transaction(val userUniqueID: String, val amount: Double, val date: Date, val category: TransactionCategory)
{
}