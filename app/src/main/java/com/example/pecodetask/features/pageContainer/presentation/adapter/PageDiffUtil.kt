package com.example.pecodetask.features.pageContainer.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.pecodetask.features.pageContainer.domain.model.PageItem

class PagerDiffUtil(private val oldList: List<PageItem>, private val newList: List<PageItem>) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size
    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].pageNumber == newList[newItemPosition].pageNumber
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].pageNumber == newList[newItemPosition].pageNumber
    }
}