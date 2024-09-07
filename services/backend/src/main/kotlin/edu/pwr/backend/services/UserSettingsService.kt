package edu.pwr.backend.services

import edu.pwr.backend.entities.UserSettings
import edu.pwr.backend.repositories.UserRepository
import edu.pwr.backend.repositories.UserSettingsRepository
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.sql.Timestamp
import java.time.Instant

@Service
class UserSettingsService(
    private val userSettingsRepository: UserSettingsRepository,
    private val userRepository: UserRepository
) {

    fun getUserSettingsById(settingsId: Int): UserSettings? {
        return userSettingsRepository.findById(settingsId).orElse(null)
    }

    fun getAllUserSettings(limit: Int = 10, offset: Int = 0): List<UserSettings> {
        return userSettingsRepository.findAll(PageRequest.of(offset, limit)).content
    }

    fun createUserSettings(
        userId: Int,
        offersNotification: Boolean,
        newsletterNotification: Boolean,
        recruiterMessages: Boolean,
        pushNotification: Boolean
    ): UserSettings? {
        val user = userRepository.findById(userId).orElse(null) ?: return null

        val newUserSettings = UserSettings(
            userId = user,
            offersNotification = offersNotification,
            newsletterNotification = newsletterNotification,
            recruiterMessages = recruiterMessages,
            pushNotification = pushNotification,
            updatedAt = Timestamp.from(Instant.now())
        )
        return userSettingsRepository.save(newUserSettings)
    }

    fun updateUserSettings(
        settingsId: Int,
        offersNotification: Boolean? = null,
        newsletterNotification: Boolean? = null,
        recruiterMessages: Boolean? = null,
        pushNotification: Boolean? = null
    ): UserSettings? {
        val existingSettings = userSettingsRepository.findById(settingsId).orElse(null) ?: return null

        offersNotification?.let { existingSettings.offersNotification = it }
        newsletterNotification?.let { existingSettings.newsletterNotification = it }
        recruiterMessages?.let { existingSettings.recruiterMessages = it }
        pushNotification?.let { existingSettings.pushNotification = it }
        existingSettings.updatedAt = Timestamp.from(Instant.now())

        return userSettingsRepository.save(existingSettings)
    }

    fun deleteUserSettings(settingsId: Int): Boolean {
        val settings = userSettingsRepository.findById(settingsId).orElse(null) ?: return false
        userSettingsRepository.delete(settings)
        return true
    }
}
