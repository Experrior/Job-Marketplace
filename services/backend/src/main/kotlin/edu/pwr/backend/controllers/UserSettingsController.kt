package edu.pwr.backend.controllers

import edu.pwr.backend.entities.UserSettings
import edu.pwr.backend.services.UserSettingsService
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class UserSettingsController(private val userSettingsService: UserSettingsService) {

    @QueryMapping
    fun userSettingsById(@Argument settingsId: Int): UserSettings? {
        return userSettingsService.getUserSettingsById(settingsId)
    }

    @QueryMapping
    fun allUserSettings(@Argument limit: Int? = 10, @Argument offset: Int? = 0): List<UserSettings> {
        return userSettingsService.getAllUserSettings(limit ?: 10, offset ?: 0)
    }

    @MutationMapping
    fun createUserSettings(
        @Argument userId: Int,
        @Argument offersNotification: Boolean,
        @Argument newsletterNotification: Boolean,
        @Argument recruiterMessages: Boolean,
        @Argument pushNotification: Boolean
    ): UserSettings? {
        return userSettingsService.createUserSettings(userId, offersNotification, newsletterNotification, recruiterMessages, pushNotification)
    }

    @MutationMapping
    fun updateUserSettings(
        @Argument settingsId: Int,
        @Argument offersNotification: Boolean? = null,
        @Argument newsletterNotification: Boolean? = null,
        @Argument recruiterMessages: Boolean? = null,
        @Argument pushNotification: Boolean? = null
    ): UserSettings? {
        return userSettingsService.updateUserSettings(settingsId, offersNotification, newsletterNotification, recruiterMessages, pushNotification)
    }

    @MutationMapping
    fun deleteUserSettings(@Argument settingsId: Int): Boolean {
        return userSettingsService.deleteUserSettings(settingsId)
    }
}
