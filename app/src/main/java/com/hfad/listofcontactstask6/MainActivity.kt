package com.hfad.listofcontactstask6

import android.os.Bundle
import android.widget.FrameLayout
import androidx.fragment.app.FragmentActivity
import com.hfad.listofcontactstask6.model.Contact
import com.hfad.listofcontactstask6.model.ContactsService
import com.hfad.listofcontactstask6.ui.ContactDetailsFragment
import com.hfad.listofcontactstask6.ui.ContactsFragment
import listofcontactstask6.R

class MainActivity : FragmentActivity(), AppContract {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, ContactsFragment())
                .commit()
        }
    }

    override val contactsService: ContactsService
        get() = (applicationContext as App).contactsService

    override fun launchContactDetail(contact: Contact) {

        if (findViewById<FrameLayout>(R.id.fragment_container_2) != null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container_2, ContactDetailsFragment.newInstance(contact))
                .commit()

        } else {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, ContactDetailsFragment.newInstance(contact))
                .addToBackStack(null)
                .commit()
        }
    }
}