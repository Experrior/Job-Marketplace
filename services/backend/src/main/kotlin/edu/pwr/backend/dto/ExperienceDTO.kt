package edu.pwr.backend.dto

import edu.pwr.backend.entities.Experience
import edu.pwr.backend.entities.UserProfile
import java.sql.Timestamp

data class ExperienceDTO(
    var experienceId: Int? = null,
    var profileId: UserProfile = UserProfile(),  // Assuming you want to store the profile ID rather than the whole UserProfile object
    var companyName: String = "",
    var role: String = "",
    var startDate: Timestamp = Timestamp(0),
    var endDate: Timestamp = Timestamp(0),
    var updatedAt: Timestamp = Timestamp(0)
) {
    fun toEntity(): Experience {
        return Experience(
            experienceId = this.experienceId,
            profileId = this.profileId,
            companyName = this.companyName,
            role = this.role,
            startDate = this.startDate,
            endDate = this.endDate,
            updatedAt = this.updatedAt
        )
    }

}
