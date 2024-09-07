package edu.pwr.backend.controllers

import edu.pwr.backend.entities.Message
import edu.pwr.backend.services.MessageService
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class MessageController(private val messageService: MessageService) {

    @QueryMapping
    fun messageById(@Argument messageId: Int): Message? {
        return messageService.getMessageById(messageId)
    }

    @QueryMapping
    fun allMessages(
        @Argument chatId: Int,
        @Argument limit: Int? = 10,
        @Argument offset: Int? = 0
    ): List<Message> {
        return messageService.getAllMessages(chatId, limit ?: 10, offset ?: 0)
    }

    @MutationMapping
    fun createMessage(
        @Argument chatId: Int,
        @Argument content: String,
        @Argument createdBy: Int
    ): Message? {
        return messageService.createMessage(chatId, content, createdBy)
    }

    @MutationMapping
    fun updateMessage(
        @Argument messageId: Int,
        @Argument content: String? = null
    ): Message? {
        return messageService.updateMessage(messageId, content)
    }

    @MutationMapping
    fun deleteMessage(@Argument messageId: Int): Boolean {
        return messageService.deleteMessage(messageId)
    }
}
