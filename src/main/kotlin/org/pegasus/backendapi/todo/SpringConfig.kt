package org.pegasus.backendapi.todo

import org.pegasus.backendapi.todo.controller.ToDoController
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.beans
import org.springframework.web.reactive.function.server.coRouter
import java.util.*

val todoInitializer: ApplicationContextInitializer<GenericApplicationContext> = beans {

}
