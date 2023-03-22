package com.bcit.budgetapp.dataClasses

import java.util.Date

enum class PurchaseCategory
{
    ENTERTAINMENT,
    GROCERIES,
    TRANSPORTATION,
    CLOTHING
}

class Purchase(val amount: Float, val date: Date, val category: PurchaseCategory)
{
}