package edu.pwr.backend.controllers

import edu.pwr.backend.entities.Application
import edu.pwr.backend.services.ApplicationService
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class ApplicationController(private val applicationService: ApplicationService) {

    @QueryMapping
    fun applicationById(@Argument applicationId: Int): Application? {
        return applicationService.getApplicationById(applicationId)
    }

    @QueryMapping
    fun allApplications(@Argument limit: Int? = 10, @Argument offset: Int? = 0): List<Application> {
        return applicationService.getAllApplications(limit ?: 10, offset ?: 0)
    }

    @MutationMapping
    fun createApplication(
        @Argument userId: Int,
        @Argument jobId: Int,
    ): Application? {
        return applicationService.createApplication(userId, jobId)
    }

    @MutationMapping
    fun updateApplication(
        @Argument applicationId: Int,
        @Argument status: String? = null
    ): Application? {
        return applicationService.updateApplication(applicationId, status)
    }

    @MutationMapping
    fun deleteApplication(@Argument applicationId: Int): Boolean {
        return applicationService.deleteApplication(applicationId)
    }
}
