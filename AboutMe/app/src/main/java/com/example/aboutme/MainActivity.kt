package com.example.aboutme

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.aboutme.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }
    private val myName: MyName = MyName("Kelly George")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.myName = myName
        binding.btnDone.setOnClickListener() {
            addNickName(it)
        }
    }

    private fun addNickName(view: View) {
        binding.apply {
            myName?.nickname = eTxtName.text.toString()
            // Invalidate all binding expressions and request a new rebind to refresh
            invalidateAll()
            eTxtName.visibility = View.GONE
            btnDone.visibility = View.GONE
            txtvNickname.visibility = View.VISIBLE
        }

        // Hide the keyboard.
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}