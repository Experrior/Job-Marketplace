package edu.pwr.backend.repositories

import edu.pwr.backend.entities.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.graphql.data.GraphQlRepository

@GraphQlRepository
interface UserRepository : JpaRepository<User, Int>, JpaSpecificationExecutor<User> {

}