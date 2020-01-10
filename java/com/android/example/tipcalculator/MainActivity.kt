package com.android.example.tipcalculator

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView

const val HUNDRED = 100.00
const val TIP_INC = 1
const val MIN_TIP = 0
const val MAX_TIP = 99

class MainActivity : AppCompatActivity(), View.OnClickListener{

    private lateinit var billEntry: EditText
    private lateinit var decreaseButton: ImageButton
    private lateinit var  increaseButton: ImageButton
    private lateinit var tipNumber: TextView
    private lateinit var tipResult: TextView
    private lateinit var totalAmount: TextView

    private var initTip = 10


     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         setContentView(R.layout.activity_main)
        firstView()
     }

         private fun firstView(){
             billEntry = findViewById(R.id.billEntry)
             decreaseButton = findViewById(R.id.decreaseButton)
             increaseButton = findViewById(R.id.increaseButton)
             tipNumber = findViewById(R.id.tipNumber)
            tipResult = findViewById(R.id.tipResult)
             totalAmount = findViewById(R.id.totalAmount)

             decreaseButton.setOnClickListener(this)
             increaseButton.setOnClickListener(this)
         }


    private fun calc(){
        val totalVal = billEntry.text.toString().toDouble()

        val totalResult = ((HUNDRED + initTip)/ HUNDRED) * totalVal

        val tipFinal = totalResult - totalVal

        totalAmount.text = String.format("$%.2f", totalResult)
        tipResult.text = String.format("$%.2f", tipFinal)

    }



    override fun onClick(view: View?) {

    when(view?.id) {
        R.id.increaseButton -> increased()
        R.id.decreaseButton -> decreased()
    }

        tipResult.visibility = View.VISIBLE
        totalAmount.visibility = View.VISIBLE

        val inputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (view != null) {
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    private fun increased(){
    if (initTip != MAX_TIP){
        initTip += TIP_INC
        tipNumber.text = String.format("%d%%", initTip)
    }
        calc()
    }

    private fun decreased(){
        if (initTip != MIN_TIP){
            initTip -= TIP_INC
            tipNumber.text = String.format("%d%%", initTip)
        }
        calc()
    }


    }



