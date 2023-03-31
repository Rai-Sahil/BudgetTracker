package com.bcit.budgetapp.Views.MainFragments

import com.bcit.budgetapp.Models.Budget
import com.bcit.budgetapp.Models.TransactionCategory

interface sortFilterRecycler
{
    public fun sort(sortType: SortType)
    public fun filter(filterType: TransactionCategory, sortType: SortType, budget: Budget)
}