package com.example.pecodetask.features.pageContent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pecodetask.R
import com.example.pecodetask.features.pageContainer.model.PagerItem

class PageContentFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_page_content, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val arguments = requireArguments()
        val pageNumber = getArgumentsPageNumber(arguments)
    }

    private fun getArgumentsPageNumber(args: Bundle) = args.getLong(PAGE_NUMBER)

    companion object {
        private const val PAGE_NUMBER = "PAGE_NUMBER"

        fun newInstance(data: PagerItem): PageContentFragment {
            val bundle = Bundle().apply {
                putLong(PAGE_NUMBER, data.pageNumber)
            }

            return PageContentFragment().apply {
                arguments = bundle
            }
        }
    }
}