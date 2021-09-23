package com.example.pecodetask

import android.content.Context
import android.util.AttributeSet
import androidx.cardview.widget.CardView

class PageIndicatorView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {
    init {
        inflate(context, R.layout.view_page_indicator, this)
    }
}