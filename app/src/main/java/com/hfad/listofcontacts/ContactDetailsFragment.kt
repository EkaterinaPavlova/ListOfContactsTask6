package com.hfad.listofcontacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.hfad.listofcontacts.databinding.FragmentContactDetailsBinding
import com.hfad.listofcontacts.model.Contacts

class ContactDetailsFragment : Fragment() {

    private lateinit var binding: FragmentContactDetailsBinding

    private val contact: Contacts
    get() = requireArguments().getSerializable(ARG_CONTACT) as Contacts

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentContactDetailsBinding.inflate(inflater, container, false)

        binding.firstName.text = contact.firstName
        binding.lastName.text = contact.lastName
        binding.number.text = contact.number


        return binding.root
    }

    companion object{
        private const val ARG_CONTACT = "ARG_CONTACT"

        fun newInstance(contact: Contacts) : ContactDetailsFragment{
            val fragment = ContactDetailsFragment()
            fragment.arguments = bundleOf(ARG_CONTACT to contact)
            return fragment
        }
    }
}