package edu.pwr.backend.services;


import edu.pwr.backend.dto.EducationDTO
import edu.pwr.backend.repositories.EducationRepository
import edu.pwr.backend.repositories.CompanyRepository
import jakarta.persistence.EntityNotFoundException
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
open class EducationService(
    val educationRepository: EducationRepository
) {
    fun get(educationId: Int): EducationDTO {
        return educationRepository.getReferenceById(educationId).toDTO();
    }

    @Transactional
    open fun update(educationDTO: EducationDTO): EducationDTO {
        val toSave = educationDTO.toEntity();
        return educationRepository.save(toSave).toDTO()
    }
    @Transactional
    open fun delete(educationId: Int): Boolean {
        try {
            val education = educationRepository.getReferenceById(educationId);
            educationRepository.delete(education);
        }catch (e: EntityNotFoundException) {
            return false
        }
        return true
    }



}
