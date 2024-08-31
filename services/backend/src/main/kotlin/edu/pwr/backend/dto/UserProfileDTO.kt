package edu.pwr.backend.dto

import edu.pwr.backend.entities.User
import edu.pwr.backend.entities.UserProfile
import java.sql.Timestamp

data class UserProfileDTO(
    var profileId: Int? = null,
    var userId: User = User(),  // Storing the User object instead of just the userId
    var resumePath: String = "",
    var profilePicturePath: String = "",
    var updatedAt: Timestamp = Timestamp(0)
) {
    // Convert UserProfileDTO to UserProfile entity
    fun toEntity(): UserProfile {
        return UserProfile(
            profileId = this.profileId,
            userId = this.userId,
            resumePath = this.resumePath,
            profilePicturePath = this.profilePicturePath,
            updatedAt = this.updatedAt
        )
    }
}
