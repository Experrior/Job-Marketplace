package edu.pwr.backend.services;


import edu.pwr.backend.dto.CompanyDTO
import edu.pwr.backend.repositories.CompanyRepository
import jakarta.persistence.EntityNotFoundException
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
open class CompanyService(
    private val companyRepository: CompanyRepository
) {

    fun get(companyId: Int): CompanyDTO {
        return companyRepository.getReferenceById(companyId).toDTO();
    }

    @Transactional
    open fun update(companyDTO: CompanyDTO): CompanyDTO {
        val toSave = companyDTO.toEntity()
        return companyRepository.save(toSave).toDTO()
    }
    @Transactional
    open fun delete(companyId: Int): Boolean {
        try {
            val company = companyRepository.getReferenceById(companyId);
            companyRepository.delete(company);
        }catch (e: EntityNotFoundException) {
            return false
        }
        return true
    }


    fun findAllByCompanyName(companyName: String): List<CompanyDTO> {
        var matchingCompanies = companyRepository.findByCompanyNameContaining(companyName);
        return matchingCompanies.map { it.toDTO() }
    }

}
