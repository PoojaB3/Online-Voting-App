package com.example.vote

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
    fun signup(v: View?)
    {
        var i= Intent(this,details::class.java)


        startActivity(i)

    }
    fun login(v: View?)
    {

        var i= Intent(this,login::class.java)

        startActivity(i)

    }}