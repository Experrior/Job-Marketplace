package edu.pwr.backend.controllers

import edu.pwr.backend.entities.Education
import edu.pwr.backend.services.EducationService
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import java.sql.Timestamp

@Controller
class EducationController(private val educationService: EducationService) {

    @QueryMapping
    fun educationById(@Argument educationId: Int): Education? {
        return educationService.getEducationById(educationId)
    }

    @QueryMapping
    fun allEducations(
        @Argument profileId: Int,
        @Argument limit: Int? = 10,
        @Argument offset: Int? = 0
    ): List<Education> {
        return educationService.getAllEducations(profileId, limit ?: 10, offset ?: 0)
    }

    @MutationMapping
    fun createEducation(
        @Argument profileId: Int,
        @Argument institutionName: String,
        @Argument degree: String,
        @Argument startDate: String,
        @Argument endDate: String
    ): Education? {
        val startDateTimestamp = Timestamp.valueOf(startDate)
        val endDateTimestamp = Timestamp.valueOf(endDate)
        return educationService.createEducation(profileId, institutionName, degree, startDateTimestamp, endDateTimestamp)
    }

    @MutationMapping
    fun updateEducation(
        @Argument educationId: Int,
        @Argument institutionName: String? = null,
        @Argument degree: String? = null,
        @Argument startDate: String? = null,
        @Argument endDate: String? = null
    ): Education? {
        val startDateTimestamp = startDate?.let { Timestamp.valueOf(it) }
        val endDateTimestamp = endDate?.let { Timestamp.valueOf(it) }
        return educationService.updateEducation(educationId, institutionName, degree, startDateTimestamp, endDateTimestamp)
    }

    @MutationMapping
    fun deleteEducation(@Argument educationId: Int): Boolean {
        return educationService.deleteEducation(educationId)
    }
}
