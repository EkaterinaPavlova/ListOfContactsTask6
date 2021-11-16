package com.hfad.listofcontactstask6.ui

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.hfad.listofcontactstask6.contract
import com.hfad.listofcontactstask6.model.Contact
import listofcontactstask6.R
import listofcontactstask6.databinding.FragmentContactsBinding
import java.util.*
import kotlin.collections.ArrayList


class ContactsFragment : Fragment(), ContactsAdapter.Listener {

    private lateinit var binding: FragmentContactsBinding
    private lateinit var adapter: ContactsAdapter
    private var listOfContacts = ArrayList<Contact>()
    private var listSearch = ArrayList<Contact>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentContactsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listOfContacts = contract().contactsService.getContacts()
        listSearch.clear()
        listSearch.addAll(listOfContacts)

        adapter = ContactsAdapter( this)
        adapter.submitList(listSearch)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter

        setHasOptionsMenu(true)

    }

    override fun selectContact(contact: Contact) {
        contract().launchContactDetail(contact)
    }

    override fun deleteContact(contact: Contact) {
        contract().contactsService.deleteContact(contact)
        listOfContacts = contract().contactsService.getContacts()
        adapter.submitList(listOfContacts)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.menu_item, menu)

        val searchItem = menu.findItem(R.id.search_contact)
        val searchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String): Boolean {

                listSearch.clear()
                val searchQuery = query.toLowerCase(Locale.getDefault())
                if (query.isNotEmpty()) {
                    listOfContacts.forEach {
                        if (it.firstName.toLowerCase(Locale.getDefault()).contains(searchQuery) ||
                            it.lastName.toLowerCase(Locale.getDefault()).contains(query)) {
                            listSearch.add(it)
                        }
                    }
                    adapter.submitList(listSearch)

                } else {

                    listSearch.addAll(listOfContacts)
                    adapter.submitList(listSearch)
                }
                searchView.clearFocus() // скрываем клавиатуру
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {

                listSearch.clear()
                val searchText = newText.toLowerCase(Locale.getDefault())
                if(searchText.isNotEmpty()){
                    listOfContacts.forEach {
                        if (it.firstName.toLowerCase(Locale.getDefault()).contains(searchText) ||
                            it.lastName.toLowerCase(Locale.getDefault()).contains(searchText)) {

                            listSearch.add(it)
                        }
                    }
                    adapter.submitList(listSearch)

                } else {
                    listSearch.addAll(listOfContacts)
                    adapter.submitList(listSearch)
                }
                return true
            }
        })
    }
}