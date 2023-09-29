package com.example.vote

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class details : AppCompatActivity() {
    private var mAuth: FirebaseAuth?=null
    private var mFirebaseDatabaseInstances: FirebaseFirestore?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        mAuth=FirebaseAuth.getInstance()
        mFirebaseDatabaseInstances= FirebaseFirestore.getInstance()
    }


    fun register1(v: View?) {
        var b: Boolean = true
        //Toast.makeText(applicationContext,"inside",Toast.LENGTH_LONG).show()
        var name = (findViewById<View>(R.id.name) as EditText).text.toString()
        var usn = (findViewById<View>(R.id.usn) as EditText).text.toString()
        var pno = (findViewById<View>(R.id.pno) as EditText).text.toString()
        var pwd = (findViewById<View>(R.id.pwd) as EditText).text.toString()
        var email = (findViewById<View>(R.id.email) as EditText).text.toString()
        var branch = (findViewById<View>(R.id.branch) as EditText).text.toString()
        var voted = 0
        if (name.isNullOrEmpty() || usn.isNullOrEmpty() || pno.isNullOrEmpty() || pwd.isNullOrEmpty() || email.isNullOrEmpty() || branch.isNullOrEmpty()) {
            Toast.makeText(this, "Please fill all mandatory fields", Toast.LENGTH_LONG).show()
        } else {
            if (email.endsWith("@gmail.com") == false) {

                Toast.makeText(this, "Invalid email", Toast.LENGTH_LONG).show()
            } else {
                var u = register(name, pwd, email, pno, branch, usn, "0")
                /*mFirebaseDatabaseInstances?.collection("register")?.document(usn!!)?.set(u)
        Toast.makeText(this, "Registered successfully", Toast.LENGTH_LONG).show()

            var i= Intent(this,login::class.java)
            startActivity(i)
        return*/
                val docRef = mFirebaseDatabaseInstances?.collection("register")?.document(usn!!)

                docRef?.get()?.addOnSuccessListener { documentSnapshot ->
                    val user = documentSnapshot.toObject(register::class.java) as register?
                    if (user != null) //user already exists
                    {
                        Toast.makeText(this, "User Already exists", Toast.LENGTH_LONG).show()
                    } else {
                        var u = register(name, pwd, email, pno, branch, usn, "0")
                        mFirebaseDatabaseInstances?.collection("register")?.document(usn!!)?.set(u)
                        Toast.makeText(this, "Registered successfully", Toast.LENGTH_LONG).show()
                    }


                    var i = Intent(this, login::class.java)
                    startActivity(i)
                }
            }
        }
    }

}
class register
{
    var branch=""
    var usn=""
    var email=""
    var phone=""
    var password=""
    var name=""
    var voted=""

    constructor(name:String,password:String,email:String,pno:String,branch:String,usn:String,voted:String)
    {
        this.name=name
        this.password=password
        this.email=email
        this.phone=pno
        this.branch=branch
        this.voted=voted
        this.usn=usn

    }
    constructor()
}