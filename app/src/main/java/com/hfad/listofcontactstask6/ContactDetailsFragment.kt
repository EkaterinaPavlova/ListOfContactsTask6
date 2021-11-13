package com.hfad.listofcontactstask6

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.hfad.listofcontactstask6.model.Contact
import listofcontactstask6.R
import listofcontactstask6.databinding.FragmentContactDetailsBinding

class ContactDetailsFragment : Fragment() {

    private lateinit var binding: FragmentContactDetailsBinding

    private val contact: Contact
    get() = requireArguments().getSerializable(ARG_CONTACT) as Contact

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
        val image = view.findViewById<ImageView>(R.id.image)

        firstName.text = contact.firstName
        lastName.text = contact.lastName
        number.text = contact.number

        Glide.with(activity?.applicationContext!!)
            .load(contact.photo)
            .circleCrop()
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .placeholder(R.drawable.ic_baseline_account_circle_24)
            .error(R.drawable.ic_baseline_error_24)
            .into(image)

        binding.buttonSave.setOnClickListener {
            contact.firstName = binding.firstName.text.toString()
            contact.lastName = binding.lastName.text.toString()
            contact.number = binding.number.text.toString()

            parentFragmentManager.popBackStack()

            val imm: InputMethodManager =
                activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    companion object{
        private const val ARG_CONTACT = "ARG_CONTACT"

        fun newInstance(contact: Contact) : ContactDetailsFragment{
            val fragment = ContactDetailsFragment()
            fragment.arguments = bundleOf(ARG_CONTACT to contact)
            return fragment
        }
    }
}