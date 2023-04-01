package com.bcit.budgetapp.Views.MainFragments

import com.bcit.budgetapp.Models.Budget
import com.bcit.budgetapp.Models.TransactionCategory
import com.bcit.budgetapp.ViewModels.BudgetViewModel

interface sortFilterRecycler
{
    public fun sort(sortType: SortType)
    public fun filter(filterType: TransactionCategory, sortType: SortType, budgetViewModel: BudgetViewModel)
}