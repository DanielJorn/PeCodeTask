package com.example.pecodetask.feature.page.presentation.pagecontainer.customview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.appcompat.view.ContextThemeWrapper
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.example.pecodetask.R
import com.example.pecodetask.databinding.ViewPageIndicatorBinding
import com.example.pecodetask.ktx.animateGone
import com.example.pecodetask.ktx.animateVisible

class PageIndicatorView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {
    private val binding = ViewPageIndicatorBinding.inflate(LayoutInflater.from(context), this, true)

    private val minusButton get() = binding.minusBtn
    private val plusButton get() = binding.plusBtn
    private val pageNumberTextSwitcher get() = binding.pageNumberTextSwitcher


    fun hideMinusButton() = minusButton.animateGone()
    fun hideMinusButtonInstantly() {
        minusButton.visibility = GONE
    }
    fun showMinusButton() = minusButton.animateVisible()
    fun changePageNumber(pageNumber: Int) = pageNumberTextSwitcher.setText("$pageNumber")

    fun plusButtonClickListener(clickListener: () -> Unit) =
        plusButton.setOnClickListener { clickListener() }

    fun minusButtonClickListener(clickListener: () -> Unit) =
        minusButton.setOnClickListener { clickListener() }

    private fun initializeTextSwitcherSettings() {
        pageNumberTextSwitcher.setFactory {
            TextView(ContextThemeWrapper(context, R.style.pageNumberTextStyle), null, 0)
        }
        val inAnim = AnimationUtils.loadAnimation(context, android.R.anim.fade_in)
        val outAnim = AnimationUtils.loadAnimation(context, android.R.anim.fade_out)

        inAnim.duration = TEXT_FADE_ANIM_DURATION
        outAnim.duration = TEXT_FADE_ANIM_DURATION
        pageNumberTextSwitcher.inAnimation = inAnim
        pageNumberTextSwitcher.outAnimation = outAnim
    }

    private fun initializeCardSettings() {
        backgroundTintList = ContextCompat.getColorStateList(context, R.color.transparent)
        cardElevation = 0f
    }

    init {
        initializeTextSwitcherSettings()
        initializeCardSettings()
    }

    companion object {
        private const val TEXT_FADE_ANIM_DURATION: Long = 100
    }
}