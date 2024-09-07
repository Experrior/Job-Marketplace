package edu.pwr.backend.services

import edu.pwr.backend.entities.Education
import edu.pwr.backend.repositories.EducationRepository
import edu.pwr.backend.repositories.UserProfileRepository
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.sql.Timestamp
import java.time.Instant
import kotlin.jvm.optionals.getOrElse

@Service
class EducationService(
    private val educationRepository: EducationRepository,
    private val userProfileRepository: UserProfileRepository
) {

    fun getEducationById(educationId: Int): Education? {
        return educationRepository.findById(educationId).orElse(null)
    }

    fun getAllEducations(profileId: Int, limit: Int = 10, offset: Int = 0): List<Education> {
        val profile = userProfileRepository.findById(profileId).orElse(null) ?: return emptyList()
        return educationRepository.findByProfileId(profile, PageRequest.of(offset, limit)).content
    }

    fun createEducation(
        profileId: Int,
        institutionName: String,
        degree: String,
        startDate: Timestamp,
        endDate: Timestamp
    ): Education? {
        val profile = userProfileRepository.findById(profileId).orElse(null) ?: return null

        val newEducation = Education(
            profileId = profile,
            institutionName = institutionName,
            degree = degree,
            startDate = startDate,
            endDate = endDate,
            updatedAt = Timestamp.from(Instant.now())
        )

        return educationRepository.save(newEducation)
    }

    fun updateEducation(
        educationId: Int,
        institutionName: String? = null,
        degree: String? = null,
        startDate: Timestamp? = null,
        endDate: Timestamp? = null
    ): Education? {
        val existingEducation = educationRepository.findById(educationId).orElse(null) ?: return null

        institutionName?.let { existingEducation.institutionName = it }
        degree?.let { existingEducation.degree = it }
        startDate?.let { existingEducation.startDate = it }
        endDate?.let { existingEducation.endDate = it }
        existingEducation.updatedAt = Timestamp.from(Instant.now())

        return educationRepository.save(existingEducation)
    }

    fun deleteEducation(educationId: Int): Boolean {
        val education = educationRepository.findById(educationId).orElse(null) ?: return false
        educationRepository.delete(education)
        return true
    }
}
