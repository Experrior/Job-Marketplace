package edu.pwr.backend.services

import edu.pwr.backend.entities.Experience
import edu.pwr.backend.repositories.ExperienceRepository
import edu.pwr.backend.repositories.UserProfileRepository
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.sql.Timestamp
import java.time.Instant

@Service
class ExperienceService(
    private val experienceRepository: ExperienceRepository,
    private val userProfileRepository: UserProfileRepository
) {

    fun getExperienceById(experienceId: Int): Experience? {
        return experienceRepository.findById(experienceId).orElse(null)
    }

    fun getAllExperiences(profileId: Int, limit: Int = 10, offset: Int = 0): List<Experience> {
        val profile = userProfileRepository.findById(profileId).orElse(null) ?: return emptyList()
        return experienceRepository.findByProfileId(profile, PageRequest.of(offset, limit)).content
    }

    fun createExperience(
        profileId: Int,
        companyName: String,
        role: String,
        startDate: Timestamp,
        endDate: Timestamp
    ): Experience? {
        val profile = userProfileRepository.findById(profileId).orElse(null) ?: return null

        val newExperience = Experience(
            profileId = profile,
            companyName = companyName,
            role = role,
            startDate = startDate,
            endDate = endDate,
            updatedAt = Timestamp.from(Instant.now())
        )

        return experienceRepository.save(newExperience)
    }

    fun updateExperience(
        experienceId: Int,
        companyName: String? = null,
        role: String? = null,
        startDate: Timestamp? = null,
        endDate: Timestamp? = null
    ): Experience? {
        val existingExperience = experienceRepository.findById(experienceId).orElse(null) ?: return null

        companyName?.let { existingExperience.companyName = it }
        role?.let { existingExperience.role = it }
        startDate?.let { existingExperience.startDate = it }
        endDate?.let { existingExperience.endDate = it }
        existingExperience.updatedAt = Timestamp.from(Instant.now())

        return experienceRepository.save(existingExperience)
    }

    fun deleteExperience(experienceId: Int): Boolean {
        val experience = experienceRepository.findById(experienceId).orElse(null) ?: return false
        experienceRepository.delete(experience)
        return true
    }
}
