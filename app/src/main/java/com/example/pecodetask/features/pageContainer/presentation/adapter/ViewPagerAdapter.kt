package com.example.pecodetask.features.pageContainer.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.pecodetask.features.pageContainer.domain.model.PageItem
import com.example.pecodetask.features.pageContent.presentation.PageContentFragment
import kotlin.math.max

class ViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val items: ArrayList<PageItem> = arrayListOf(PageItem(1))

    override fun createFragment(position: Int) = PageContentFragment.newInstance(items[position])
    override fun getItemCount() = items.size
    override fun getItemId(position: Int) = items[position].pageNumber
    override fun containsItem(itemId: Long) = items.any { it.pageNumber == itemId }

    val lastPageIndex get() = max(0, itemCount - 1)
    val lastPageNumber get() = itemCount

    fun addPage() {
        val newPage = PageItem((itemCount + 1).toLong())
        val newList = items.toMutableList()
        newList.add(newPage)
        setItems(newList)
    }

    fun removePage() {
        val newList = items.toMutableList()
        newList.removeLast()
        setItems(newList)
    }

    fun setPageCount(pageCount: Int) {
        val newList = List(pageCount) { pageIndex -> PageItem((pageIndex + 1).toLong()) }
        setItems(newList)
    }

    private fun setItems(newItems: List<PageItem>) {
        val callback = PagerDiffUtil(items, newItems)
        val diff = DiffUtil.calculateDiff(callback)

        items.clear()
        items.addAll(newItems)
        diff.dispatchUpdatesTo(this)
    }
}