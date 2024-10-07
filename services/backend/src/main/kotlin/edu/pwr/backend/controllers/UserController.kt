package edu.pwr.backend.controllers

import edu.pwr.backend.entities.User
import edu.pwr.backend.services.UserService
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class UserController(private val userService: UserService) {

    @QueryMapping
    fun userById(@Argument userId: Int): User? {
        return userService.getUserById(userId)
    }

    @QueryMapping
    fun allUsers(@Argument limit: Int?, @Argument offset: Int?): List<User> {
        return userService.getAllUsers(limit ?: 10, offset ?: 0)
    }

    @MutationMapping
    fun createUser(
        @Argument email: String,
        @Argument firstName: String,
        @Argument lastName: String,
        @Argument passwordHash: String,
        @Argument phone: String? = null,
        @Argument role: String? = null,
    ): User {
        return userService.createUser(email, firstName, lastName, phone, role, passwordHash)
    }

    @MutationMapping
    fun updateUser(
        @Argument userId: Int,
        @Argument email: String? = null,
        @Argument firstName: String? = null,
        @Argument lastName: String? = null,
        @Argument phone: String? = null,
        @Argument role: String? = null,
        @Argument isBlocked: Boolean? = null,
        @Argument emailVerified: Boolean? = null,
        @Argument employeeVerified: Boolean? = null
    ): User? {
        return userService.updateUser(userId, email, firstName, lastName, phone, role, isBlocked, emailVerified, employeeVerified)
    }

    @MutationMapping
    fun deleteUser(@Argument userId: Int): Boolean {
        return userService.deleteUser(userId)
    }
}
