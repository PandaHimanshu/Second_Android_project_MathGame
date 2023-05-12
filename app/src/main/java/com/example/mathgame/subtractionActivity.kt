package com.example.mathgame

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.util.*
import kotlin.random.Random

class subtractionActivity : AppCompatActivity() {

    lateinit var textscore:TextView
    lateinit var textlife:TextView
    lateinit var texttime:TextView

    lateinit var textquestion:TextView
    lateinit var edittextanswer:EditText

    lateinit var buttonok:Button

    var correctanswer=0
    var userscore=0
    var userlife=3

    lateinit var timer:CountDownTimer
    private val starttimerinmillisec:Long=30000
    var timeleftinmillisec:Long=starttimerinmillisec

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        supportActionBar!!.title="Subtraction"

        textscore=findViewById(R.id.textViewscore)
        textlife=findViewById(R.id.textViewlife)
        texttime=findViewById(R.id.textViewtime)
        textquestion=findViewById(R.id.textViewquestion)
        edittextanswer=findViewById(R.id.editTextanswer)
        buttonok=findViewById(R.id.buttonok)

        gamecontinue()

        buttonok.setOnClickListener {
            val input=edittextanswer.text.toString()

            if(input==""){
                Toast.makeText(applicationContext,"Please write an answer or click the next button",
                    Toast.LENGTH_LONG).show()
            }
            else{

                pauseTimer()
                val useranswer=input.toInt()
                if(useranswer==correctanswer){
                    userscore=userscore+10
                    textquestion.text="Congratulation,your answer is correct"
                    textscore.text=userscore.toString()
                }
                else{
                    userlife--
                    textquestion.text="sorry,your answer is wrong!"
                    textlife.text=userlife.toString()
                }
            }
            pauseTimer()
            resetTimer()

            edittextanswer.setText("")

            if(userlife==0){
                Toast.makeText(applicationContext,"Game Over!",Toast.LENGTH_LONG).show()
                val intent=Intent(this@subtractionActivity,ResultActivity::class.java)
                intent.putExtra("Score",userscore)
                startActivity(intent)
                finish()
            }
            else{
                gamecontinue()
            }
        }
    }
    fun gamecontinue(){
        val number1= Random.nextInt(0,100)
        val number2=Random.nextInt(0,100)
        textquestion.text="$number1 - $number2"

        correctanswer=number1-number2

        starttimer()
    }

    fun starttimer(){
        timer=object :CountDownTimer(timeleftinmillisec,1000){
            override fun onTick(millisecuntillfinished: Long) {
                timeleftinmillisec=millisecuntillfinished
                updateText()
            }

            override fun onFinish() {
                pauseTimer()
                resetTimer()
                updateText()

                userlife--
                textlife.text=userlife.toString()
                textquestion.text="sorry,Time is up!"
            }

        }.start()
    }
    fun updateText(){
        val remainingtime:Int=(timeleftinmillisec/1000).toInt()
        texttime.text=String.format(Locale.getDefault(),"%02d",remainingtime)
    }
    fun pauseTimer(){
        timer.cancel()
    }
    fun  resetTimer(){
        timeleftinmillisec=starttimerinmillisec
        updateText()
    }
}