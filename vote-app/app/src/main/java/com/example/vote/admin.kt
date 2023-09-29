package com.example.vote

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class admin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)
    }
    fun add(v: View?)
    {
        var i= Intent(this,option::class.java)
        startActivity(i)
    }
    fun listing(v: View?)
    {
        var i= Intent(this,list::class.java)
        startActivity(i)
    }
    fun resu(v: View?)
    {
        var i= Intent(this,result::class.java)
        startActivity(i)
    }
    fun analyse(v: View?)
    {
        var i= Intent(this,analysis::class.java)
        startActivity(i)
    }

}