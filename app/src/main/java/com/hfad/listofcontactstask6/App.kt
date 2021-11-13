package com.hfad.listofcontactstask6

import android.app.Application
import com.hfad.listofcontactstask6.model.ContactsService

class App : Application() {
    val contactsService = ContactsService()
}