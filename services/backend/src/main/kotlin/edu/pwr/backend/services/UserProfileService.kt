package edu.pwr.backend.services;


import edu.pwr.backend.dto.UserProfileDTO
import edu.pwr.backend.repositories.UserProfileRepository
import edu.pwr.backend.repositories.CompanyRepository
import jakarta.persistence.EntityNotFoundException
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
open class UserProfileService(
    val userProfileRepository: UserProfileRepository
) {
    fun get(userProfileId: Int): UserProfileDTO {
        return userProfileRepository.getReferenceById(userProfileId).toDTO();
    }

    @Transactional
    open fun update(userProfileDTO: UserProfileDTO): UserProfileDTO {
        val toSave = userProfileDTO.toEntity()
        return userProfileRepository.save(toSave).toDTO()
    }
    @Transactional
    open fun delete(userProfileId: Int): Boolean {
        try {
            val userProfile = userProfileRepository.getReferenceById(userProfileId);
            userProfileRepository.delete(userProfile);
        }catch (e: EntityNotFoundException) {
            return false
        }
        return true
    }



}
