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
        NotificationService(notificationRepository, userService)
    }

    bean {
        val notificationService = ref<NotificationService>()
        NotificationEventListener(notificationService)
    }

    // Failed to instantiate [org.springframework.context.ApplicationEventPublisher]: Specified class is an interface
    // bean<ApplicationEventPublisher>()
    // bean {
    //     val applicationEventPublisher = ref<ApplicationEventPublisher>()
    //      EventPublisher(applicationEventPublisher)
    // }

    bean {
//        val coroutineContext = ref<CoroutineContext>("notificationDispatcher")
//        val notificationService = ref<NotificationService>()
//        NotificationHandler(notificationService, coroutineContext)
    }

}
