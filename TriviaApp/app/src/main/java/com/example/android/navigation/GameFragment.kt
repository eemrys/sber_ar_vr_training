/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.navigation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_game.*

class GameFragment : Fragment(R.layout.fragment_game) {

    private val questions: MutableList<Question> by lazy { initQuestions() }

    private var currentQuestion = questions[0]
    private var answers = currentQuestion.answers.toMutableList()

    private var questionIndex = 0
    private val numQuestions = ((questions.size + 1) / 2).coerceAtMost(3)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnSubmit.setOnClickListener {
            onSubmit(view)
        }

        randomizeQuestions()
    }

    private fun onSubmit(view: View) {
        val checkedId = radioGroupQuestion.checkedRadioButtonId
        if (-1 != checkedId) {
            var answerIndex = 0
            when (checkedId) {
                R.id.radioBtnSecondAnswer -> answerIndex = 1
                R.id.radioBtnThirdAnswer -> answerIndex = 2
                R.id.radioBtnFourthAnswer -> answerIndex = 3
            }

            if (answers[answerIndex] == currentQuestion.answers[0]) {
                questionIndex++
                if (questionIndex < numQuestions) {
                        currentQuestion = questions[questionIndex]
                        setQuestion()
                } else {
                    view.findNavController()
                            .navigate(GameFragmentDirections.actionFragmentGameToFragmentGameWon(numQuestions,questionIndex))
                    }
            } else {
                view.findNavController()
                        .navigate(GameFragmentDirections.actionFragmentGameToFragmentGameOver())
            }
        }
    }

    private fun initQuestions(): MutableList<Question> {
        return mutableListOf(
                Question(text = "What is Android Jetpack?",
                        answers = listOf("all of these", "tools", "documentation", "libraries")),
                Question(text = "Base class for Layout?",
                        answers = listOf("ViewGroup", "ViewSet", "ViewCollection", "ViewRoot")),
                Question(text = "Layout for complex Screens?",
                        answers = listOf("ConstraintLayout", "GridLayout", "LinearLayout", "FrameLayout")),
                Question(text = "Pushing structured data into a Layout?",
                        answers = listOf("Data Binding", "Data Pushing", "Set Text", "OnClick")),
                Question(text = "Inflate layout in fragments?",
                        answers = listOf("onCreateView", "onActivityCreated", "onCreateLayout", "onInflateLayout")),
                Question(text = "Build system for Android?",
                        answers = listOf("Gradle", "Graddle", "Grodle", "Groyle")),
                Question(text = "Android vector format?",
                        answers = listOf("VectorDrawable", "AndroidVectorDrawable", "DrawableVector", "AndroidVector")),
                Question(text = "Android Navigation Component?",
                        answers = listOf("NavController", "NavCentral", "NavMaster", "NavSwitcher")),
                Question(text = "Registers app with launcher?",
                        answers = listOf("intent-filter", "app-registry", "launcher-registry", "app-launcher")),
                Question(text = "Mark a layout for Data Binding?",
                        answers = listOf("<layout>", "<binding>", "<data-binding>", "<dbinding>"))
        )
    }

    private fun randomizeQuestions() {
        questions.shuffle()
        questionIndex = 0
        setQuestion()
    }

    private fun setQuestion() {
        currentQuestion = questions[questionIndex]
        txtvQuestion.text = currentQuestion.text

        answers = currentQuestion.answers.toMutableList()
        answers.shuffle()
        radioBtnFirstAnswer.text = answers[0]
        radioBtnSecondAnswer.text = answers[1]
        radioBtnThirdAnswer.text = answers[2]
        radioBtnFourthAnswer.text = answers[3]

        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.title_android_trivia_question, questionIndex + 1, numQuestions)
    }
}