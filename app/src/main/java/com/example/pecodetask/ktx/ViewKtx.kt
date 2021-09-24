package com.example.pecodetask.ktx

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View
import androidx.cardview.widget.CardView

fun View.animateGone() {
    cancelAnimation()
    val fullyTransparentAlpha = 0f

    animate(fullyTransparentAlpha) { view ->
        view.visibility = CardView.GONE
    }
}

fun View.animateVisible() {
    cancelAnimation()
    visibility = CardView.VISIBLE

    val fullyOpaqueAlpha = 1f
    animate(fullyOpaqueAlpha)
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
    val listener = when (completionListener) {
        null -> null
        else -> object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) = completionListener(this@animate)
        }
    }

    animate()
        .alpha(finalAlpha)
        .setDuration(shortAnimationDuration.toLong())
        .setListener(listener)
}