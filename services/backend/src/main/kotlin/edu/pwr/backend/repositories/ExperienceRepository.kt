package edu.pwr.backend.repositories

import edu.pwr.backend.entities.Experience
import edu.pwr.backend.entities.UserProfile
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

@Repository
interface ExperienceRepository : JpaRepository<Experience, Int>, JpaSpecificationExecutor<Experience> {
    fun findByProfileId(profile: UserProfile, pageable: Pageable): Page<Experience>
}