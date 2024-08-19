package org.pegasus.backendapi.user

import java.util.*

class UserNotFoundException(id: UUID) : RuntimeException("User with id $id not exist")
