package com.bcit.budgetapp.Models

class Budget
{
    public var budgetAmount: Double = 0.0
    public val transactions = ArrayList<Transaction>()
    public val bills = ArrayList<Bill>()

    public fun addTransaction(transaction: Transaction)
    {
        transactions.add(transaction)
    }

    public fun getTotalSpent(): Double
    {
        var total = 0.0

        for(transaction: Transaction in transactions)
        {
            total += transaction.amount
        }

        return total
    }
}