package com.vladimir.composition.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.vladimir.composition.R
import com.vladimir.composition.databinding.FragmentGameFinishedBinding
import com.vladimir.composition.domain.entity.GameResult


class GameFinishedFragment : Fragment() {

    private lateinit var gameResult: GameResult


    private var _binding: FragmentGameFinishedBinding? = null
    private val binding: FragmentGameFinishedBinding
        get() = _binding ?: throw RuntimeException("FragmentGameFinishedBinding? = null")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameFinishedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                retryGame()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner, callback
        )

        binding.bTryAgain.setOnClickListener {
            retryGame()
        }
        bindViews()
    }

    private fun bindViews() {
        with(binding) {
            ivResult.setImageResource(getSmileResId())
            tvScore.text = String.format(getString(R.string.your_Score),
                gameResult.countOfRightAnswers)
            tvCorrectAnswers.text = String.format(getString(R.string.needed_percent_correct_answer),
            gameResult.gameSetting.minPercentOfRightAnswers)
            tvNeededPercentage.text = String.format(getString(R.string.correct_answers),
            gameResult.gameSetting.minCountOfRightAnswers)
            tvCorrectPercentage.text = String.format(getString(R.string.percentage_correct_answers),
            getPercentOfRightAnswers())
        }

    }

    private fun getPercentOfRightAnswers() = with(gameResult) {
            if (countOfQuestions == 0){
                 0
            } else {
                ((countOfRightAnswers/countOfQuestions.toDouble()) * 100).toInt()
            }
        }


    private fun getSmileResId():Int {
        return if (gameResult.winner) {
            R.drawable.emoji_happy
        } else {
            R.drawable.emoji_sad
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        purseArgs()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun purseArgs() {
        requireArguments().getParcelable<GameResult>(KEY_GAME_RESULT)?.let {
            gameResult = it
        }
    }

    private fun retryGame() {
        requireActivity().supportFragmentManager.popBackStack(
            GameFragment.NAME_FOR_BACK_STACK,
            FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
    }

    companion object {

        private const val KEY_GAME_RESULT = "game_result"

        fun newInstance(gameResult: GameResult): GameFinishedFragment {
            return GameFinishedFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_GAME_RESULT, gameResult)
                }
            }
        }
    }
}