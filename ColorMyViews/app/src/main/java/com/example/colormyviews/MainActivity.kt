package com.example.colormyviews

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setListeners()
    }

    private fun setListeners(){
        val clickableViews: List<View> = listOf(txtvBoxOne,txtvBoxTwo,txtvBoxThree,txtvBoxFour,txtvBoxFive,consLayout)
        for (item in clickableViews) {
            item.setOnClickListener { makeColored(it) }
        }
    }

    private fun makeColored(view: View) {
        when (view) {
            // Boxes using Color class colors for background
            txtvBoxOne -> view.setBackgroundColor(Color.DKGRAY)
            txtvBoxTwo -> view.setBackgroundColor(Color.GRAY)

            // Boxes using Android color resources for background
            txtvBoxThree -> view.setBackgroundResource(android.R.color.holo_green_light)
            txtvBoxFour -> view.setBackgroundResource(android.R.color.holo_green_dark)
            txtvBoxFive -> view.setBackgroundResource(android.R.color.holo_green_light)

            else -> view.setBackgroundColor(Color.LTGRAY)
        }
    }

}