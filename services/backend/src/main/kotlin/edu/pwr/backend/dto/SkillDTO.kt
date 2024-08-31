package edu.pwr.backend.dto

import edu.pwr.backend.entities.Skill
import edu.pwr.backend.entities.UserProfile
import edu.pwr.backend.enums.EProficiency
import java.sql.Timestamp

data class SkillDTO(
    var skillId: Int? = null,
    var profileId: UserProfile = UserProfile(),  // Storing the profile ID instead of the entire UserProfile object
    var skillName: String = "",
    var proficencyLevel: EProficiency = EProficiency.INTERMEDIATE,
    var updatedAt: Timestamp = Timestamp(0)
)
{
    fun toEntity(): Skill {
        return Skill(
            skillId = this.skillId,
            profileId = this.profileId,
            skillName = this.skillName,
            proficencyLevel = this.proficencyLevel,
            updatedAt = this.updatedAt
        )
    }

}