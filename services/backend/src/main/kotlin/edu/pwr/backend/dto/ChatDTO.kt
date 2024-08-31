package edu.pwr.backend.dto

import edu.pwr.backend.entities.Chat
import edu.pwr.backend.entities.User
import edu.pwr.backend.entities.UserSettings
import java.sql.Timestamp

data class ChatDTO(
    var chatId: Int? = null,
    var name: String = "",
    var members: List<Int> = mutableListOf(),
    var createdBy: User = User(),
    var deletedBy: List<Int> = mutableListOf(),
    var lastMessage: String = "",
    var tags: List<String> = mutableListOf(),
    var createdAt: Timestamp = Timestamp(0),
    var updatedAt: Timestamp = Timestamp(0),
) {

    fun toEntity(): Chat {
        return Chat(
            chatId = this.chatId,
            name = this.name,
            members = this.members,
            createdBy = this.createdBy,
            deletedBy = this.deletedBy,
            lastMessage = this.lastMessage,
            tags = this.tags,
            createdAt = this.createdAt,
            updatedAt = this.updatedAt
        )
    }
}
