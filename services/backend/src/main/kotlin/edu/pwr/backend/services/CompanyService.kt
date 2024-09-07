package edu.pwr.backend.services

import edu.pwr.backend.entities.Company

import edu.pwr.backend.repositories.CompanyRepository
import org.springframework.stereotype.Service

@Service
class CompanyService(private val companyRepository: CompanyRepository) {

    fun createCompany(company: Company): Company {
        return companyRepository.save(company)
    }

    fun getCompanyById(companyId: Int): Company? {
        return companyRepository.findById(companyId).get()
    }

    fun getAllCompanies(): List<Company> {
        return companyRepository.findAll()
    }

    fun updateCompany(companyId: Int, updatedCompany: Company): Company? {
        val existingCompany = companyRepository.findById(companyId).orElse(null)

        return if (existingCompany != null) {
            existingCompany.apply {
                companyName = updatedCompany.companyName
                location = updatedCompany.location
                industry = updatedCompany.industry
                description = updatedCompany.description
                verified = updatedCompany.verified
                updatedAt = updatedCompany.updatedAt
            }
            companyRepository.save(existingCompany)
        } else {
            null  // Return null if the company with the given ID doesn't exist
        }
    }

    fun deleteCompany(companyId: Int): Boolean {
        val company = companyRepository.findById(companyId).orElse(null)
        return if (company != null) {
            companyRepository.delete(company)
            true
        } else
            false
    }
}
