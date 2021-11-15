package com.hfad.listofcontactstask6.ui

import androidx.recyclerview.widget.DiffUtil
import com.hfad.listofcontactstask6.model.Contact

class DiffUtilContacts(
    private val oldList: List<Contact>,
    private val newList: List<Contact>
): DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}