package edu.pwr.backend.dto

import edu.pwr.backend.entities.Chat
import edu.pwr.backend.entities.User
import edu.pwr.backend.entities.UserSettings
import java.sql.Timestamp

data class ChatCreationDTO(
    var name: String = "",
    var members: List<Int> = mutableListOf(),
    var createdBy: User = User(),
) {

    fun toEntity(): Chat {
        return Chat(
            name = this.name,
            members = this.members,
            createdBy = this.createdBy,
        )
    }
}
