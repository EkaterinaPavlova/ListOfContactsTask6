package com.hfad.listofcontactstask6

import androidx.fragment.app.Fragment
import com.hfad.listofcontactstask6.model.Contact
import com.hfad.listofcontactstask6.model.ContactsService


fun Fragment.contract(): AppContract = requireActivity() as AppContract

interface AppContract {
    val contactsService : ContactsService
    fun launchContactDetail(contact: Contact)
}