package com.example.calcy

import android.annotation.SuppressLint
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

    @SuppressLint("SuspiciousIndentation")
    fun numberAction(view: View){

        if(view is Button){
            if(view.text == "."){
                if(canAddDecimal)
                    workingsTV.append(view.text)

                canAddDecimal = false
            }
            else
            workingsTV.append(view.text)
            canAddOperation = true
        }
    }
    fun oprationAction(view: View){
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
    fun equalsAction(view: View) {
        resultsTV.text = calculateResults()
    }

    private fun calculateResults(): String
    {
        val digitsOperators = digitsoperator()
        if (digitsOperators.isEmpty()) return ""

        val timesDivision = timesDivisionCalculate(digitsOperators)
        if (timesDivision.isEmpty()) return ""

        val result = addSubtractCalculate(timesDivision)

        return Float.toString()
    }

    private fun addSubtractCalculate(passedList: MutableList<Any>): Float
    {
        var result
    }

    private fun timesDivisionCalculate(passedList: MutableList<Any>): MutableList<Any> {

        var list = passedList
        while (list.contains('x') || list.contains('/') ){
            list = calcTimesDiv(list)
        }
        return list
    }

    private fun calcTimesDiv(passedList: MutableList<Any>): MutableList<Any>
    {

        val newList = mutableListOf<Any>()
        var restartIndex = passedList.size

        for(i in passedList.indices)
        {

            if(passedList[i] is Char && i !== passedList.LastIndex && i < restartIndex )
            {
                val operator = passedList[i]
                val prevDigit = passedList[i] as Float
                val nextDigit = passedList[i] as Float
                when(operator)
                {
                    'x' ->
                    {
                        newList.add(prevDigit * nextDigit)
                        restartIndex = i + 1
                    }
                    '/' ->
                    {
                        newList.add(prevDigit / nextDigit)
                        restartIndex = i + 1
                    }
                    else ->
                    {
                        newList.add(prevDigit)
                        newList.add(operator)
                    }
                }
            }

            if(i > restartIndex)
                newList.add(passedList[i])
        }
        return newList

    }


    private fun digitsoperator(): MutableList<Any>
    {
        val list = mutableListOf<Any>()
        var currentDigit= ""
        for(charactor in workingsTV.text){
            if(charactor.isDigit() || charactor == '.')
                currentDigit += charactor
            else{
                list.add(currentDigit.toFloat())
                currentDigit=""
                list.add(charactor)
            }
        }
        if(currentDigit != "")
            list.add(currentDigit.toFloat())

        return list
        }
    }
}
