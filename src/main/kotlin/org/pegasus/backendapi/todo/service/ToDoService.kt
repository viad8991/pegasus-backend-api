package org.pegasus.backendapi.todo.service

import org.pegasus.backendapi.todo.model.ToDoDTO
import org.pegasus.backendapi.todo.model.entity.Task
import org.pegasus.backendapi.todo.model.entity.ToDo
import org.pegasus.backendapi.todo.repository.TaskRepository
import org.pegasus.backendapi.todo.repository.ToDoRepository
import org.pegasus.backendapi.utils.log
import java.util.*
import java.util.concurrent.Flow

interface ToDoService {
    fun getToDoList(): List<ToDoDTO>
    fun getTasks(todoId: UUID): List<Task>
}

class ToDoServiceImpl(
    private val toDoRepository: ToDoRepository,
    private val taskRepository: TaskRepository,
) : ToDoService {

    //    private val log = LoggerFactory.getLogger(ToDoServiceImpl::class.java)
    private val log = log()
    override fun getToDoList(): List<ToDoDTO> {
        TODO("Not yet implemented")
    }

    override fun getTasks(todoId: UUID): List<Task> {
        TODO("Not yet implemented")
    }
}
