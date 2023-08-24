package com.example.calcy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.resultsTV
import kotlinx.android.synthetic.main.activity_main.workingsTV

class MainActivity : AppCompatActivity() {
    private var canAddOperation = false
    private var canAddDecimal = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun numberAction(view: View){
        if(view is Button){
            if(view.text)
            workingsTV.append(view.text)
            canAddOperation = true
        }
    }
    fun oprationaction(view: View){
        if(view is Button && canAddOperation){
            workingsTV.append(view.text)
            canAddOperation = false
            canAddDecimal = true
        }

    }
    fun allClearAction(view: View) {
        workingsTV.text = ""
        resultsTV.text = ""
    }
    fun backSpaceAction(view: View) {
        val length = workingsTV.length()
        if (length>0){
            workingsTV.text = workingsTV.text.subSequence(0,length-1)
        }
    }
    fun equalsAction(view: View) {}
}
