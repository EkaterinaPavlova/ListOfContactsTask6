package com.hfad.listofcontactstask6

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.hfad.listofcontactstask6.model.Contact
import listofcontactstask6.databinding.FragmentContactsBinding

class ContactsFragment : Fragment(), ContactsAdapter.SelectContactListener {

    private lateinit var binding: FragmentContactsBinding
    private lateinit var adapter: ContactsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentContactsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val contacts = contract().contactsService.getContacts()

        adapter = ContactsAdapter(contacts, this)
        val layoutManager = LinearLayoutManager(context)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter

    }

    override fun selectContact(contact: Contact) {
        contract().launchContactDetail(contact)
    }
}