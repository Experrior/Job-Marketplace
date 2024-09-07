package edu.pwr.backend.services

import edu.pwr.backend.entities.UserProfile
import edu.pwr.backend.repositories.UserProfileRepository
import edu.pwr.backend.repositories.UserRepository
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.sql.Timestamp
import java.time.Instant

@Service
class UserProfileService(
    private val userProfileRepository: UserProfileRepository,
    private val userRepository: UserRepository
) {

    fun getUserProfileById(profileId: Int): UserProfile? {
        return userProfileRepository.findById(profileId).orElse(null)
    }

    fun getAllUserProfiles(limit: Int = 10, offset: Int = 0): List<UserProfile> {
        return userProfileRepository.findAll(PageRequest.of(offset, limit)).content
    }

    fun createUserProfile(
        userId: Int,
        resumePath: String,
        profilePicturePath: String
    ): UserProfile? {
        val user = userRepository.findById(userId).orElse(null) ?: return null

        val newUserProfile = UserProfile(
            userId = user,
            resumePath = resumePath,
            profilePicturePath = profilePicturePath,
            updatedAt = Timestamp.from(Instant.now())
        )
        return userProfileRepository.save(newUserProfile)
    }

    fun updateUserProfile(
        profileId: Int,
        resumePath: String? = null,
        profilePicturePath: String? = null
    ): UserProfile? {
        val existingProfile = userProfileRepository.findById(profileId).orElse(null) ?: return null

        resumePath?.let { existingProfile.resumePath = it }
        profilePicturePath?.let { existingProfile.profilePicturePath = it }
        existingProfile.updatedAt = Timestamp.from(Instant.now())

        return userProfileRepository.save(existingProfile)
    }

    fun deleteUserProfile(profileId: Int): Boolean {
        val profile = userProfileRepository.findById(profileId).orElse(null) ?: return false
        userProfileRepository.delete(profile)
        return true
    }
}
