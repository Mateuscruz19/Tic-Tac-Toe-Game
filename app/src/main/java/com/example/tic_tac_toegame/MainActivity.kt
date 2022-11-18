package com.example.tic_tac_toegame

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    var isPlayer1:Boolean = true;
    var gameEnd:Boolean = false;

    private lateinit var topLeft: ImageView
    private lateinit var top: ImageView
    private lateinit var topRight: ImageView

    private lateinit var centerLeft: ImageView
    private lateinit var center: ImageView
    private lateinit var centerRight: ImageView

    private lateinit var bottomLeft: ImageView
    private lateinit var bottom: ImageView
    private lateinit var bottomRight: ImageView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        topLeft = findViewById(R.id.topLeft)
        top = findViewById(R.id.top)
        topRight = findViewById(R.id.topRight)

        centerLeft = findViewById(R.id.centerLeft)
        center = findViewById(R.id.center)
        centerRight = findViewById(R.id.centerRight)

        bottomLeft = findViewById(R.id.bottomLeft)
        bottom = findViewById(R.id.bottom)
        bottomRight = findViewById(R.id.bottomRight)

        val reset:Button = findViewById(R.id.resetButton)
        reset.setOnClickListener {
            resetBox(topLeft)
            resetBox(top)
            resetBox(topRight)

            resetBox(centerLeft)
            resetBox(center)
            resetBox(centerRight)

            resetBox(bottomLeft)
            resetBox(bottom)
            resetBox(bottomRight)
        }

        singleBox(topLeft)
        singleBox(top)
        singleBox(topRight)

        singleBox(centerLeft)
        singleBox(center)
        singleBox(centerRight)

        singleBox(bottomLeft)
        singleBox(bottom)
        singleBox(bottomRight)
    }

    private fun resetBox(box: ImageView){
        box.setImageDrawable(null)
        box.tag = null
        isPlayer1 = true
        gameEnd = false
    }


    private fun singleBox(box: ImageView) {
        box.setOnClickListener{
            if(box.tag == null && !gameEnd) {
                if (isPlayer1) {
                    box.setImageResource(R.drawable.ic_baseline_close_24)
                    isPlayer1 = false
                    box.tag = 1
                } else {
                    box.tag = 2
                    isPlayer1 = true
                    box.setImageResource(R.drawable.ic_baseline_check_circle_24)
                }
                if(playerWin(1)){
                    Toast.makeText(this@MainActivity, "Player 1 Wins", Toast.LENGTH_SHORT).show()
                    gameEnd = true
                }else if (playerWin(2)){
                    Toast.makeText(this@MainActivity, "Player 2 Wins", Toast.LENGTH_SHORT).show()
                    gameEnd = true
                }
            }
        }
    }

    private fun playerWin(value:Int): Boolean {
        if((top.tag == value && center.tag == value && bottom.tag == value) ||
            (topLeft.tag == value && centerLeft.tag == value && bottomLeft.tag == value) ||
            (topRight.tag == value && centerRight.tag == value && bottomRight.tag == value) ||

            (topRight.tag == value && top.tag == value && topLeft.tag == value) ||
            (centerRight.tag == value && center.tag == value && centerLeft.tag == value) ||
            (bottomRight.tag == value && bottom.tag == value && bottomLeft.tag == value) ||

            (topLeft.tag == value && center.tag == value && bottomRight.tag == value) ||
            (topRight.tag == value && center.tag == value && bottomLeft.tag == value)
        ){
            return true
        }
        return false
    }


}