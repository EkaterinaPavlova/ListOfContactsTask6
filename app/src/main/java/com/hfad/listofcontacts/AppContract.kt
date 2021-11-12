package com.hfad.listofcontacts

import androidx.fragment.app.Fragment
import com.hfad.listofcontacts.model.Contacts
import com.hfad.listofcontacts.model.ContactsService


fun Fragment.contract(): AppContract = requireActivity() as AppContract

interface AppContract {
    val contactsService : ContactsService
    fun launchContactDetail(contact: Contacts)
}