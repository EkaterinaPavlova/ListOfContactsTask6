package com.hfad.listofcontactstask6.ui

import android.app.AlertDialog
import android.app.Application
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hfad.listofcontactstask6.model.Contact
import listofcontactstask6.databinding.ItemContactBinding

class ContactsAdapter(
    private var oldContactsList: List<Contact>,
    private val clickListener: Listener
) : RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder>() {

    interface Listener {
        fun selectContact(contact: Contact)
        fun deleteContact(contact: Contact)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemContactBinding.inflate(inflater, parent, false)
        return ContactsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        val contact = oldContactsList[position]
        with(holder.binding) {
            name.text = "${contact.firstName} ${contact.lastName}"
            number.text = contact.number
        }

        holder.itemView.setOnClickListener {
            clickListener.selectContact(contact)
        }

        holder.itemView.setOnLongClickListener {

            val builder = AlertDialog.Builder(holder.itemView.context)
            builder.setMessage("Delete this contact?")
            builder.setPositiveButton("YES") { _: DialogInterface, _: Int ->
                clickListener.deleteContact(contact)
            }
            builder.setNegativeButton("CANCEL") { _: DialogInterface, _: Int -> }
            builder.show()
            return@setOnLongClickListener true
        }
    }

    override fun getItemCount(): Int = oldContactsList.size

    fun setData(newContactsList: List<Contact>) {
        val diffUtil = DiffUtilContacts(oldContactsList, newContactsList)
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        oldContactsList = newContactsList
        diffResults.dispatchUpdatesTo(this)
    }

    class ContactsViewHolder(val binding: ItemContactBinding) :
        RecyclerView.ViewHolder(binding.root)

}