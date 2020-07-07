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

package com.example.android.guesstheword.screens.score

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.android.guesstheword.R
import kotlinx.android.synthetic.main.fragment_score.*

class ScoreFragment : Fragment(R.layout.fragment_score) {

    private val viewModelFactory by lazy {
        val scoreFragmentArgs by navArgs<ScoreFragmentArgs>()
        ScoreViewModelFactory(scoreFragmentArgs.score)
    }
    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(ScoreViewModel::class.java)
    }

    private val navOptions by lazy {
        NavOptions.Builder()
                .setEnterAnim(R.anim.slide_in_right)
                .setExitAnim(R.anim.slide_out_left)
                .setPopEnterAnim(R.anim.slide_in_right)
                .setPopExitAnim(R.anim.slide_out_left)
                .setPopUpTo(R.id.fragmentTitle, false)
                .build()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnPlayAgain.setOnClickListener { viewModel.onPlayAgain() }
        setObserver()
    }

    private fun setObserver() {
        viewModel.apply{
            finalScore.observe(viewLifecycleOwner, Observer {
                txtvScore.text = it.toString()
            })
            eventPlayAgain.observe(viewLifecycleOwner, Observer {
                if (it) {
                    findNavController().navigate(R.id.fragmentGame, null, navOptions)
                    viewModel.onPlayAgainCompleted()
                }
            })
        }
    }
}