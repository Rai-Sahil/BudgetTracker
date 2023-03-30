package com.bcit.budgetapp.Models

import java.util.*

public enum class BillType
{
    ANNUALLY,
    MONTHLY,
    BIWEEKLY,
    WEEKLY
}

class Bill(userUniqueID: String, amount: Double, date: Date, category: TransactionCategory, val billType: BillType): Transaction(userUniqueID, amount, date, category)
{
}