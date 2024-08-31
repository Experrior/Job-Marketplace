package edu.pwr.backend.entities

import edu.pwr.backend.dto.CompanyDTO
import jakarta.persistence.*
import java.sql.Timestamp
import java.time.Instant

@Entity(name = "companies")
class Company(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var companyId: Int? = null,
    @Column(nullable = false)
    var companyName: String = "default",
    @Column(nullable = false)
    var location: String = "default",
    @Column(nullable = false)
    var industry: String = "default",
    @Column(nullable = false)
    var description: String = "default",
    @Column(nullable = false)
    var verified: Boolean = false,
    @Column(nullable = false)
    var createdAt: Timestamp = Timestamp.from(Instant.now()),
    @Column(nullable = false)
    var updatedAt: Timestamp = Timestamp.from(Instant.now()),
) {

    fun toDTO(): CompanyDTO {
        return CompanyDTO(
            companyId = this.companyId,
            companyName = this.companyName,
            location = this.location,
            industry = this.industry,
            description = this.description,
            verified = this.verified,
            createdAt = this.createdAt,
            updatedAt = this.updatedAt
        )
    }

    @PrePersist
    fun onCreate() {
        val currentTimestamp = Timestamp.from(Instant.now())
        createdAt = currentTimestamp
        updatedAt = currentTimestamp
    }

    @PreUpdate
    fun onUpdate() {
        val currentTimestamp = Timestamp.from(Instant.now())
        updatedAt = currentTimestamp
    }
}