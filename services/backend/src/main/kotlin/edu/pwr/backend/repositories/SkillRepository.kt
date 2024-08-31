package edu.pwr.backend.repositories

import edu.pwr.backend.entities.Application
import edu.pwr.backend.entities.Job
import edu.pwr.backend.entities.Skill
import org.springframework.data.jpa.repository.JpaRepository

import org.springframework.stereotype.Repository

@Repository
interface SkillRepository : JpaRepository<Skill, Int> {


}