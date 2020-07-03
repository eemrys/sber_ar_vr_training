/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.dessertpusher

import android.content.ActivityNotFoundException
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.lifecycle.LifecycleObserver
import timber.log.Timber
import kotlinx.android.synthetic.main.activity_main.*

const val KEY_REVENUE = "key_revenue"
const val KEY_DESSERTS = "key_desserts"
const val KEY_TIMER = "key_timer"

class MainActivity : AppCompatActivity(), LifecycleObserver {

    private var revenue = 0
    private var dessertsSold = 0
    private val allDesserts = getAllDesserts()
    private var currentDessert = allDesserts[0]
    private val dessertTimer = DessertTimer(this.lifecycle)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDessert.apply {
            setOnClickListener { onDessertClicked() }
            setImageResource(currentDessert.imageId)
        }

        handleSavedBundle(savedInstanceState)

        setTextViews()

        Timber.i("onCreate called")
    }

    override fun onRestart() {
        super.onRestart()
        Timber.i("onRestart called")
    }

    override fun onStart() {
        super.onStart()
        Timber.i("onStart called")
    }

    override fun onResume() {
        super.onResume()
        Timber.i("onResume called")
    }

    override fun onPause() {
        super.onPause()
        Timber.i("onPause called")
    }

    override fun onStop() {
        super.onStop()
        Timber.i("onStop called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.i("onDestroy called")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_REVENUE, revenue)
        outState.putInt(KEY_DESSERTS, dessertsSold)
        outState.putInt(KEY_TIMER, dessertTimer.secondsCount)

        Timber.i("onSaveInstanceState called")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        Timber.i("onRestoreInstanceState called")
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.btnShareMenu -> onShare()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun onDessertClicked() {

        revenue += currentDessert.price
        dessertsSold++

        txtvRevenue.text = getString(R.string.dollars, revenue)
        txtvAmountSold.text = dessertsSold.toString()

        showCurrentDessert()
    }

    private fun showCurrentDessert() {
        var newDessert = allDesserts[0]

        allDesserts.forEach {
            if (dessertsSold >= it.startProductionAmount) {
                newDessert = it
            }
        }

        if (newDessert != currentDessert) {
            currentDessert = newDessert
            btnDessert.setImageResource(newDessert.imageId)
        }
    }

    private fun onShare() {
        val shareIntent = ShareCompat.IntentBuilder.from(this)
                .setText(getString(R.string.share_text, dessertsSold, revenue))
                .setType("text/plain")
                .intent
        try {
            startActivity(shareIntent)
        } catch (ex: ActivityNotFoundException) {
            Toast.makeText(this, getString(R.string.sharing_not_available),
                    Toast.LENGTH_LONG).show()
        }
    }

    private fun getAllDesserts(): List<Dessert> {
        return listOf(
                Dessert(R.drawable.cupcake, 5, 0),
                Dessert(R.drawable.donut, 10, 5),
                Dessert(R.drawable.eclair, 15, 20),
                Dessert(R.drawable.froyo, 30, 50),
                Dessert(R.drawable.gingerbread, 50, 100),
                Dessert(R.drawable.honeycomb, 100, 200),
                Dessert(R.drawable.icecreamsandwich, 500, 500),
                Dessert(R.drawable.jellybean, 1000, 1000),
                Dessert(R.drawable.kitkat, 2000, 2000),
                Dessert(R.drawable.lollipop, 3000, 4000),
                Dessert(R.drawable.marshmallow, 4000, 8000),
                Dessert(R.drawable.nougat, 5000, 16000),
                Dessert(R.drawable.oreo, 6000, 20000)
        )
    }

    private fun handleSavedBundle(savedInstanceState: Bundle?) {
        savedInstanceState?.apply {
            dessertTimer.secondsCount = getInt(KEY_TIMER)
            revenue = getInt(KEY_REVENUE)
            dessertsSold = getInt(KEY_DESSERTS)
        }
    }

    private fun setTextViews() {
        txtvRevenue.text = getString(R.string.dollars, revenue)
        txtvAmountSold.text = dessertsSold.toString()
    }

}
