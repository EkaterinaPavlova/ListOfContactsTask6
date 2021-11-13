package com.hfad.listofcontacts.model

import java.io.Serializable

data class Contacts (
    var firstName : String,
    var lastName : String,
    var number : String
) : Serializable {
    override fun toString(): String {
        return firstName
    }
}