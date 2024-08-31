package edu.pwr.backend.dto

import edu.pwr.backend.entities.Company
import java.sql.Timestamp
import java.time.Instant


data class CompanyDTO(
    var companyId: Int? = null,
    var companyName: String = "default",
    var location: String = "default",
    var industry: String = "default",
    var description: String = "default",
    var verified: Boolean = false,
    var createdAt: Timestamp = Timestamp.from(Instant.now()),
    var updatedAt: Timestamp = Timestamp.from(Instant.now())
){
    fun toEntity(): Company {
        return Company(
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
}
