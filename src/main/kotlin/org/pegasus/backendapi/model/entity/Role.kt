package org.pegasus.backendapi.model.entity

enum class Role(val ru: String, en: String) {

    ADMIN("Администратор", "Admin"),

    USER("Пользователь", "User"),

    GUEST("Гость", "Guest"),

}
