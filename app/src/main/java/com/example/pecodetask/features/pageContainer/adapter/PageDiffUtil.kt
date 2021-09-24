package com.example.pecodetask.features.pageContainer.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.pecodetask.features.pageContainer.model.PagerItem

class PagerDiffUtil(private val oldList: List<PagerItem>, private val newList: List<PagerItem>) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size
    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].pageNumber == newList[newItemPosition].pageNumber
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].pageNumber == newList[newItemPosition].pageNumber
    }
}