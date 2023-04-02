package com.bcit.budgetapp.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bcit.budgetapp.Models.*
import com.google.android.gms.tasks.OnSuccessListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Duration
import java.time.Instant
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.Date
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.milliseconds

class BudgetViewModel : ViewModel()
{
    val userID: String = "sahilrai"
    val transactions = ArrayList<Transaction>()

    //Live Data
    val allTransaction: MutableLiveData<List<Transaction>> = MutableLiveData<List<Transaction>>()
    val allBills: MutableLiveData<List<Bill>> = MutableLiveData<List<Bill>>()
    val budgets: MutableLiveData<ArrayList<Budget>> = MutableLiveData<ArrayList<Budget>>()

    private val budgetRepository = BudgetRepository()
    private val transactionRepository = TransactionRepository()
    private val billRepository = BillRepository()

    private var lastDate = Date.from(Instant.now())
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

    public fun getTotalSpent(transactionCategory: TransactionCategory = TransactionCategory.NONE): Double
    {
        var total = 0.0

        for(transaction: Transaction in allTransaction.value!!)
        {
            if(transactionCategory == TransactionCategory.NONE)
            {
                total += transaction.amount!!
            }
            else if(transaction.category == transactionCategory)
            {
                total += transaction.amount!!
            }
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

    public fun updateTransactions()
    {
        val currentDate: Date = Date.from(Instant.now())
        val distanceDays = ChronoUnit.DAYS.between(Instant.now(), lastDate.toInstant());

        if(distanceDays <= -1)
        {
            for(bill in allBills.value!!)
            {
                val newDate: Date = bill.date?.clone() as Date


                if(bill.billType == BillType.ANNUALLY)
                {
                    newDate.year = currentDate.year
                    val transaction: Transaction = Transaction(bill.userUniqueID, bill.amount, newDate, bill.category)

                    if(transaction.date!! <  currentDate && transaction.date > lastDate)
                    {
                        addTransaction(transaction)
                    }
                }
                else if(bill.billType == BillType.MONTHLY)
                {
                    newDate.year = currentDate.year
                    newDate.month = currentDate.month
                    val transaction: Transaction = Transaction(bill.userUniqueID, bill.amount, newDate, bill.category)
                    if(transaction.date!! < currentDate && transaction.date > lastDate)
                    {
                        addTransaction(transaction)
                    }
                }
            }
        }
        lastDate = Date.from(Instant.now())
    }
}