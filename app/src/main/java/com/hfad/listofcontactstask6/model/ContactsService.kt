package com.hfad.listofcontactstask6.model

import com.github.javafaker.Faker

class ContactsService {

    private var contacts = ArrayList<Contact>()

    init {
        val faker = Faker.instance()

        contacts = (1..100).map{
            Contact(
                id = it,
                firstName = faker.name().firstName(),
                lastName = faker.name().lastName(),
                number = faker.phoneNumber().phoneNumber(),
                photo = "https://picsum.photos/200/300"
            )
        } as ArrayList<Contact>
    }

    fun getContacts() : ArrayList<Contact> {
        return contacts
    }

    fun deleteContact(contact: Contact){
        val indexToDelete = contacts.indexOfFirst { it.id == contact.id }
        if(indexToDelete != - 1){
            contacts = ArrayList(contacts)
            contacts.removeAt(indexToDelete)
        }
    }
}