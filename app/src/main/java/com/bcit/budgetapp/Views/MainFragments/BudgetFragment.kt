package com.bcit.budgetapp.Views.MainFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.activityViewModels
import com.bcit.budgetapp.Models.Budget
import com.bcit.budgetapp.Models.TransactionCategory
import com.bcit.budgetapp.R
import com.bcit.budgetapp.ViewModels.BudgetViewModel
import com.bcit.budgetapp.databinding.FragmentBudgetBinding
import com.bcit.budgetapp.databinding.FragmentExpenseBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BudgetFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BudgetFragment : Fragment()
{
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val budgetViewModel: BudgetViewModel by activityViewModels()
    private var _binding: FragmentBudgetBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        _binding = FragmentBudgetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //setup the Spinner
        val spinner = view.findViewById<Spinner>(R.id.spinner_budgetFragment_cat)
        val adapter = ArrayAdapter<TransactionCategory>(requireContext(), android.R.layout.simple_spinner_item, TransactionCategory.values().take(5))
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        binding.buttonBudgetFragmentUpdate.setOnClickListener { view -> updateBudgetButtonClick(view) }
        //binding.buttonExpenseAdd.setOnClickListener{it -> addExpenseButtonClick(it) }

    }

    private fun updateBudgetButtonClick(view: View){
        val budget = Budget(userID, 100.0, TransactionCategory.GROCERIES)
        budgetViewModel.addBudget(budget)
    }

    companion object
    {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BudgetFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BudgetFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}