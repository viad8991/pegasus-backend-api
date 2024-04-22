package org.pegasus.backendapi.todo.repository

import org.pegasus.backendapi.todo.model.entity.Task
import org.pegasus.backendapi.todo.model.entity.ToDo
import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.stereotype.Repository

// Асинхронная работа с репозиторием

@Repository
interface ToDoRepository : R2dbcRepository<ToDo, Long>

@Repository
interface TaskRepository : R2dbcRepository<Task, Long>

// Синхронная работа с репозиторием

//@Repository
//interface ToDoRepository {
//    fun getToDoList(): List<ToDo>
//    fun getToDoById(id: UUID)
//}

//class ToDoRepositoryImpl(
//    val entityManager: EntityManager,
//) : ToDoRepository {
//    override fun getToDoList(): List<ToDo> {
//    }
//
//    override fun getToDoById(id: UUID) {
//    }
//}
