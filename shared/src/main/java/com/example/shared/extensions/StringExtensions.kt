package com.example.shared.extensions

fun String.isValidEmail(): Boolean = matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex())

fun String.isValidPassword(): Boolean =
    matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+\$).*[A-Za-z0-9].{8,}\$".toRegex())

fun String.isValidName(): Boolean = matches("(?i)[a-z]([- ',.a-z]{0,23}[a-z])?".toRegex())

fun String.isNotValidPassword(): Boolean = this.isValidPassword().not()

fun String.isNotValidEmail(): Boolean = this.isValidEmail().not()

fun String.isNotValidName(): Boolean = this.isValidName().not()

fun String?.notNull(): String = this ?: ""