package com.example.pecodetask.features.pageContainer.adapter

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.pecodetask.features.pageContainer.model.PagerItem
import com.example.pecodetask.features.pageContent.PageContentFragment
import kotlin.math.max

class ViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val items: ArrayList<PagerItem> = arrayListOf(PagerItem(1))

    override fun createFragment(position: Int) = PageContentFragment.newInstance(items[position])
    override fun getItemCount() = items.size
    override fun getItemId(position: Int) = items[position].pageNumber
    override fun containsItem(itemId: Long) = items.any { it.pageNumber == itemId }

    val lastPageIndex get() = max(0, itemCount - 1)

    fun addPage() {
        val newPage = PagerItem((itemCount + 1).toLong())
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
        val newList = List(pageCount) { pageIndex -> PagerItem((pageIndex + 1).toLong()) }
        setItems(newList)
    }

    private fun setItems(newItems: List<PagerItem>) {
        val callback = PagerDiffUtil(items, newItems)
        val diff = DiffUtil.calculateDiff(callback)

        items.clear()
        items.addAll(newItems)
        diff.dispatchUpdatesTo(this)
    }
}