package com.hfad.listofcontacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val firstName = view.findViewById<EditText>(R.id.firstName) as TextView
        val lastName = view.findViewById<EditText>(R.id.lastName) as TextView
        val number = view.findViewById<EditText>(R.id.number) as TextView

        firstName.text = contact.firstName
        lastName.text = contact.lastName
        number.text = contact.number

        binding.buttonSave.setOnClickListener {
            contact.firstName = binding.firstName.text.toString()
            contact.lastName = binding.lastName.text.toString()
            contact.number = binding.number.text.toString()

            parentFragmentManager.popBackStack()
        }
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