package com.example.pecodetask.features.pageContainer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.pecodetask.databinding.FragmentViewPagerContainerBinding
import com.example.pecodetask.features.pageContainer.adapter.ViewPagerAdapter

class ViewPagerContainerFragment : Fragment() {
    private var _binding: FragmentViewPagerContainerBinding? = null
    private val binding get() = _binding!!

    private val viewPager get() = binding.viewPager
    private val pageIndicator get() = binding.pageIndicator

    private val pagerAdapter by lazy { ViewPagerAdapter(this) }
    private val pageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(pageIndex: Int) = onNewPageSelected(pageIndex)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentViewPagerContainerBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        restoreStateOnCreation(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewPager.adapter = pagerAdapter
        viewPager.registerOnPageChangeCallback(pageChangeCallback)
        pageIndicator.plusButtonClickListener(::onPlusButtonClicked)
        pageIndicator.minusButtonClickListener(::onMinusButtonClicked)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(PAGES_COUNT_BUNDLE_KEY, pagerAdapter.itemCount)
        outState.putInt(SELECTED_PAGE_BUNDLE_KEY, viewPager.currentItem)
        outState.putBoolean(MINUS_BUTTON_HIDDEN_BUNDLE_KEY, isMinusButtonHidden())
    }

    private fun isMinusButtonHidden() = isFirstPageSelected()

    private fun isFirstPageSelected() = viewPager.currentItem == 0

    private fun restoreStateOnCreation(savedInstanceState: Bundle?) {
        restoreMinusButtonVisibility(savedInstanceState)
        restorePageNumberText(savedInstanceState)
        restoreCountOfCreatedPages(savedInstanceState)
        restoreSelectedPage(savedInstanceState)
    }

    private fun restoreMinusButtonVisibility(savedInstanceState: Bundle?) {
        when (wasMinusButtonHidden(savedInstanceState)) {
            true -> pageIndicator.hideMinusButtonInstantly()
            false -> pageIndicator.showMinusButton()
        }
    }

    private fun restoreSelectedPage(state: Bundle?) {
        if (state == null) return
        val savedSelectedPage = getSavedSelectedPage(state)

        val needAnimation = false
        viewPager.setCurrentItem(savedSelectedPage, needAnimation)
    }

    private fun restoreCountOfCreatedPages(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) return
        val savedPagesCount = getSavedPagesCount(savedInstanceState)
        pagerAdapter.setPageCount(savedPagesCount)
    }

    private fun restorePageNumberText(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) return
        val savedPagesCount = getSavedPagesCount(savedInstanceState)
        pageIndicator.changePageNumber(savedPagesCount)
    }

    private fun getSavedPagesCount(state: Bundle) = state.getInt(PAGES_COUNT_BUNDLE_KEY, 1)

    private fun getSavedSelectedPage(state: Bundle) = state.getInt(SELECTED_PAGE_BUNDLE_KEY, 1)

    private fun wasMinusButtonHidden(state: Bundle?): Boolean {
        if (state == null) return true
        return state.getBoolean(MINUS_BUTTON_HIDDEN_BUNDLE_KEY, true)
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

    private fun onNewPageSelected(pageIndex: Int) {
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

    companion object {
        private const val PAGES_COUNT_BUNDLE_KEY = "PAGES_COUNT_BUNDLE_KEY"
        private const val SELECTED_PAGE_BUNDLE_KEY = "SELECTED_PAGE_BUNDLE_KEY"
        private const val MINUS_BUTTON_HIDDEN_BUNDLE_KEY = "MINUS_BUTTON_HIDDEN_BUNDLE_KEY"
    }
}