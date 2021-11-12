package com.hfad.listofcontacts.model

import java.io.Serializable

data class Contacts (
    val firstName : String,
    val lastName : String,
    val number : String
) : Serializable {
    override fun toString(): String {
        return firstName
    }
}