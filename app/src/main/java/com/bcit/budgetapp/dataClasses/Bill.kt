package com.bcit.budgetapp.dataClasses

import java.util.*

public enum class BillType
{
    ANNUALLY,
    MONTHLY,
    BIWEEKLY,
    WEEKLY
}

class Bill(amount: Double, date: Date, category: TransactionCategory, val billType: BillType): Transaction(amount, date, category)
{
}