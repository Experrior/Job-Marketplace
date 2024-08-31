package edu.pwr.backend.entities

import edu.pwr.backend.dto.UserDTO
import edu.pwr.backend.enums.ERole
import jakarta.persistence.*
import java.sql.Timestamp
import java.time.Instant

@Entity(name = "users")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var userId: Int? = null,
    @ManyToOne
    @JoinColumn(name = "companyId", referencedColumnName = "companyId")
    var companyId: Company? = null,
    @Column(nullable = false)
    var email: String = "",
    @Column(nullable = false)
    var firstName: String = "",
    @Column(nullable = false)
    var lastName: String = "",
    @Column(nullable = true)
    var phone: String = "",
    @Column(nullable = false, columnDefinition = "role::smallint")
    var role: ERole = ERole.APPLICANT,
    @Column(nullable = false)
    var isBlocked: Boolean = false,
    @Column(nullable = false)
    var emailVerified: Boolean = false,
    @Column(nullable = false)
    var employeeVerified: Boolean = false,
    @Column(nullable = false)
    var createdAt: Timestamp = Timestamp(0),

    ) {

    @PrePersist
    fun onCreate() {
        val currentTimestamp = Timestamp.from(Instant.now())
        createdAt = currentTimestamp
    }

    fun toDTO(): UserDTO {
        return UserDTO(
            userId = this.userId,
            companyId = this.companyId,
            email = this.email,
            firstName = this.firstName,
            lastName = this.lastName,
            phone = this.phone,
            role = this.role,
            isBlocked = this.isBlocked,
            emailVerified = this.emailVerified,
            employeeVerified = this.employeeVerified,
            createdAt = this.createdAt
        )
    }
}