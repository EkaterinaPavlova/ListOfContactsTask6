package com.hfad.listofcontacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.hfad.listofcontacts.databinding.FragmentContactsBinding

class ContactsFragment : Fragment() {

    private lateinit var binding: FragmentContactsBinding

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

        val contacts = contract().contactsService.contacts
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, contacts)
        binding.listContactsFragment.adapter = adapter
        binding.listContactsFragment.setOnItemClickListener { _, _, position, _ ->
            val currentItem = adapter.getItem(position)!!
            contract().launchContactDetail(currentItem)
        }
    }
}