package com.hfad.listofcontactstask6.ui

import android.app.AlertDialog
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hfad.listofcontactstask6.model.Contact
import listofcontactstask6.databinding.ItemContactBinding

class ContactsAdapter(
    private val clickListener: Listener
) : ListAdapter<Contact, ContactsAdapter.ContactsViewHolder>(ContactComparator()) {

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
        val contact = getItem(position)

        if (contact != null) {

            holder.bind(contact)

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

    }

    class ContactComparator : DiffUtil.ItemCallback<Contact>() {
        override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem == newItem
        }
    }

    override fun submitList(list: MutableList<Contact>?) {
        super.submitList(list?.let { ArrayList(it) })
    }

    class ContactsViewHolder(private val binding: ItemContactBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(contact: Contact) {
            binding.apply {
                name.text = contact.toString()
                number.text = contact.number
            }
        }
    }
}