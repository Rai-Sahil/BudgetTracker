package com.bcit.budgetapp.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bcit.budgetapp.Models.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BudgetViewModel : ViewModel()
{
    //val budget = Budget(clothing, 200)
    val budgets = ArrayList<Budget>()
    val transactions = ArrayList<Transaction>()
    val bills = ArrayList<Bill>()

    //Live Data
    val allTransaction: MutableLiveData<List<Transaction>> = MutableLiveData<List<Transaction>>()
    val allBills: MutableLiveData<List<Bill>> = MutableLiveData<List<Bill>>()

    private val budgetRepository = BudgetRepository()
    private val transactionRepository = TransactionRepository()
    private val billRepository = BillRepository()

    init{
        observeTransactions()
        observerBills()
    }

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
            total += transaction.amount!!
        }

        return total
    }

    private fun observeTransactions(){
        val scope = CoroutineScope(Dispatchers.Main)

        scope.launch {
            transactionRepository.getTransactionFlow().collect{
                allTransaction.value = it
            }
        }
    }

    private fun observerBills(){
        val scope = CoroutineScope(Dispatchers.Main)

        scope.launch {
            billRepository.getBillsFlow().collect{
                allBills.value = it
            }
        }
    }

}