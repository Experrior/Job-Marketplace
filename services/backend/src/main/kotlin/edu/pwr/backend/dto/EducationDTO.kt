package edu.pwr.backend.dto

import edu.pwr.backend.entities.Education
import edu.pwr.backend.entities.Job
import edu.pwr.backend.entities.UserProfile
import java.sql.Timestamp


data class EducationDTO(
    var educationId: Int? = null,
    var profileId: UserProfile = UserProfile(),
    var institutionName: String = "",
    var degree: String = "",
    var startDate: Timestamp = Timestamp(0),
    var endDate: Timestamp = Timestamp(0),
    var updatedAt: Timestamp = Timestamp(0)
){



    fun toEntity(): Education {
        return Education(
            educationId = this.educationId,
            profileId = this.profileId,
            institutionName = this.institutionName,
            degree = this.degree,
            startDate = this.startDate,
            endDate = this.endDate,
            updatedAt = this.updatedAt
        )
    }


}
