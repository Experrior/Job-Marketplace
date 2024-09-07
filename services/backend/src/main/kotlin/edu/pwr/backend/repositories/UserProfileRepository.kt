package edu.pwr.backend.repositories

import edu.pwr.backend.entities.UserProfile
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.graphql.data.GraphQlRepository
import org.springframework.stereotype.Repository

@GraphQlRepository
interface UserProfileRepository : JpaRepository<UserProfile, Int>, JpaSpecificationExecutor<UserProfile> {

}