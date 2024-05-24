package org.pegasus.backendapi.social.comment

import org.pegasus.backendapi.route.DestinationRepository
import org.pegasus.backendapi.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class CommentService(
    @Autowired private val commentRepository: CommentRepository,
    @Autowired private val userRepository: UserRepository,
    @Autowired private val destinationRepository: DestinationRepository
) {

    fun addComment(userId: UUID, destinationId: UUID, text: String, rating: Int): Comment {
        val user = userRepository.findById(userId) ?: throw RuntimeException("User not found")
        val destination = destinationRepository.findById(destinationId) ?: throw RuntimeException("Destination not found")
        val comment = Comment(user = user, destination = destination, text = text, rating = rating)
        return commentRepository.create(comment)
    }

    fun getCommentsByDestination(destinationId: UUID): List<Comment> {
        return commentRepository.findByDestinationId(destinationId)
    }
}
