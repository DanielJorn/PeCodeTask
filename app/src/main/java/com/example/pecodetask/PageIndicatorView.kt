package com.example.pecodetask

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class PageIndicatorView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {

    init {
        inflate(context, R.layout.view_page_indicator, this)
    }

    fun hideMinusButton() {
        val minusButton = findViewById<FloatingActionButton>(R.id.pageIndicator_minusBtn)
        minusButton.visibility = GONE
    }

    fun showMinusButton() {
        val minusButton = findViewById<FloatingActionButton>(R.id.pageIndicator_minusBtn)
        minusButton.visibility = VISIBLE
    }

    fun changePageNumber(pageNumber: Int) {
        val pageNumberTextView = findViewById<TextView>(R.id.pageIndicator_pageNumberTv)
        pageNumberTextView.text = "$pageNumber"
    }
}