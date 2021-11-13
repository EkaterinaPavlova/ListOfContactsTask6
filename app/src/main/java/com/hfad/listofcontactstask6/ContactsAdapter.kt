package com.hfad.listofcontactstask6

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hfad.listofcontactstask6.model.Contact
import listofcontactstask6.databinding.ItemContactBinding


interface deleteContactListener{
    fun deleteContact(id: Int)
}


class ContactsAdapter(
    private val contacts: List<Contact>,
    private val clickListener: SelectContactListener
) : RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder>() {

    interface SelectContactListener{
        fun selectContact(contact: Contact)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemContactBinding.inflate(inflater, parent, false)
        return ContactsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        val contact = contacts[position]
        with(holder.binding){
            name.text = "${contact.firstName} ${contact.lastName}"
            number.text = contact.number
        }

        holder.itemView.setOnClickListener {
         clickListener.selectContact(contact)
        }
    }

    override fun getItemCount(): Int = contacts.size


    class ContactsViewHolder(val binding: ItemContactBinding) : RecyclerView.ViewHolder(binding.root)

}