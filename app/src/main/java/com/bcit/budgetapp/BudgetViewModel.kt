package com.bcit.budgetapp

import androidx.lifecycle.ViewModel
import com.bcit.budgetapp.dataClasses.Budget

class BudgetViewModel : ViewModel()
{
    val budget = Budget()
}