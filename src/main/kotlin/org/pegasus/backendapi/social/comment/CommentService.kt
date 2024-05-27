package org.pegasus.backendapi.social.comment

import org.pegasus.backendapi.repository.UserRepositoryJPA
import org.pegasus.backendapi.route.destination.DestinationRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*

@Service
class CommentService(
    @Autowired private val commentRepository: CommentRepository,
    @Autowired private val userRepository: UserRepositoryJPA,
    @Autowired private val destinationRepository: DestinationRepository
) {

    fun addComment(userId: UUID, destinationId: UUID, text: String, rating: Int): Comment {
        val user = userRepository.findByIdOrNull(userId) ?: throw RuntimeException("User not found")
        val destination =
            destinationRepository.findById(destinationId) ?: throw RuntimeException("Destination not found")
        val comment = Comment(user = user, destination = destination, text = text, rating = rating)
        return commentRepository.create(comment)
    }

    fun getCommentsByDestination(destinationId: UUID): List<Comment> {
        return commentRepository.findByDestinationId(destinationId)
    }
}
