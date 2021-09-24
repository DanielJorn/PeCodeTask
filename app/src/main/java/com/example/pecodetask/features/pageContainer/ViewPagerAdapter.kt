package com.example.pecodetask.features.pageContainer

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.pecodetask.features.pageContent.PageContentFragment

class ViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    private var itemCount = 1
    private val lastItemIndex get() = getItemCount() - 1

    override fun getItemCount() = itemCount
    override fun createFragment(position: Int) = PageContentFragment.newInstance(position + 1)

    fun addPage() {
        itemCount += 1
        notifyNewItemInsertedAtEnd()
    }

    fun removePage() {
        itemCount -= 1
        notifyLastItemRemoved()
    }

    private fun notifyLastItemRemoved() {
        notifyItemRemoved(lastItemIndex)
    }

    private fun notifyNewItemInsertedAtEnd() {
        notifyItemInserted(lastItemIndex)
    }
}