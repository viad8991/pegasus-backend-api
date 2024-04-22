package org.pegasus.backendapi.todo.controller

import org.pegasus.backendapi.todo.model.ToDoDTO
import org.pegasus.backendapi.todo.service.ToDoService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.servlet.function.ServerRequest
import java.util.*

@RestController("/api/v1/todo")
interface ToDoController {

    @GetMapping("/list")
    suspend fun list(): ServerResponse

    @GetMapping("/{id}")
    suspend fun getToDo(serverRequest: ServerRequest): ServerResponse
}

class ToDoControllerImpl(
    private val toDoService: ToDoService,
) : ToDoController {
    override suspend fun list(): ServerResponse {
        TODO("Not yet implemented")
    }

    override suspend fun getToDo(serverRequest: ServerRequest): ServerResponse {
        TODO("Not yet implemented")
    }
}
