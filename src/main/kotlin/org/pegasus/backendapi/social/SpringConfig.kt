package org.pegasus.backendapi.social

import jakarta.persistence.EntityManager
import org.pegasus.backendapi.social.comment.CommentRepository
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.beans

val socialInitializer: ApplicationContextInitializer<GenericApplicationContext> = beans {

    bean {
        val entityManager = ref<EntityManager>()

        CommentRepository(entityManager)
    }

}
