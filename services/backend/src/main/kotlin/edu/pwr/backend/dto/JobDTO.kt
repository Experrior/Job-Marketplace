package edu.pwr.backend.dto

import edu.pwr.backend.entities.Job
import java.sql.Timestamp

data class JobDTO(
    var jobId: Int? = null,
    var companyId: Int = -1,
    var jobTitle: String = "",
    var jobDescription: String = "",
    var requiredSkills: String = "",
    var requiredExperience: String = "",
    var location: String = "",
    var salary: String? = null,
    var createdAt: Timestamp = Timestamp(0)
){



    fun toEntity(): Job {
        return Job(
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
