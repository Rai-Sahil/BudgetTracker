package com.bcit.budgetapp.mainFrags

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.bcit.budgetapp.MainActivity
import com.bcit.budgetapp.R
import com.bcit.budgetapp.dataClasses.Transaction
import com.bcit.budgetapp.dataClasses.TransactionCategory
import java.sql.Date

class ExpenseFragment : Fragment()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_expense, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        doTransactionStuff(view)
        var button = view.findViewById<Button>(R.id.button_expense_add).setOnClickListener{
            val datePicker = view.findViewById<DatePicker>(R.id.datePicker_expense)
            val editTextNumber = view.findViewById<EditText>(R.id.editTextNumberDecimal_expense)
            val spinner = view.findViewById<Spinner>(R.id.expense_spinner)
            val date = Date(datePicker.year, datePicker.month, datePicker.dayOfMonth)

            var amount = 0.0

            if(!editTextNumber.text.toString().isEmpty())
            {
                val amount = editTextNumber.text.toString().toDouble()
            }

            val transaction = Transaction(amount, date, (spinner.selectedItem as TransactionCategory) )

            //this needs to be not this
            (activity as MainActivity).budget.addTransaction(transaction)
        }
    }



    private fun doTransactionStuff(view: View)
    {
        val spinner = view.findViewById<Spinner>(R.id.expense_spinner)
        val adapter = ArrayAdapter<TransactionCategory>(view.context, android.R.layout.simple_spinner_item, TransactionCategory.values())
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }

    companion object
    {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ExpenseFragment().apply {

            }
    }
}