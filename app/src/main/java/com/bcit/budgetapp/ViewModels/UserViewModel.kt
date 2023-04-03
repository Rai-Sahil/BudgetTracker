package com.bcit.budgetapp.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bcit.budgetapp.Models.User

class UserViewModel : ViewModel() {

    val username = MutableLiveData<String>()

    fun loggedInUser(ID: String){
        username.value = ID
    }

}