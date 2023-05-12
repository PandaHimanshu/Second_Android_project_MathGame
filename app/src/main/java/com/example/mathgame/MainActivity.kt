package com.example.mathgame

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    lateinit var addition:Button
    lateinit var substraction:Button
    lateinit var multiplication:Button
    lateinit var division:Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addition=findViewById(R.id.buttonadd)
        substraction=findViewById(R.id.buttonsub)
        multiplication=findViewById(R.id.multiplication)
        division=findViewById(R.id.buttondiv)

        addition.setOnClickListener {
            val intent=Intent(this@MainActivity,GameActivity::class.java)
            startActivity(intent)
        }
        substraction.setOnClickListener {
            val intent=Intent(this@MainActivity,subtractionActivity::class.java)
            startActivity(intent)
        }
        multiplication.setOnClickListener {
            val intent=Intent(this@MainActivity,MultiplicationActivity::class.java)
            startActivity(intent)
        }
        division.setOnClickListener {
            val intent=Intent(this@MainActivity,DivisionActivity::class.java)
            startActivity(intent)
        }
    }
}