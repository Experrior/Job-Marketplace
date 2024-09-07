package edu.pwr.backend.entities


import jakarta.persistence.*
import lombok.Data
import java.sql.Timestamp
import java.time.Instant

@Data
@Entity(name = "chat_messages")
class Message(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var messageId: Int? = null,

    @ManyToOne
    @JoinColumn(name = "chatId", referencedColumnName = "chatId")
    var chatId: Chat = Chat(),

    @Column(nullable = false)
    var content: String = "",

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    var createdBy: User = User(),

    @Column
    @ElementCollection
    var readBy: List<Int> = mutableListOf(),

    @Column
    @ElementCollection
    var deletedBy: List<Int> = mutableListOf(),

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


    
}
