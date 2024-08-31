package edu.pwr.backend.dto

import edu.pwr.backend.entities.Application
import edu.pwr.backend.entities.Job
import edu.pwr.backend.entities.User
import java.sql.Timestamp
import java.time.Instant


data class ApplicationDTO(
    var applicationId: Int? = null,
    var userId: User = User(),
    var jobId: Job = Job(),
    var status: String = "",
    var applicationDate: Timestamp = Timestamp.from(Instant.now()),
){
    fun toEntity(): Application {
        return Application(
            applicationId = this.applicationId,
            userId = this.userId,
            jobId = this.jobId,
            status = this.status,
            applicationDate = this.applicationDate,
        )
    }
}
