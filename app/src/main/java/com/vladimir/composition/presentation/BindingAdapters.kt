package com.vladimir.composition.presentation

import android.content.Context
import android.content.res.ColorStateList
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.vladimir.composition.R
import com.vladimir.composition.domain.entity.GameResult

interface OnOptionClickListener {
    fun onOptionClick(option: Int)
}


@BindingAdapter("correctAnswers")
fun bindCorrectAnswers(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.correct_answers),
        count
    )
}


@BindingAdapter("gameScore")
fun bindGameScore(textView: TextView, score: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.your_Score),
        score
    )
}

@BindingAdapter("neededPercentage")
fun bindNeededPercentage(textView: TextView, percentage: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.needed_percent_correct_answer),
        percentage
    )
}

@BindingAdapter("correctPercentage")
fun bindCorrectPercentage(textView: TextView, gameResult: GameResult) {
    textView.text = String.format(
        textView.context.getString(R.string.needed_percent_correct_answer),
        getPercentOfRightAnswers(gameResult)
    )
}


private fun getPercentOfRightAnswers(gameResult: GameResult) = with(gameResult) {
    if (countOfQuestions == 0) {
        0
    } else {
        ((countOfRightAnswers / countOfQuestions.toDouble()) * 100).toInt()
    }
}

@BindingAdapter("resultEmoji")
fun getResultEmoji(imageView: ImageView, winner: Boolean) {
    imageView.setImageResource(getSmileResId(winner))
}

private fun getSmileResId(winner: Boolean): Int {
    return if (winner) {
        R.drawable.emoji_happy
    } else {
        R.drawable.emoji_sad
    }
}

@BindingAdapter("enoughCount")
fun bindEnoughCount(textView: TextView, enough: Boolean){
    textView.setTextColor(getColorByState(textView.context, enough))
}

@BindingAdapter("enoughPercent")
fun bindEnoughPercent(progressBar: ProgressBar, enough: Boolean){
    val color = getColorByState(progressBar.context ,enough)
    progressBar.progressTintList = ColorStateList.valueOf(color)

}

private fun getColorByState(context: Context, goodState: Boolean): Int {
    val colorResId = if (goodState) {
        android.R.color.holo_green_light
    } else {
        android.R.color.holo_red_light
    }
    return ContextCompat.getColor(context, colorResId)
}

@BindingAdapter("numberAsText")
fun bindNumberAsText(textView: TextView, number: Int) {
    textView.text = number.toString()
}

@BindingAdapter("onOptionClickListener")
fun bindOnOptionClickListener(textView: TextView, clickListener:OnOptionClickListener) {
    textView.setOnClickListener {
        clickListener.onOptionClick(textView.text.toString().toInt())
    }
}




