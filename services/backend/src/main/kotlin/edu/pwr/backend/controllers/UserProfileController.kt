package edu.pwr.backend.controllers

import edu.pwr.backend.entities.UserProfile
import org.springframework.stereotype.Service
import java.sql.Timestamp
import java.time.Instant

import edu.pwr.backend.repositories.UserProfileRepository
import edu.pwr.backend.repositories.UserRepository
import edu.pwr.backend.services.UserProfileService
import org.springframework.data.domain.PageRequest

import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class UserProfileController(private val userProfileService: UserProfileService) {

    @QueryMapping
    fun userProfileById(@Argument profileId: Int): UserProfile? {
        return userProfileService.getUserProfileById(profileId)
    }

    @QueryMapping
    fun allUserProfiles(@Argument limit: Int? = 10, @Argument offset: Int? = 0): List<UserProfile> {
        return userProfileService.getAllUserProfiles(limit ?: 10, offset ?: 0)
    }

    @MutationMapping
    fun createUserProfile(
        @Argument userId: Int,
        @Argument resumePath: String,
        @Argument profilePicturePath: String
    ): UserProfile? {
        return userProfileService.createUserProfile(userId, resumePath, profilePicturePath)
    }

    @MutationMapping
    fun updateUserProfile(
        @Argument profileId: Int,
        @Argument resumePath: String? = null,
        @Argument profilePicturePath: String? = null
    ): UserProfile? {
        return userProfileService.updateUserProfile(profileId, resumePath, profilePicturePath)
    }

    @MutationMapping
    fun deleteUserProfile(@Argument profileId: Int): Boolean {
        return userProfileService.deleteUserProfile(profileId)
    }
}
