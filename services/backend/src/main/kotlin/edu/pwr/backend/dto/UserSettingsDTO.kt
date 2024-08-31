package edu.pwr.backend.dto

import edu.pwr.backend.entities.User
import edu.pwr.backend.entities.UserSettings
import java.sql.Timestamp

data class UserSettingsDTO(
    var settingsId: Int? = null,
    var userId: User = User(),  // Storing the User object instead of just the userId
    var offersNotification: Boolean = false,
    var newsletterNotification: Boolean = false,
    var recruiterMessages: Boolean = false,
    var pushNotification: Boolean = false,
    var updatedAt: Timestamp = Timestamp(0)
) {
    // Convert UserSettingsDTO to UserSettings entity
    fun toEntity(): UserSettings {
        return UserSettings(
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
