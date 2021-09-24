package com.example.pecodetask.features.pageContent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pecodetask.R

class PageContentFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_page_content, container, false)

    companion object {
        private const val PAGE_NUMBER = "PAGE_NUMBER"

        fun newInstance(pageNumber: Int): PageContentFragment {
            val bundle = Bundle().apply {
                putInt(PAGE_NUMBER, pageNumber)
            }

            return PageContentFragment().apply {
                arguments = bundle
            }
        }
    }
}