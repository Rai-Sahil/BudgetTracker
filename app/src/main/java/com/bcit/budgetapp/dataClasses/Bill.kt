package com.bcit.budgetapp.dataClasses

import java.util.Date

enum class BillCategory
{
    ELECTRICITY,
    HOUSING,
    WATER,
    PHONE,
    GAS
}

class Bill(val amount: Float, val category: BillCategory, val dueDate: Date)
{
}