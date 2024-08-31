package edu.pwr.backend.repositories

import edu.pwr.backend.entities.Application
import edu.pwr.backend.entities.Job
import edu.pwr.backend.entities.User
import edu.pwr.backend.entities.UserProfile
import org.springframework.data.jpa.repository.JpaRepository

import org.springframework.stereotype.Repository

@Repository
interface UserProfileRepository : JpaRepository<UserProfile, Int> {


}