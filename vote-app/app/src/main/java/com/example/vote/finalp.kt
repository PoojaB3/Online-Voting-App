package com.example.vote

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class finalp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        var ii:Boolean=true
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finalp)

        Thread(Runnable {
            while(ii){runOnUiThread{}
                Thread.sleep(5000)
                ii=false
            }  }).start()


            var i = Intent(this, login::class.java)
            startActivity(i)

    }
}