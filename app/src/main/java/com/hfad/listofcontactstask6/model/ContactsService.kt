package com.hfad.listofcontactstask6.model

import com.github.javafaker.Faker

class ContactsService {

    private var contacts = mutableListOf<Contact>()

    init {
        val faker = Faker.instance()

        contacts = (1..100).map{
            Contact(
                id = it.toLong(),
                firstName = faker.name().firstName(),
                lastName = faker.name().lastName(),
                number = faker.phoneNumber().phoneNumber(),
                photo = "https://picsum.photos/200/300"
            )
        }.toMutableList()
    }

    fun getContacts() : List<Contact> {
        return contacts
    }
}