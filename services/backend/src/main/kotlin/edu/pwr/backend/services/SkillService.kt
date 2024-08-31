package edu.pwr.backend.services;


import edu.pwr.backend.dto.SkillDTO
import edu.pwr.backend.repositories.SkillRepository
import edu.pwr.backend.repositories.CompanyRepository
import jakarta.persistence.EntityNotFoundException
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
open class SkillService(
    val skillRepository: SkillRepository
) {
    fun get(skillId: Int): SkillDTO {
        return skillRepository.getReferenceById(skillId).toDTO();
    }

    @Transactional
    open fun update(skillDTO: SkillDTO): SkillDTO {
        val toSave = skillDTO.toEntity()
        return skillRepository.save(toSave).toDTO()
    }
    @Transactional
    open fun delete(skillId: Int): Boolean {
        try {
            val skill = skillRepository.getReferenceById(skillId);
            skillRepository.delete(skill);
        }catch (e: EntityNotFoundException) {
            return false
        }
        return true
    }



}
