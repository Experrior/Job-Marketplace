package edu.pwr.backend.services;


import edu.pwr.backend.dto.UserSettingsDTO
import edu.pwr.backend.repositories.UserSettingsRepository
import edu.pwr.backend.repositories.CompanyRepository
import jakarta.persistence.EntityNotFoundException
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
open class UserSettingsService(
    private val userSettingsRepository: UserSettingsRepository
) {
    fun get(userSettingsId: Int): UserSettingsDTO {
        val userSettings = userSettingsRepository.findById(userSettingsId).orElseThrow { EntityNotFoundException("User with ID $userSettingsId not found") }
        return userSettings.toDTO()
    }

    @Transactional
    open fun update(userSettingsDTO: UserSettingsDTO): UserSettingsDTO {
        val toSave = userSettingsDTO.toEntity()
        return userSettingsRepository.save(toSave).toDTO()
    }
    @Transactional
    open fun delete(userSettingsId: Int): Boolean {
        try {
            val userSettings = userSettingsRepository.getReferenceById(userSettingsId);
            userSettingsRepository.delete(userSettings);
        }catch (e: EntityNotFoundException) {
            return false
        }
        return true
    }



}
