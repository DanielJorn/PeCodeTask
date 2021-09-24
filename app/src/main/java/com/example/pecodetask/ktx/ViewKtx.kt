package com.example.pecodetask.ktx

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View
import androidx.cardview.widget.CardView


fun View.animateGone() {
    cancelAnimation()
    val finalAlpha = 0f

    animate(finalAlpha) { view ->
        view.visibility = CardView.GONE
    }
}

fun View.animateVisible() {
    cancelAnimation()

    visibility = CardView.VISIBLE
    animate(1f)
}

private fun View.cancelAnimation() {
    clearAnimation()
    animate().cancel()
}

private fun View.animate(
    finalAlpha: Float,
    completionListener: ((view: View) -> Unit)? = null,
) {
    val shortAnimationDuration = resources.getInteger(android.R.integer.config_shortAnimTime)
    val listener = if (completionListener == null)
        null
    else
        object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) = completionListener(this@animate)
        }

    animate()
        .alpha(finalAlpha)
        .setDuration(shortAnimationDuration.toLong())
        .setListener(listener)
}