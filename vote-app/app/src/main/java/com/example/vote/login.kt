package com.example.vote

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class login : AppCompatActivity() {
    private var mAuth: FirebaseAuth?=null
    private var mFirebaseDatabaseInstances: FirebaseFirestore?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mAuth=FirebaseAuth.getInstance()
        mFirebaseDatabaseInstances= FirebaseFirestore.getInstance()
    }
    fun adm(v: View?)
    {
        var usn1=(findViewById<View>(R.id.usn1) as EditText).text.toString()
        var pwd1=(findViewById<View>(R.id.pwd1) as EditText).text.toString()
        //Toast.makeText(applicationContext,usn1,Toast.LENGTH_LONG).show()
        if(usn1=="admin" && pwd1=="jnnce")
        {
            var i= Intent(applicationContext,admin::class.java)
            startActivity(i)
        }
        else
        {
            var flag=0
            try {
                mFirebaseDatabaseInstances?.collection("register")!!.document(usn1)
                    .get().addOnSuccessListener { result ->

                        val uu = result.toObject(register::class.java) as register?
                        if(uu==null)
                        {
                            Toast.makeText(this, "User does not exist", Toast.LENGTH_LONG).show()
                        }
                        else {
                            if (uu.password.equals(pwd1)) {
                                var i=Intent(applicationContext,candidates::class.java)
                                i.putExtra("usn",uu.usn)
                                //Toast.makeText(this, "User", Toast.LENGTH_LONG).show()
                                startActivity(i)
                            } else {
                                Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_LONG).show()
                            }
                        }
                    }

                    .addOnFailureListener { exception ->
                        Toast.makeText(this, "User does not exist", Toast.LENGTH_LONG).show()
                    }
            }
            catch(e:Exception)
            {
                Toast.makeText(this, "User does not exist", Toast.LENGTH_LONG).show()
            }


        }


    }

}