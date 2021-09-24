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

        restoreStateOnCreation(savedInstanceState)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewPager.adapter = pagerAdapter
        viewPager.registerOnPageChangeCallback(pageChangeCallback)
        pageIndicator.plusButtonClickListener { onPlusButtonClicked() }
        pageIndicator.minusButtonClickListener { onMinusButtonClicked() }
    }

    private fun shouldScrollToCreatedPage(): Boolean {
        val currentPage = viewPager.currentItem
        val currentPageIsRightBeforeLastPage = currentPage == pagerAdapter.lastPageIndex - 1
        return currentPageIsRightBeforeLastPage
    }

    private fun scrollToLastPage() {
        viewPager.currentItem = pagerAdapter.lastPageIndex
    }

    private fun restoreStateOnCreation(savedInstanceState: Bundle?) {
        if (isFirstPageWasSelectedInBundle(savedInstanceState)) {
            pageIndicator.hideMinusButtonInstantly()
        }
    }

    private fun isFirstPageWasSelectedInBundle(savedInstanceState: Bundle?): Boolean {
        return savedInstanceState == null
    }

    private fun onPlusButtonClicked() {
        pagerAdapter.addPage()
        if (shouldScrollToCreatedPage())
            scrollToLastPage()
    }

    private fun onMinusButtonClicked() {
        pagerAdapter.removePage()
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