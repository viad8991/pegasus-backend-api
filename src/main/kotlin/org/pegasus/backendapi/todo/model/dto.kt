package org.pegasus.backendapi.todo.model

import org.pegasus.backendapi.todo.model.entity.Task
import org.pegasus.backendapi.todo.model.entity.ToDo
import java.time.Instant

// TODO unused. Fix that. Use this.
data class ToDoDTO(
    val id: String,
    val name: String,
    val tasks: List<TaskDTO>,
) {

    companion object {
        fun of(todo: ToDo): ToDoDTO {
            return ToDoDTO(
                id = todo.id.toString(),
                name = todo.name ?: todo.id.toString(),
                tasks = todo.tasks.map { TaskDTO.of(it) },
            )
        }
    }
}

data class TaskDTO(
    val id: String,
    val description: String,
    val from: Instant?,
    val to: Instant?,
) {

    companion object {
        fun of(task: Task) = TaskDTO(
            task.id.toString(),
            task.description,
            task.from,
            task.to,
        )
    }
}
