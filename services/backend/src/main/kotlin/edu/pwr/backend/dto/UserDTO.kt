package edu.pwr.backend.dto

import edu.pwr.backend.entities.Company
import edu.pwr.backend.entities.User
import edu.pwr.backend.enums.ERole
import java.sql.Timestamp

data class UserDTO(
    var userId: Int? = null,
    var companyId: Company? = null,  // Storing the entire Company object
    var email: String = "",
    var firstName: String = "",
    var lastName: String = "",
    var phone: String = "",
    var role: ERole = ERole.APPLICANT,
    var isBlocked: Boolean = false,
    var emailVerified: Boolean = false,
    var employeeVerified: Boolean = false,
    var createdAt: Timestamp = Timestamp(0)
){


    fun toEntity(): User {
        return User(
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
