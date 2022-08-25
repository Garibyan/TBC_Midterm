package com.garibyan.armen.tbc_midterm.view.auth

data class User(
    val email: String = "",
)
{
    object UserInstance {
        var userData: User? = null
    }
}