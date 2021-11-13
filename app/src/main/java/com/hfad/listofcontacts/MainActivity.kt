package com.hfad.listofcontacts

import android.os.Bundle
import android.widget.FrameLayout
import androidx.fragment.app.FragmentActivity
import com.hfad.listofcontacts.databinding.ActivityMainBinding
import com.hfad.listofcontacts.model.Contacts
import com.hfad.listofcontacts.model.ContactsService

class MainActivity : FragmentActivity(), AppContract {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, ContactsFragment())
                .commit()
        }
    }

    override val contactsService: ContactsService
        get() = (applicationContext as App).contactsService

    override fun launchContactDetail(contact: Contacts) {

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