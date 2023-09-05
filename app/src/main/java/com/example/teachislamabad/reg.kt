package com.example.teachislamabad

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class reg : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg)

        var login = findViewById<Button>(R.id.bt_login)
        login.setOnClickListener{ val intent =Intent(this,Login::class.java)
            startActivity(intent) }

        var signup = findViewById<Button>(R.id.bt_signup)
        signup.setOnClickListener{ val intent = Intent(this,SignUp::class.java)
        startActivity(intent) }

    }
}