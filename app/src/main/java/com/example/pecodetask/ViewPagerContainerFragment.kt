package com.example.pecodetask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pecodetask.databinding.FragmentViewPagerContainerBinding

class ViewPagerContainerFragment : Fragment() {
    private var _binding: FragmentViewPagerContainerBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val pagerAdapter by lazy { ViewPagerAdapter(this) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentViewPagerContainerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.viewPager.adapter = pagerAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}