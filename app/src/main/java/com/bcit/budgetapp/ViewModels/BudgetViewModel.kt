package com.bcit.budgetapp.ViewModels

import androidx.lifecycle.ViewModel
import com.bcit.budgetapp.Models.*

class BudgetViewModel : ViewModel()
{
    //val budget = Budget(clothing, 200)
    val budgets = ArrayList<Budget>()
    val transactions = ArrayList<Transaction>()
    val bills = ArrayList<Bill>()

    private val budgetRepository = BudgetRepository()
    private val transactionRepository = TransactionRepository()
    private val billRepository = BillRepository()

    public fun addTransaction(transaction: Transaction)
    {
        transactionRepository.addTransaction(transaction)
        transactions.add(transaction)
    }

    public fun addBill(bill: Bill)
    {
        billRepository.addBill(bill)
        bills.add(bill)
    }

    public fun addBudget(budget: Budget){
        budgetRepository.addBudget(budget)
        budgets.add(budget)
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