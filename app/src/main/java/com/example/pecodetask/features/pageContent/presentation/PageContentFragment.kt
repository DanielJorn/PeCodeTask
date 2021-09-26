package com.example.pecodetask.features.pageContent.presentation

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.media.RingtoneManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.pecodetask.R
import com.example.pecodetask.databinding.FragmentPageContentBinding
import com.example.pecodetask.features.pageContainer.domain.model.PageItem
import com.example.pecodetask.features.pageContent.domain.model.NotificationData
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