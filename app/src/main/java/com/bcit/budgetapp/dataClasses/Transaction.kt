package com.bcit.budgetapp.dataClasses

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

class Transaction(val amount: Double, val date: Date, val category: TransactionCategory)
{
}