package com.example.pecodetask

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.cardview.widget.CardView
import com.example.pecodetask.databinding.ViewPageIndicatorBinding

class PageIndicatorView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {
    private val binding = ViewPageIndicatorBinding.inflate(LayoutInflater.from(context), this)

    private val minusButton get() = binding.minusBtn
    private val plusButton get() = binding.plusBtn
    private val pageNumberTextView get() = binding.pageNumberTv

    fun hideMinusButton() = minusButton.animateGone()
    fun showMinusButton() = minusButton.animateVisible()

    fun changePageNumber(pageNumber: Int) {
        pageNumberTextView.text = "$pageNumber"
    }
}