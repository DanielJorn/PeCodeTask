package com.example.pecodetask.features.pageContainer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.pecodetask.databinding.FragmentViewPagerContainerBinding
import com.example.pecodetask.features.pageContainer.adapter.ViewPagerAdapter

private const val TAG = "PagerContainerFragment"

class ViewPagerContainerFragment : Fragment() {
    private var _binding: FragmentViewPagerContainerBinding? = null
    private val binding get() = _binding!!

    private val viewPager get() = binding.viewPager
    private val pageIndicator get() = binding.pageIndicator

    private val pagerAdapter by lazy { ViewPagerAdapter(this) }
    private val pageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(pageIndex: Int) =
            this@ViewPagerContainerFragment.onPageSelected(pageIndex)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentViewPagerContainerBinding.inflate(inflater, container, false)

        restoreStateOnCreation(savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewPager.adapter = pagerAdapter
        viewPager.registerOnPageChangeCallback(pageChangeCallback)
        pageIndicator.plusButtonClickListener(::onPlusButtonClicked)
        pageIndicator.minusButtonClickListener(::onMinusButtonClicked)
    }

    private fun restoreStateOnCreation(savedInstanceState: Bundle?) {
        if (wasFirstPageSelectedInBundle(savedInstanceState)) {
            pageIndicator.hideMinusButtonInstantly()
        }
    }

    private fun wasFirstPageSelectedInBundle(savedInstanceState: Bundle?): Boolean {
        return savedInstanceState == null // TODO
    }

    private fun onPlusButtonClicked() {
        pagerAdapter.addPage()
        scrollToLastPage()
    }

    private fun onMinusButtonClicked() {
        pagerAdapter.removePage()
    }

    private fun scrollToLastPage() {
        viewPager.currentItem = pagerAdapter.lastPageIndex
    }

    private fun onPageSelected(pageIndex: Int) {
        when (pageIndex) {
            0 -> hideIndicatorMinusButton()
            else -> showIndicatorMinusButton()
        }
        changeIndicatorPageNumber(pageIndex)
    }

    private fun changeIndicatorPageNumber(pageIndex: Int) {
        val pageNumber = pageIndex + 1
        pageIndicator.changePageNumber(pageNumber)
    }

    private fun hideIndicatorMinusButton() {
        pageIndicator.hideMinusButton()
    }

    private fun showIndicatorMinusButton() {
        pageIndicator.showMinusButton()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewPager.unregisterOnPageChangeCallback(pageChangeCallback)
        _binding = null
    }
}