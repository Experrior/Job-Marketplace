package edu.pwr.backend.entities

import edu.pwr.backend.dto.SkillDTO
import edu.pwr.backend.enums.EProficiency
import jakarta.persistence.*
import java.sql.Timestamp
import java.time.Instant


@Entity(name = "skills")
class Skill(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var skillId: Int? = null,

    @ManyToOne
    @JoinColumn(name = "profileId", referencedColumnName = "profileId")
    var profileId: UserProfile = UserProfile(),

    @Column(nullable = false)
    var skillName: String = "",

    @Column(nullable = false)
    var proficencyLevel: EProficiency = EProficiency.INTERMEDIATE,

    @Column(nullable = false)
    var updatedAt: Timestamp = Timestamp(0),
) {

    @PreUpdate
    fun onUpdate() {
        val currentTimestamp = Timestamp.from(Instant.now())
        updatedAt = currentTimestamp
    }

    fun toDTO(): SkillDTO {
        return SkillDTO(
            skillId = this.skillId,
            profileId = this.profileId,
            skillName = this.skillName,
            proficencyLevel = this.proficencyLevel,
            updatedAt = this.updatedAt
        )
    }

}