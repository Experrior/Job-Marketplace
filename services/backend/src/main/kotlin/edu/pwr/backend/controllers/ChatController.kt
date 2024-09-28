package edu.pwr.backend.controllers

import edu.pwr.backend.entities.Chat
import edu.pwr.backend.services.ChatService
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class ChatController(private val chatService: ChatService) {

    @QueryMapping
    fun chatById(@Argument chatId: Int): Chat? {
        return chatService.getChatById(chatId)
    }

    @QueryMapping
    fun allChats(@Argument limit: Int? = 10, @Argument offset: Int? = 0): List<Chat> {
        return chatService.getAllChats(limit ?: 10, offset ?: 0)
    }

    @MutationMapping
    fun createChat(
        @Argument name: String,
        @Argument members: List<Int>,
        @Argument createdBy: Int,
        @Argument lastMessage: String? = null,
        @Argument tags: List<String>? = null
    ): Chat? {
        return chatService.createChat(name, members, createdBy, lastMessage, tags)
    }

    @MutationMapping
    fun updateChat(
        @Argument chatId: Int,
        @Argument name: String? = null,
        @Argument members: List<Int>? = null,
        @Argument lastMessage: String? = null,
        @Argument tags: List<String>? = null
    ): Chat? {
        return chatService.updateChat(chatId, name, members, lastMessage, tags)
    }

    @MutationMapping
    fun deleteChat(@Argument chatId: Int): Boolean {
        return chatService.deleteChat(chatId)
    }
}
