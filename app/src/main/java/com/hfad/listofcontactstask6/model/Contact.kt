package com.hfad.listofcontactstask6.model

import java.io.Serializable

data class Contact(
    var id: Long,
    var firstName: String,
    var lastName: String,
    var number: String,
    var photo: String
) : Serializable {
}