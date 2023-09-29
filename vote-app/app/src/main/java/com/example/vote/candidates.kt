
package com.example.vote
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.vote.R
import com.example.vote.finalp
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
class candidates : AppCompatActivity() {



    private var mAuth: FirebaseAuth?=null
    private var mFirebaseDatabaseInstances: FirebaseFirestore?=null
    private var mFirebaseDatabaseInstances1: FirebaseFirestore?=null
    private var mFirebaseDatabaseInstances2: FirebaseFirestore?=null
    var radioGroup: RadioGroup? =null
    var usn=""
    var cusn=""

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_candidates)
        radioGroup= findViewById<RadioGroup>(R.id.radioGroup) as RadioGroup
        mAuth= FirebaseAuth.getInstance()
        var flag=0
        usn= intent.getStringExtra("usn").toString()
        mFirebaseDatabaseInstances= FirebaseFirestore.getInstance()
        mFirebaseDatabaseInstances1= FirebaseFirestore.getInstance()
        mFirebaseDatabaseInstances?.collection("votes")?.get()
            ?.addOnCompleteListener(OnCompleteListener<QuerySnapshot> { task ->
                if (task.isSuccessful) {

                    for (document in task.result) {
                        if(document.data["susn"]!!.equals(usn)) {
                            var txtres: TextView = findViewById(R.id.txtres) as TextView
                            txtres.text =
                                txtres.text.toString() + "Already voted to ${document.data["cusn"]}"
                            var sbBtn: Button =findViewById(R.id.button5) as Button
                            sbBtn.isEnabled=false
                            flag = 1
                        }
                        //Toast.makeText(this, document.data["name"].toString(), Toast.LENGTH_LONG).show()
                        // addRadioButton(document.data["name"] .toString())
                    }
                    if(flag==0)
                    {
                        mFirebaseDatabaseInstances1?.collection("candidate")?.get()
                            ?.addOnCompleteListener(OnCompleteListener<QuerySnapshot> { task ->
                                if (task.isSuccessful) {
                                    val list: MutableList<String> = ArrayList()
                                    for (document in task.result) {
                                        list.add(document.id)
                                    //    Toast.makeText(this, "You have successfully casted \n your valuable vote !!\n\n Thank You :)", Toast.LENGTH_LONG).show()
                                        addRadioButton(document.data["name"] .toString())

                                    }
                                    //Log.d(TAG, list.toString())
                                } else {
                                    //  Log.d(TAG, "Error getting documents: ", task.exception)
                                }
                            })

                    }
                    //Log.d(TAG, list.toString())
                }
            })


    }




    fun addRadioButton(name:String) {
        val radioButton1 = RadioButton(this)
        radioButton1.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        radioButton1.setText(name)
        radioButton1.setOnClickListener {
            cusn=name
        }
        radioGroup!!.addView(radioButton1)

    }
    fun addvote()
    {  mFirebaseDatabaseInstances2= FirebaseFirestore.getInstance()
        mFirebaseDatabaseInstances2?.collection("candidate")?.get()
            ?.addOnCompleteListener(OnCompleteListener<QuerySnapshot> { task ->
                if (task.isSuccessful) {
                  //  Toast.makeText(this, cusn, Toast.LENGTH_LONG).show()

                    for (document in task.result) {
                        if (document.data["name"]!!.equals(cusn)) {
                            var vo:Int = document.data["votes"].toString().toInt()+1
                            var usn1 = document.data["usn"].toString()
                            var u=vot(usn1,vo.toString())
                            //   var u = register(name, pwd, email, pno, branch,usn,"0")
                            val increment= mFirebaseDatabaseInstances
                           // Toast.makeText(this,vo.toString(), Toast.LENGTH_LONG).show()
                            mFirebaseDatabaseInstances2?.collection("candidate")?.document(document.id)?.update("votes",vo.toString())
                            //mFirebaseDatabaseInstances?.collection("candidate")?.add(u)
                             Toast.makeText(this, "You have successfully casted \n your valuable vote !!\n\n Thank You :)", Toast.LENGTH_LONG).show()

                            var i = Intent(this, finalp::class.java)
                            startActivity(i)
                        }
                    }
                }
            }
            );
        /*var vo = document.data["votes"].toString().toInt()+1
       var  reference = FirebaseFirestore.getInstance().getReference("candidate");
        reference.child(usn).child("votes").setValue(vo)*/

    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun last(v: View?) {
        val current = LocalDateTime.now()

        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
        val formatted = current.format(formatter)
        var u = voting(intent.getStringExtra("usn").toString(),cusn,formatted)
        mFirebaseDatabaseInstances?.collection("votes")?.add(u)
        addvote()


    }

}
class voting
{

    var susn=""
    var cusn=""
    var dt=""


    constructor(usn:String,cusn:String,dt:String)
    {
        this.susn=usn
        this.cusn=cusn
        this.dt=dt

    }
    constructor()

}
class vot
{


    var usn=""
    var votes=""


    constructor(usn1:String,dt:String)
    {

        this.usn=usn1
        this.votes=dt

    }
    constructor()

}
/* class candidate
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
} */