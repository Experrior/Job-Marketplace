package edu.pwr.backend.entities

import edu.pwr.backend.dto.ApplicationDTO
import edu.pwr.backend.dto.JobDTO
import jakarta.persistence.*
import java.sql.Timestamp
import java.time.Instant

@Entity(name = "jobs")
class Job(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var jobId: Int? = null,
    @Column(nullable = false)
    var companyId: Int = -1,
    @Column(nullable = false)
    var jobTitle: String = "",
    @Column(nullable = false)
    var jobDescription: String = "",
    @Column(nullable = false)
    var requiredSkills: String = "",
    @Column(nullable = false)
    var requiredExperience: String = "",
    @Column(nullable = false)
    var location: String = "",
    @Column(nullable = true)
    var salary: String? = null,
    @Column(nullable = false)
    var createdAt: Timestamp = Timestamp(0)
) {

    @PrePersist
    fun onCreate() {
        val currentTimestamp = Timestamp.from(Instant.now())
        createdAt = currentTimestamp
    }

    fun toDTO(): JobDTO {
        return JobDTO(
            jobId = this.jobId,
            companyId = this.companyId,
            jobTitle = this.jobTitle,
            jobDescription = this.jobDescription,
            requiredSkills = this.requiredSkills,
            requiredExperience = this.requiredExperience,
            location = this.location,
            salary = this.salary,
            createdAt = this.createdAt
        )
    }

}