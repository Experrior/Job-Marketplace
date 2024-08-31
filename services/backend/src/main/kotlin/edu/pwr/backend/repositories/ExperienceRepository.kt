package edu.pwr.backend.repositories

import edu.pwr.backend.entities.Application
import edu.pwr.backend.entities.Experience
import edu.pwr.backend.entities.Job
import org.springframework.data.jpa.repository.JpaRepository

import org.springframework.stereotype.Repository

@Repository
interface ExperienceRepository : JpaRepository<Experience, Int> {


}