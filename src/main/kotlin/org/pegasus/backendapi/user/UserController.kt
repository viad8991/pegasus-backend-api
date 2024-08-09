package org.pegasus.backendapi.user

import org.apache.logging.log4j.kotlin.logger
import org.pegasus.backendapi.user.model.UserResponse
import org.pegasus.backendapi.user.service.UserInternalService
import org.pegasus.backendapi.user.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/api/v1/user")
class UserController(
    val userInternalService: UserInternalService,
    val userService: UserService,
) {

    private val log = logger()

    @GetMapping("/", "/{id}")
    fun user(@PathVariable(required = false) id: UUID? = null): ResponseEntity<UserResponse> {
        val foundUser = if (id == null) UserMapper.toDto(userInternalService.currentUser()) else userService.findUser(id)

        return if (foundUser == null) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.ok(UserMapper.toResponse(foundUser))
        }
    }

    @GetMapping("/list")
    fun all(): ResponseEntity<List<UserResponse>> = userService.getAllUsers()
        .map { userDto -> UserMapper.toResponse(userDto) }
        .let { ResponseEntity.ok(it) }

}
