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

package com.example.android.guesstheword.screens.game

import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.View
import androidx.core.content.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.example.android.guesstheword.R
import kotlinx.android.synthetic.main.fragment_game.*

class GameFragment : Fragment(R.layout.fragment_game) {

    private val viewModel by lazy {
        ViewModelProvider(this).get(GameViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClick()
        setObserver()
    }

    private fun setOnClick() {
        btnCorrect.setOnClickListener {
            viewModel.onCorrect()
        }
        btnSkip.setOnClickListener {
            viewModel.onSkip()
        }
    }

    private fun setObserver() {
        viewModel.apply {
            score.observe(viewLifecycleOwner, Observer { updateScoreText(it) })
            word.observe(viewLifecycleOwner, Observer { updateWordText(it) })
            currentTimeString.observe(viewLifecycleOwner, Observer { updateTimerText(it) })
            eventGameFinished.observe(viewLifecycleOwner, Observer {
                if (it) {
                    gameFinished()
                    viewModel.onGameFinishComplete()
                }
            })
            eventBuzz.observe(viewLifecycleOwner, Observer { buzzType ->
                if (buzzType != BuzzType.NO_BUZZ) {
                    buzz(buzzType.pattern)
                    viewModel.onBuzzComplete()
                }
            })
        }
    }

    private fun updateWordText(word: String) {
        txtvCurrentWord.text = getString(R.string.quote_format, word)
    }

    private fun updateScoreText(score: Int) {
        txtvScore.text = getString(R.string.score_format, score)
    }

    private fun updateTimerText(time: String) {
        txtvTimer.text = time
    }

    private fun gameFinished() {
        val action = GameFragmentDirections.actionGameToScore(viewModel.score.value ?: 0)
        findNavController(this).navigate(action)
    }

    private fun buzz(pattern: LongArray) {
        val buzzer = activity?.getSystemService<Vibrator>()

        buzzer?.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                buzzer.vibrate(VibrationEffect.createWaveform(pattern, -1))
            } else {
                //deprecated in API 26
                buzzer.vibrate(pattern, -1)
            }
        }
    }
}