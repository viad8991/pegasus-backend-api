package org.pegasus.backendapi.family

class FamilyAlreadyException(username: String) : RuntimeException("Family for user $username already exist")

class FamilyNotExistException(username: String) : RuntimeException("Family for user $username not exist")
