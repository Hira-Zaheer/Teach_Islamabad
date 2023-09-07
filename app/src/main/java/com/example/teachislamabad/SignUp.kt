package com.example.teachislamabad

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.teachislamabad.databinding.ActivitySignUpBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class SignUp : AppCompatActivity() {


    private lateinit var emailEditText: TextInputEditText
    private lateinit var passwordEditText: TextInputEditText
    private lateinit var confirmPasswordEditText: TextInputEditText
    private lateinit var emailTextInputLayout: TextInputLayout
    private lateinit var passwordTextInputLayout: TextInputLayout
    private lateinit var confirmPasswordTextInputLayout: TextInputLayout
    private lateinit var submitButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        emailEditText = findViewById(R.id.et_emailId)
        passwordEditText = findViewById(R.id.et_cpassword)
        confirmPasswordEditText = findViewById(R.id.et_password)
        emailTextInputLayout = findViewById(R.id.et_email)
        passwordTextInputLayout = findViewById(R.id.et_cpass)
        confirmPasswordTextInputLayout = findViewById(R.id.et_pass)
        submitButton = findViewById(R.id.bt_register)


        // Set up text change listeners for real-time validation
        passwordEditText.addTextChangedListener(passwordTextWatcher)
        confirmPasswordEditText.addTextChangedListener(confirmPasswordTextWatcher)
        emailEditText.addTextChangedListener(emailTextWatcher)


        // Set up focus change listener for email field
        emailFocusListener()

        // Set up focus change listener for confirm password field
        confirmFocusListener()

        submitButton.setOnClickListener(View.OnClickListener {
            // Validate email, passwords when the submit button is clicked
            if (validateEmail() &&  validatePasswords() ) {
                // Proceed with your task here if both email and passwords are valid
                // For example, you can show a success message or perform a login action
            }
        })

    }

    //Email Validation

    private val emailTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable?) {
            validateEmail()
        }
    }

   // Password and confirm Password validation
    private val passwordTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable?) {
            validatePasswords()
        }
    }

    private val confirmPasswordTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable?) {
            validatePasswords()
        }
    }


    //emailFocus function
    private fun emailFocusListener() {
        emailEditText.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                validateEmail()
            }
        }
    }
    private fun validateEmail(): Boolean {
        val email: String = emailEditText.text.toString().trim()
        return if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailTextInputLayout.error = "Invalid email address"
            emailTextInputLayout.helperText = null
            false
        } else {
            emailTextInputLayout.error = null
            emailTextInputLayout.helperText = "Email is valid"
            true
        }
    }

    //confirm focus function
    private fun confirmFocusListener() {
        confirmPasswordEditText.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                validatePasswords()
            }
        }
    }
    private fun validatePasswords() :Boolean {
        val password: String = passwordEditText.text.toString()
        val confirmPassword: String = confirmPasswordEditText.text.toString()

        if (password.isEmpty()) {
            passwordTextInputLayout.error = "Password is required"
            passwordTextInputLayout.helperText = null
            return false

        }
        else if (password.length < 8) {
            passwordTextInputLayout.error = "Password must be at least 8 characters"
            passwordTextInputLayout.helperText = null
            return false

        }
        else if(!password.matches(".*[A-Z].*".toRegex()))
        {
            passwordTextInputLayout.error = "Must Contain 1 Upper_case"
            passwordTextInputLayout.helperText = null
            return false
        }
        else  if (!password.matches(".*[a-z].*".toRegex())) {
            passwordTextInputLayout.error ="Must Contain 1 Lower_case"
            passwordTextInputLayout.helperText = null
            return false
        }
        else if (!password.matches(".*[@#\$%^&+=].*".toRegex())) {
            passwordTextInputLayout.error = "Must Contain 1 Special Character (@#\$%^&+=)"
            passwordTextInputLayout.helperText = null
            return false
        }
        else {
            passwordTextInputLayout.error = null
            passwordTextInputLayout.helperText = "Password is valid"
        }

        if (confirmPassword.isEmpty()) {
            confirmPasswordTextInputLayout.error = "Confirm Password is required"
            confirmPasswordTextInputLayout.helperText = null
            return false
        }
        else if (password != confirmPassword) {
            confirmPasswordTextInputLayout.error = "Passwords do not match"
            confirmPasswordTextInputLayout.helperText = null
            return false
        }
        else {
            confirmPasswordTextInputLayout.error = null
            confirmPasswordTextInputLayout.helperText = "Passwords match"
        }
        return true
    }
}