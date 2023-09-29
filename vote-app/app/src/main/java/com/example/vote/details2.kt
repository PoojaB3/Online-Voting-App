package com.example.vote

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class details2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details2)
    }
    fun det(v: View?)
    {
        var i= Intent(this,details::class.java)
        startActivity(i)
    }
}