package com.hfad.listofcontacts.model

class ContactsService {

    val contacts: List<Contacts> = mutableListOf(
        Contacts("Ekateina", "Pavlova", "+79117453621"),
        Contacts("Anton", "Liskov", "+79817653892"),
        Contacts("Svetlana", "Makova", "+79211986473"),
        Contacts("Dima", "Petrov", "+79028694637")
    )
}