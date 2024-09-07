package edu.pwr.backend.entities



import jakarta.persistence.*
import java.sql.Timestamp
import java.time.Instant

@Entity
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


}