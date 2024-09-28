package edu.pwr.backend.repositories

import edu.pwr.backend.entities.Job
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface JobRepository : JpaRepository<Job, Int>, JpaSpecificationExecutor<Job> {

}