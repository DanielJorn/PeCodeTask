package com.example.pecodetask.feature.page.presentation.pagecontainer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.example.pecodetask.databinding.FragmentViewPagerContainerBinding
import com.example.pecodetask.feature.page.presentation.pagecontainer.adapter.ViewPagerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ViewPagerContainerFragment : Fragment() {
    private var _binding: FragmentViewPagerContainerBinding? = null
    private val binding get() = _binding!!

    private val viewPager get() = binding.viewPager
    private val pageIndicator get() = binding.pageIndicator

    private val viewModel: ViewPagerContainerViewModel by viewModels()
    private val lastPageNumber get() = pagerAdapter.lastPageNumber

    private val navArguments: ViewPagerContainerFragmentArgs by navArgs()

    private val pagerAdapter by lazy { ViewPagerAdapter(this) }
    private val pageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(pageIndex: Int) = onNewPageSelected(pageIndex)
    }

    override fun onCreateView(inflater: LayoutInflater, root: ViewGroup?, state: Bundle?): View {
        _binding = FragmentViewPagerContainerBinding.inflate(inflater, root, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }

        return binding.root
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        restoreStateOnCreation(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupUi()
        navigateToPageFromArguments()
    }

    private fun setupUi() {
        viewPager.apply {
            adapter = pagerAdapter
            registerOnPageChangeCallback(pageChangeCallback)
        }

        pageIndicator.apply {
            plusButtonClickListener(::onPlusButtonClicked)
            minusButtonClickListener(::onMinusButtonClicked)
        }
    }

    private fun navigateToPageFromArguments() {
        val selectedPage = navArguments.selectedPageNumber
        pagerAdapter.setPageCount(selectedPage)
        viewPager.currentItem = selectedPage
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(PAGES_COUNT_BUNDLE_KEY, pagerAdapter.itemCount)
        outState.putInt(SELECTED_PAGE_INDEX_BUNDLE_KEY, viewPager.currentItem)
    }

    private fun restoreStateOnCreation(savedInstanceState: Bundle?) {
        restoreMinusButtonVisibility(savedInstanceState)
        restorePageNumberText(savedInstanceState)
        restoreCountOfCreatedPages(savedInstanceState)
        restoreSelectedPage(savedInstanceState)
    }

    private fun restoreMinusButtonVisibility(state: Bundle?) {
        val wasFirstPageRestored = state?.getInt(SELECTED_PAGE_INDEX_BUNDLE_KEY) == 0
        val wasFirstPageInitiallySelected = navArguments.selectedPageNumber == 1
        val wasFirstPageLaunched = wasFirstPageRestored || wasFirstPageInitiallySelected

        when {
            wasFirstPageLaunched -> pageIndicator.hideMinusButtonInstantly()
            else -> pageIndicator.showMinusButton()
        }
    }

    private fun restorePageNumberText(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) return
        val savedPagesCount = getSavedPagesCount(savedInstanceState)
        pageIndicator.changePageNumber(savedPagesCount)
    }

    private fun restoreCountOfCreatedPages(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) return
        val savedPagesCount = getSavedPagesCount(savedInstanceState)
        pagerAdapter.setPageCount(savedPagesCount)
    }

    private fun restoreSelectedPage(state: Bundle?) {
        if (state == null) return
        val savedSelectedPage = getSavedSelectedPage(state)

        val needAnimation = false
        viewPager.setCurrentItem(savedSelectedPage, needAnimation)
    }

    private fun getSavedPagesCount(state: Bundle) = state.getInt(PAGES_COUNT_BUNDLE_KEY, 1)
    private fun getSavedSelectedPage(state: Bundle) =
        state.getInt(SELECTED_PAGE_INDEX_BUNDLE_KEY, 1)

    private fun onPlusButtonClicked() {
        pagerAdapter.addPage()
        scrollToLastPage()
    }

    private fun onMinusButtonClicked() {
        viewModel.onMinusButtonClicked(lastPageNumber)
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
        private const val SELECTED_PAGE_INDEX_BUNDLE_KEY = "SELECTED_PAGE_INDEX_BUNDLE_KEY"
    }
}