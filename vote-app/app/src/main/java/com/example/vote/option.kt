package com.example.vote

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class option : AppCompatActivity() {
    private var mAuth: FirebaseAuth?=null
    private var mFirebaseDatabaseInstances: FirebaseFirestore?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_option)
        mAuth= FirebaseAuth.getInstance()
        mFirebaseDatabaseInstances= FirebaseFirestore.getInstance()
    }
    fun listing(v: View?)
    {
        var i= Intent(this,list::class.java)
        startActivity(i)
    }
    fun addc(v: View?)
    {
     //   Toast.makeText(applicationContext,"inside", Toast.LENGTH_LONG).show()
        var name=(findViewById<View>(R.id.nm) as EditText).text.toString()
        var usn=(findViewById<View>(R.id.us) as EditText).text.toString()
        var sec=(findViewById<View>(R.id.sc) as EditText).text.toString()
        var sem=(findViewById<View>(R.id.se) as EditText).text.toString()
        var branch=(findViewById<View>(R.id.br) as EditText).text.toString()
        if (usn.isNullOrEmpty() || name.isNullOrEmpty() || sec.isNullOrEmpty() || sem.isNullOrEmpty() || branch.isNullOrEmpty() ) {
            Toast.makeText(this,"Please fill all mandatory fields",Toast.LENGTH_LONG).show()}
        else {
            var u = candidate(name, usn, sec, sem, branch)
            /* mFirebaseDatabaseInstances?.collection("candidate")?.document(usn!!)?.set(u)
        Toast.makeText(this, "Registered successfully", Toast.LENGTH_LONG).show()
*/
            val docRef = mFirebaseDatabaseInstances?.collection("candidate")?.document(usn!!)
            docRef?.get()?.addOnSuccessListener { documentSnapshot ->
                val user = documentSnapshot.toObject(candidate::class.java) as candidate?
                if (user != null) //user already exists
                {
                    Toast.makeText(this, "User Already exists", Toast.LENGTH_LONG).show()


                } else {
                    var u = candidate(name, usn, sem, sec, branch)
                    mFirebaseDatabaseInstances?.collection("candidate")?.document(usn!!)?.set(u)
                    Toast.makeText(this, "Registered successfully", Toast.LENGTH_LONG).show()
                }


            }
            // var i= Intent(this,list::class.java)
            // startActivity(i)
        }

    }

}
class candidate
{
    var branch=""
    var usn=""
    var name=""
    var sec=""
    var sem=""
    var votes="0"
    constructor(name:String,usn:String,sem:String,sec:String,branch:String)
    {
        this.name=name
        this.usn=usn
        this.sem=sem
        this.sec=sec
        this.branch=branch
        this.votes="0"
    }
    constructor()
}
