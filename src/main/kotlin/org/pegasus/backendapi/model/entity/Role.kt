package org.pegasus.backendapi.model.entity

import jakarta.persistence.Entity

@Entity
enum class Role(val ruName: String) {

    ADMIN("Администратор"),

    USER("Пользователь"),

    GUEST("Гость"),

}