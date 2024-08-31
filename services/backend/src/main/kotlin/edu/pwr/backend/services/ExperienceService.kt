package edu.pwr.backend.services;


import edu.pwr.backend.dto.ExperienceDTO
import edu.pwr.backend.repositories.ExperienceRepository
import edu.pwr.backend.repositories.CompanyRepository
import jakarta.persistence.EntityNotFoundException
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
open class ExperienceService(
    val experienceRepository: ExperienceRepository
) {
    fun get(experienceId: Int): ExperienceDTO {
        return experienceRepository.getReferenceById(experienceId).toDTO();
    }

    @Transactional
    open fun update(experienceDTO: ExperienceDTO): ExperienceDTO {
        val toSave = experienceDTO.toEntity()
        return experienceRepository.save(toSave).toDTO()
    }
    @Transactional
    open fun delete(experienceId: Int): Boolean {
        try {
            val experience = experienceRepository.getReferenceById(experienceId);
            experienceRepository.delete(experience);
        }catch (e: EntityNotFoundException) {
            return false
        }
        return true
    }



}
