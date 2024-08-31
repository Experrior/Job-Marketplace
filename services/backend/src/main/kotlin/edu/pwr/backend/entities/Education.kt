package edu.pwr.backend.entities

import edu.pwr.backend.dto.EducationDTO
import jakarta.persistence.*
import java.sql.Timestamp
import java.time.Instant


@Entity(name = "educations")
class Education(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var educationId: Int? = null,

    @ManyToOne
    @JoinColumn(name = "profileId", referencedColumnName = "profileId")
    var profileId: UserProfile = UserProfile(),

    @Column(nullable = false)
    var institutionName: String = "",
    @Column(nullable = false)
    var degree: String = "",
    @Column(nullable = false)
    var startDate: Timestamp = Timestamp(0),
    @Column(nullable = false)
    var endDate: Timestamp = Timestamp(0),

    @Column(nullable = false)
    var updatedAt: Timestamp = Timestamp(0),
) {

    @PreUpdate
    fun onUpdate() {
        val currentTimestamp = Timestamp.from(Instant.now())
        updatedAt = currentTimestamp
    }

    fun toDTO(): EducationDTO {
        return EducationDTO(
            educationId = this.educationId,
            profileId = this.profileId,  // Assuming UserProfile has a profileId field
            institutionName = this.institutionName,
            degree = this.degree,
            startDate = this.startDate,
            endDate = this.endDate,
            updatedAt = this.updatedAt
        )
    }

}