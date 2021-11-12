package com.hfad.listofcontacts

import android.app.Application
import com.hfad.listofcontacts.model.ContactsService

class App : Application() {
    val contactsService = ContactsService()
}