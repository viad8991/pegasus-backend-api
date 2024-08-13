package org.pegasus.backendapi.notification

import jakarta.persistence.EntityManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.pegasus.backendapi.notification.service.NotificationInternalService
import org.pegasus.backendapi.notification.service.NotificationService
import org.pegasus.backendapi.user.service.UserInternalService
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.beans
import kotlin.coroutines.CoroutineContext

@OptIn(ExperimentalCoroutinesApi::class)
val notificationInitializer: ApplicationContextInitializer<GenericApplicationContext> = beans {

    bean("notificationDispatcher") {
        Dispatchers.IO.limitedParallelism(10)
    }

    bean {
        val entityManager = ref<EntityManager>()
        NotificationRepository(entityManager)
    }

    bean {
        NotificationInternalService()
    }

    bean {
        val notificationRepository = ref<NotificationRepository>()
        val userService = ref<UserInternalService>()
        val coroutineContext = ref<CoroutineContext>("notificationDispatcher")

        NotificationService(notificationRepository, userService, coroutineContext)
    }

    bean {
        val notificationService = ref<NotificationService>()
        NotificationHandler(notificationService)
    }

}
