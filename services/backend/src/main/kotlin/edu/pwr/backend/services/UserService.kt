package edu.pwr.backend.services

import edu.pwr.backend.entities.User
import edu.pwr.backend.repositories.UserRepository
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.sql.Timestamp
import java.time.Instant

@Service
class UserService(private val userRepository: UserRepository) {

    fun getUserById(userId: Int): User? {
        return userRepository.findById(userId).orElse(null)
    }

    fun getAllUsers(limit: Int = 10, offset: Int = 0): List<User> {
        return userRepository.findAll(PageRequest.of(offset / limit, limit)).content
    }

    fun createUser(email: String, firstName: String, lastName: String, phone: String?, role: String?, passwordHash: String): User {
        val user = User(
            email = email,
            firstName = firstName,
            lastName = lastName,
            phone = phone ?: "",
            role = role ?: "applicant",
            passwordHash = passwordHash,
        )
        return userRepository.save(user)
    }


    fun updateUser(
        userId: Int,
        email: String? = null,
        firstName: String? = null,
        lastName: String? = null,
        phone: String? = null,
        role: String? = null,
        isBlocked: Boolean? = null,
        emailVerified: Boolean? = null,
        employeeVerified: Boolean? = null
    ): User? {
        val existingUser = userRepository.findById(userId).orElse(null) ?: return null

        email?.let { existingUser.email = it }
        firstName?.let { existingUser.firstName = it }
        lastName?.let { existingUser.lastName = it }
        phone?.let { existingUser.phone = it }
        role?.let { existingUser.role = it }
        isBlocked?.let { existingUser.isBlocked = it }
        emailVerified?.let { existingUser.emailVerified = it }
        employeeVerified?.let { existingUser.employeeVerified = it }

        existingUser.createdAt = Timestamp.from(Instant.now()) // Optionally update the timestamp
        return userRepository.save(existingUser)
    }

    fun deleteUser(userId: Int): Boolean {
        return if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId)
            true
        } else {
            false
        }
    }
}
