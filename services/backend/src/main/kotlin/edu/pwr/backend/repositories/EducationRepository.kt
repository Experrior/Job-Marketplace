package edu.pwr.backend.repositories


import edu.pwr.backend.entities.Education
import edu.pwr.backend.entities.UserProfile
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.graphql.data.GraphQlRepository


@GraphQlRepository
interface EducationRepository : JpaRepository<Education, Int> {
    fun findByProfileId(profileId: UserProfile, pageable: Pageable): Page<Education>
}