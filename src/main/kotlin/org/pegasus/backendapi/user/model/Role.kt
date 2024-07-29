package org.pegasus.backendapi.user.model

enum class Role(val ru: String, en: String) {

    ADMIN("Администратор", "Admin"),

    USER("Пользователь", "User"),

    GUEST("Гость", "Guest"),

}
