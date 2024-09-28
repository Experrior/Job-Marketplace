package edu.pwr.backend.controllers

import edu.pwr.backend.entities.Experience
import edu.pwr.backend.services.ExperienceService
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

import java.sql.Timestamp

@Controller
class ExperienceController(private val experienceService: ExperienceService) {

    @QueryMapping
    fun experienceById(@Argument experienceId: Int): Experience? {
        return experienceService.getExperienceById(experienceId)
    }

    @QueryMapping
    fun allExperiences(
        @Argument profileId: Int,
        @Argument limit: Int? = 10,
        @Argument offset: Int? = 0
    ): List<Experience> {
        return experienceService.getAllExperiences(profileId, limit ?: 10, offset ?: 0)
    }

    @MutationMapping
    fun createExperience(
        @Argument profileId: Int,
        @Argument companyName: String,
        @Argument role: String,
        @Argument startDate: String,
        @Argument endDate: String
    ): Experience? {
        val startDateTimestamp = Timestamp.valueOf(startDate)
        val endDateTimestamp = Timestamp.valueOf(endDate)
        return experienceService.createExperience(profileId, companyName, role, startDateTimestamp, endDateTimestamp)
    }

    @MutationMapping
    fun updateExperience(
        @Argument experienceId: Int,
        @Argument companyName: String? = null,
        @Argument role: String? = null,
        @Argument startDate: String? = null,
        @Argument endDate: String? = null
    ): Experience? {
        val startDateTimestamp = startDate?.let { Timestamp.valueOf(it) }
        val endDateTimestamp = endDate?.let { Timestamp.valueOf(it) }
        return experienceService.updateExperience(experienceId, companyName, role, startDateTimestamp, endDateTimestamp)
    }

    @MutationMapping
    fun deleteExperience(@Argument experienceId: Int): Boolean {
        return experienceService.deleteExperience(experienceId)
    }
}
