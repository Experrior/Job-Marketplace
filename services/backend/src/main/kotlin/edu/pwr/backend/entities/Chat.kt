package edu.pwr.backend.entities

import edu.pwr.backend.dto.ApplicationDTO
import edu.pwr.backend.dto.ChatDTO
import edu.pwr.backend.dto.UserSettingsDTO
import jakarta.persistence.*
import java.sql.Timestamp
import java.time.Instant

@Entity(name = "chats")
class Chat(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var chatId: Int? = null,

    @Column(nullable = false)
    var name: String = "",

    @Column
    @ElementCollection
    var members: List<Int> = mutableListOf(),

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    var createdBy: User = User(),

    @Column
    @ElementCollection
    var deletedBy: List<Int> = mutableListOf(),

    @Column
    var lastMessage: String = "",

    @Column
    @ElementCollection
    var tags: List<String> = mutableListOf(),

    @Column(nullable = false)
    var createdAt: Timestamp = Timestamp(0),

    @Column(nullable = false)
    var updatedAt: Timestamp = Timestamp(0),
) {

    @PrePersist
    fun onCreate() {
        val currentTimestamp = Timestamp.from(Instant.now())
        updatedAt = currentTimestamp
        createdAt = currentTimestamp
    }

    @PreUpdate
    fun onUpdate() {
        val currentTimestamp = Timestamp.from(Instant.now())
        updatedAt = currentTimestamp
    }

    fun toDTO(): ChatDTO {
        return ChatDTO(
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
