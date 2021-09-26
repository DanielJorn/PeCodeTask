package com.example.pecodetask.feature.page.presentation.pagecontent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.pecodetask.databinding.FragmentPageContentBinding
import com.example.pecodetask.feature.page.domain.model.PageItem
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class PageContentFragment : Fragment() {

    private val argumentPageNumber get() = requireArguments().getInt(PAGE_NUMBER)

    @Inject
    lateinit var factory: PageContentViewModel.AssistedFactory
    private val viewModel: PageContentViewModel by viewModels(factoryProducer = {
        PageContentViewModel.provideFactory(factory, argumentPageNumber)
    })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentPageContentBinding.inflate(inflater, container, false).apply {
            viewModel = this@PageContentFragment.viewModel
            lifecycleOwner = viewLifecycleOwner
        }

        return binding.root
    }

    companion object {
        private const val PAGE_NUMBER = "PAGE_NUMBER"

        fun newInstance(data: PageItem): PageContentFragment {
            val bundle = Bundle().apply {
                putInt(PAGE_NUMBER, data.pageNumber)
            }

            return PageContentFragment().apply {
                arguments = bundle
            }
        }
    }
}