package com.bcit.budgetapp.Views.AuthenticationFragments

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import com.bcit.budgetapp.R
import com.bcit.budgetapp.ViewModels.UserViewModel
import com.bcit.budgetapp.Views.AuthenticationActivity
import com.bcit.budgetapp.Views.FragmentNavigation
import com.bcit.budgetapp.Views.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.MainScope


class LoginFragment : Fragment()
{

    private lateinit var username: EditText
    private lateinit var password: EditText

    private lateinit var firebaseAuth: FirebaseAuth

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
        var view = inflater.inflate(R.layout.fragment_login, container, false)

        username = view.findViewById(R.id.edittext_loginFragment_username)
        password = view.findViewById(R.id.edittext_loginFragment_password)

        firebaseAuth = Firebase.auth

        view.findViewById<Button>(R.id.button_loginFragment_login).setOnClickListener {
            validateEmptyForm()
        }

        view.findViewById<Button>(R.id.button_loginFragment_register)
            .setOnClickListener{
                var navRegister = activity as FragmentNavigation
                navRegister.navigateFrag(SignUpFragment(), false)
            }


        return view
    }

    private fun validateEmptyForm(){
        val icon = AppCompatResources.getDrawable(requireContext(),
            R.drawable.ic_baseline_warning_24)

        icon?.setBounds(0, 0, icon.intrinsicWidth, icon.intrinsicHeight)
        when{
            TextUtils.isEmpty(username.text.toString().trim()) -> {
                username.setError("Please Enter Username", icon)
            }

            TextUtils.isEmpty(password.text.toString().trim()) -> {
                username.setError("Please Enter Password", icon)
            }

            username.text.toString().isNotEmpty() &&
                    password.text.toString().isNotEmpty()  -> {

                if (username.text.toString()
                        .matches(Regex("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"))){

                    firebaseSignIn()

                } else {
                    username.setError("Please Enter a Valid Email ID", icon)
                }
            }
        }
    }

    private fun firebaseSignIn(){
        firebaseAuth.signInWithEmailAndPassword(username.text.toString(),
            password.text.toString()).addOnCompleteListener { task ->

            if (task.isSuccessful){
                val userViewModel = ViewModelProvider(AuthenticationActivity())[UserViewModel::class.java]

                userViewModel.loggedInUser(username.text.toString())

//                val userViewModel: UserViewModel by activityViewModels()
//                userViewModel.loggedInUser(username.text.toString())

                val intent = Intent(this.context, MainActivity::class.java)
                startActivity(intent)
            }
            else {
                Toast.makeText(context, task.exception?.message, Toast.LENGTH_SHORT).show()
            }
        }
    }


}