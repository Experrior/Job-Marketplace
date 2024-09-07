package edu.pwr.backend.repositories

import edu.pwr.backend.entities.Application
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository


@Repository
interface ApplicationRepository : JpaRepository<Application, Int>, JpaSpecificationExecutor<Application> {

}