package com.example.pecodetask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.pecodetask.databinding.FragmentViewPagerContainerBinding

private const val TAG = "PagerContainerFragment"

class ViewPagerContainerFragment : Fragment() {
    private var _binding: FragmentViewPagerContainerBinding? = null
    private val binding get() = _binding!!

    private val pagerAdapter by lazy { ViewPagerAdapter(this) }
    private val pageChangeCallback by lazy {
        object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) =
                this@ViewPagerContainerFragment.onPageSelected(position)
        }
    }

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
        binding.viewPager.registerOnPageChangeCallback(pageChangeCallback)
    }

    private fun onPageSelected(position: Int) {
        val firstPageSelected = position == 0
        if (firstPageSelected)
            hideIndicatorMinusButton()
        else
            showIndicatorMinusButton()
        changeIndicatorPageNumber(position)
    }

    private fun changeIndicatorPageNumber(pageIndex: Int) {
        val pageNumber = pageIndex + 1
        binding.pageIndicator.changePageNumber(pageNumber)
    }

    private fun hideIndicatorMinusButton() {
        binding.pageIndicator.hideMinusButton()
    }

    private fun showIndicatorMinusButton() {
        binding.pageIndicator.showMinusButton()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.viewPager.unregisterOnPageChangeCallback(pageChangeCallback)
    }
}