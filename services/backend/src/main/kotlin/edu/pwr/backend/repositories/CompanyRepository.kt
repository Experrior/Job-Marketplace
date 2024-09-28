package edu.pwr.backend.repositories

import edu.pwr.backend.entities.Company
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

import org.springframework.stereotype.Repository

@Repository
interface CompanyRepository : JpaRepository<Company, Int>, JpaSpecificationExecutor<Company> {

}