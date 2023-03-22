package com.bcit.budgetapp.dataClasses

class Budget
{
    private val budgets = ArrayList<BudgetCategory>()

    public fun addBudgetCat(budgetCat: BudgetCategory)
    {
        budgets.add(budgetCat)
    }

    public fun getBudgets(): ArrayList<BudgetCategory>
    {
        return budgets
    }

    public fun getTotalSpent(): Float
    {
        var total = 0F
        for(budget: BudgetCategory in budgets )
        {
            total += budget.spentCad
        }

        return total
    }

    public fun getTotalLimit(): Float
    {
        var total = 0F
        for(budget: BudgetCategory in budgets )
        {
            total += budget.limitCad
        }

        return total
    }
}