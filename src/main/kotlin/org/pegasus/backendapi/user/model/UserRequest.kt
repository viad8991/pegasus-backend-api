package org.pegasus.backendapi.user.model

import org.pegasus.backendapi.utils.IRequest

data class UserRequest(val username: String) : IRequest
