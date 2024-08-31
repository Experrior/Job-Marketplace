package edu.pwr.backend.entities

import edu.pwr.backend.dto.UserSettingsDTO
import jakarta.persistence.*
import java.sql.Timestamp
import java.time.Instant

@Entity(name = "user_settings")
class UserSettings(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var settingsId: Int? = null,

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    var userId: User = User(),

    @Column(nullable = false)
    var offersNotification: Boolean = false,

    @Column(nullable = false)
    var newsletterNotification: Boolean = false,

    @Column(nullable = false)
    var recruiterMessages: Boolean = false,

    @Column(nullable = false)
    var pushNotification: Boolean = false,

    @Column(nullable = false)
    var updatedAt: Timestamp = Timestamp(0)
) {

    @PrePersist
    fun onCreate() {
        val currentTimestamp = Timestamp.from(Instant.now())
        updatedAt = currentTimestamp
    }

    // Convert UserSettings entity to UserSettingsDTO
    fun toDTO(): UserSettingsDTO {
        return UserSettingsDTO(
            settingsId = this.settingsId,
            userId = this.userId,
            offersNotification = this.offersNotification,
            newsletterNotification = this.newsletterNotification,
            recruiterMessages = this.recruiterMessages,
            pushNotification = this.pushNotification,
            updatedAt = this.updatedAt
        )
    }
}
