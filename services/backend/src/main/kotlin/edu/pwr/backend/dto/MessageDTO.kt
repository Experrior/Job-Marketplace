package edu.pwr.backend.dto

import edu.pwr.backend.entities.Chat
import edu.pwr.backend.entities.Message
import edu.pwr.backend.entities.User
import java.sql.Timestamp

data class MessageDTO(
    var messageId: Int? = null,
    var chatId: Chat = Chat(),
    var content: String = "",
    var createdBy: User = User(),
    var readBy: List<Int> = mutableListOf(),
    var deletedBy: List<Int> = mutableListOf(),
    var createdAt: Timestamp = Timestamp(0),
    var updatedAt: Timestamp = Timestamp(0),
) {

    fun toEntity(): Message {
        return Message(
            messageId = this.messageId,
            chatId = this.chatId,
            content = this.content,
            createdBy = this.createdBy,
            readBy = this.readBy,
            deletedBy = this.deletedBy,
            createdAt = this.createdAt,
            updatedAt = this.updatedAt
        )
    }
}
