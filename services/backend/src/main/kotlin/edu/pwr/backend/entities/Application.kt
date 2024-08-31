package edu.pwr.backend.entities

import edu.pwr.backend.dto.ApplicationDTO
import jakarta.persistence.*
import java.sql.Timestamp
import java.time.Instant
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor

@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "applications")
class Application(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var applicationId: Int? = null,

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    var userId: User = User(),

    @ManyToOne
    @JoinColumn(name = "jobId", referencedColumnName = "jobId")
    var jobId: Job = Job(),
    @Column(nullable = false)
    var status: String = "",
    @Column(nullable = false)
    var applicationDate: Timestamp = Timestamp(0),
) {
    @PrePersist
    fun onCreate() {
        val currentTimestamp = Timestamp.from(Instant.now())
        status = "PENDING"
        applicationDate = currentTimestamp
    }

    fun toDTO(): ApplicationDTO {
        return ApplicationDTO(
            applicationId = this.applicationId,
            userId = this.userId,
            jobId = this.jobId,
            status = this.status,
            applicationDate = this.applicationDate,
        )
    }
}