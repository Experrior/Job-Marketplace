package edu.pwr.backend.services

import edu.pwr.backend.entities.Message
import edu.pwr.backend.repositories.ChatRepository
import edu.pwr.backend.repositories.MessageRepository
import edu.pwr.backend.repositories.UserRepository
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.sql.Timestamp
import java.time.Instant

@Service
class MessageService(
    private val messageRepository: MessageRepository,
    private val chatRepository: ChatRepository,
    private val userRepository: UserRepository
) {

    fun getMessageById(messageId: Int): Message? {
        return messageRepository.findById(messageId).orElse(null)
    }

    fun getAllMessages(chatId: Int, limit: Int = 10, offset: Int = 0): List<Message> {
        val chat = chatRepository.findById(chatId).orElse(null) ?: return emptyList()
        return messageRepository.findByChatId(chat, PageRequest.of(offset, limit)).content
    }

    fun createMessage(
        chatId: Int,
        content: String,
        createdById: Int
    ): Message? {
        val chat = chatRepository.findById(chatId).orElse(null) ?: return null
        val createdBy = userRepository.findById(createdById).orElse(null) ?: return null

        val newMessage = Message(
            chatId = chat,
            content = content,
            createdBy = createdBy,
            createdAt = Timestamp.from(Instant.now()),
            updatedAt = Timestamp.from(Instant.now())
        )

        return messageRepository.save(newMessage)
    }

    fun updateMessage(messageId: Int, content: String? = null): Message? {
        val existingMessage = messageRepository.findById(messageId).orElse(null) ?: return null

        content?.let { existingMessage.content = it }
        existingMessage.updatedAt = Timestamp.from(Instant.now())

        return messageRepository.save(existingMessage)
    }

    fun deleteMessage(messageId: Int): Boolean {
        val message = messageRepository.findById(messageId).orElse(null) ?: return false
        messageRepository.delete(message)
        return true
    }
}
