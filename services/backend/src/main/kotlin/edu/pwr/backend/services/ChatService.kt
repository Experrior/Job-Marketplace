package edu.pwr.backend.services

import edu.pwr.backend.entities.Chat
import edu.pwr.backend.repositories.ChatRepository
import edu.pwr.backend.repositories.UserRepository
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.sql.Timestamp
import java.time.Instant

@Service
class ChatService(
    private val chatRepository: ChatRepository,
    private val userRepository: UserRepository
) {

    fun getChatById(chatId: Int): Chat? {
        return chatRepository.findById(chatId).orElse(null)
    }

    fun getAllChats(limit: Int = 10, offset: Int = 0): List<Chat> {
        return chatRepository.findAll(PageRequest.of(offset, limit)).content
    }

    fun createChat(
        name: String,
        members: List<Int>,
        createdById: Int,
        lastMessage: String? = null,
        tags: List<String>? = listOf()
    ): Chat? {
        val createdBy = userRepository.findById(createdById).orElse(null) ?: return null

        val newChat = Chat(
            name = name,
            members = members,
            createdBy = createdBy,
            lastMessage = lastMessage ?: "",
            tags = tags ?: listOf(),
            createdAt = Timestamp.from(Instant.now()),
            updatedAt = Timestamp.from(Instant.now())
        )

        return chatRepository.save(newChat)
    }

    fun updateChat(
        chatId: Int,
        name: String? = null,
        members: List<Int>? = null,
        lastMessage: String? = null,
        tags: List<String>? = null
    ): Chat? {
        val existingChat = chatRepository.findById(chatId).orElse(null) ?: return null

        name?.let { existingChat.name = it }
        members?.let { existingChat.members = it }
        lastMessage?.let { existingChat.lastMessage = it }
        tags?.let { existingChat.tags = it }

        existingChat.updatedAt = Timestamp.from(Instant.now())
        return chatRepository.save(existingChat)
    }

    fun deleteChat(chatId: Int): Boolean {
        val chat = chatRepository.findById(chatId).orElse(null) ?: return false
        chatRepository.delete(chat)
        return true
    }
}
