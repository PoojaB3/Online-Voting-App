package com.example.vote

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class list : AppCompatActivity() {
    private var mTableLayout: TableLayout? = null

    private var mAuth: FirebaseAuth?=null
    private var mFirebaseDatabaseInstances: FirebaseFirestore?=null
    var i=0

    @SuppressLint("ResourceType", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        var flag=0
        mTableLayout = findViewById(R.id.table) as TableLayout
        mTableLayout!!.setStretchAllColumns(true);
        mTableLayout!!.removeAllViews();
        mTableLayout!!.setBackgroundDrawable(getResources().getDrawable(R.xml.table));
        mAuth= FirebaseAuth.getInstance()
        mFirebaseDatabaseInstances= FirebaseFirestore.getInstance()
        mFirebaseDatabaseInstances?.collection("candidate")?.get()
            ?.addOnCompleteListener(OnCompleteListener<QuerySnapshot> { task ->
                if (task.isSuccessful) {

                    for (document in task.result) {
                        /* list.add(document.id)
                         Toast.makeText(this, document.data["name"].toString(), Toast.LENGTH_LONG).show()
                         addRadioButton(dovacument.data["name"] .toString())*/
                        var tv: TextView =TextView(this);
                        tv.setLayoutParams(
                            TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,

                                TableRow.LayoutParams.WRAP_CONTENT));
                        tv.setGravity(Gravity.LEFT);
                        tv.setTextColor(Color.WHITE)
                        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP,22F);
                        tv.setPadding(5, 15, 0, 15);
                        tv.text=document.data["name"].toString()

                        var tv1:TextView =TextView(this);
                        tv1.setLayoutParams(
                            TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,

                                TableRow.LayoutParams.WRAP_CONTENT));
                        tv1.setGravity(Gravity.LEFT);
                        tv1.setTextColor(Color.WHITE)
                        tv1.setTextSize(TypedValue.COMPLEX_UNIT_SP,22F);
                        tv1.setPadding(5, 15, 0, 15);
                        tv1.text=document.data["usn"].toString()

                        var tv2:TextView =TextView(this);
                        tv2.setLayoutParams(
                            TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,

                                TableRow.LayoutParams.WRAP_CONTENT));
                        tv2.setGravity(Gravity.LEFT);
                        tv2.setTextColor(Color.WHITE)
                        tv2.setTextSize(TypedValue.COMPLEX_UNIT_SP,22F);
                        tv2.setPadding(5, 15, 0, 15);
                        tv2.text=document.data["sem"].toString()

                        var tv3:TextView =TextView(this);
                        tv3.setLayoutParams(
                            TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,

                                TableRow.LayoutParams.WRAP_CONTENT));
                        tv3.setGravity(Gravity.LEFT);
                        tv3.setTextColor(Color.WHITE)
                        tv3.setTextSize(TypedValue.COMPLEX_UNIT_SP,22F);
                        tv3.setPadding(5, 15, 0, 15);
                        tv3.text=document.data["sec"].toString()
                        var layCustomer: LinearLayout = LinearLayout(this);
                        /* layCustomer.addView(tv)
                         layCustomer.addView(tv1)
                         layCustomer.addView(tv2)
                         layCustomer.addView(tv3)*/
                        var tr:TableRow=TableRow(this)
                        tr.id=(i+1)
                        var trParams:TableLayout.LayoutParams = TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                            TableLayout.LayoutParams.WRAP_CONTENT);
                        trParams.setMargins(10, 5, 10, 0);
                        tr.setPadding(0,0,0,0);
                        tr.setLayoutParams(trParams);

                        tr.addView(tv);
                        tr.addView(tv1);
                        tr.addView(tv2);
                        tr.addView(tv3);
                        // tr.addView(layCustomer);
                        mTableLayout!!.addView(tr,trParams)



                    }

                    //Log.d(TAG, list.toString())
                } else {
                    //  Log.d(TAG, "Error getting documents: ", task.exception)
                }
            })




    }
}