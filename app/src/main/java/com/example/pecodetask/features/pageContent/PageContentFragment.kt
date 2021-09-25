package com.example.pecodetask.features.pageContent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pecodetask.databinding.FragmentPageContentBinding
import com.example.pecodetask.features.pageContainer.model.PagerItem

class PageContentFragment : Fragment() {

    private val viewModel: PageContentViewModel by viewModels(
        factoryProducer = { PageContentViewModelFactory(getArgumentsPageNumber()) }
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentPageContentBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel

        return binding.root
    }

    private fun getArgumentsPageNumber(): Long {
        val args = requireArguments()
        return args.getLong(PAGE_NUMBER)
    }

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