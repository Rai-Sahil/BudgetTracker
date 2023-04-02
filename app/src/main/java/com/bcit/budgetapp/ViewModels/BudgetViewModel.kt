package com.bcit.budgetapp.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bcit.budgetapp.Models.*
import com.google.android.gms.tasks.OnSuccessListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BudgetViewModel : ViewModel()
{
    val userID: String = "sahilrai"
    val transactions = ArrayList<Transaction>()
    val bills = ArrayList<Bill>()

    //Live Data
    val allTransaction: MutableLiveData<List<Transaction>> = MutableLiveData<List<Transaction>>()
    val allBills: MutableLiveData<List<Bill>> = MutableLiveData<List<Bill>>()
    val budgets: MutableLiveData<ArrayList<Budget>> = MutableLiveData<ArrayList<Budget>>()

    private val budgetRepository = BudgetRepository()
    private val transactionRepository = TransactionRepository()
    private val billRepository = BillRepository()

    init{
        observeTransactions()
        observerBills()
        loadBudgets()
    }

    fun addTransaction(transaction: Transaction)
    {
        transactionRepository.addTransaction(transaction)
        transactions.add(transaction)
    }

    fun addBill(bill: Bill)
    {
        billRepository.addBill(bill)
        bills.add(bill)
    }

    fun addOrUpdateBudget(budget: Budget){
        val matchingBudget = budgets.value!!.firstOrNull { it == budget }
        if (matchingBudget != null){
            budget.id = matchingBudget.id
            updateBudget(budget)
        } else {
            addBudget(budget)
        }
    }

    private fun addBudget(budget: Budget){
        budgetRepository.addBudget(budget) { savedBudget ->
            budgets.value?.add(savedBudget)
            budgets.value = budgets.value
        }
    }

    private fun updateBudget(budget: Budget){
        budgetRepository.updateBudget(budget) { updatedBudget ->
            budgets.value?.remove(budget)
            budgets.value?.add(updatedBudget)
            budgets.value = budgets.value
        }
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

    fun getTotalBudget(): Double {
        var totalBudget = 0.0
        for (budget: Budget in budgets.value!!){
            totalBudget += budget.amount!!
        }
        return totalBudget
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
    private fun loadBudgets(){
        budgets.value = ArrayList()
        budgetRepository.getBudgetsForUser(userID) { budget ->
            budgets.value?.add(budget)
            budgets.value = budgets.value
        }
    }

}


