package edu.pwr.backend.controllers

import edu.pwr.backend.entities.Job
import edu.pwr.backend.services.JobService
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class JobController(private val jobService: JobService) {

    @QueryMapping
    fun jobById(@Argument jobId: Int): Job? {
        return jobService.getJobById(jobId)
    }

    @QueryMapping
    fun allJobs(@Argument limit: Int? = 10, @Argument offset: Int? = 0): List<Job> {
        return jobService.getAllJobs(limit ?: 10, offset ?: 0)
    }

    @MutationMapping
    fun createJob(
        @Argument companyId: Int,
        @Argument jobTitle: String,
        @Argument jobDescription: String,
        @Argument requiredSkills: String,
        @Argument requiredExperience: String,
        @Argument location: String,
        @Argument salary: String? = null
    ): Job {
        return jobService.createJob(
            companyId, jobTitle, jobDescription, requiredSkills, requiredExperience, location, salary
        )
    }

    @MutationMapping
    fun updateJob(
        @Argument jobId: Int,
        @Argument companyId: Int? = null,
        @Argument jobTitle: String? = null,
        @Argument jobDescription: String? = null,
        @Argument requiredSkills: String? = null,
        @Argument requiredExperience: String? = null,
        @Argument location: String? = null,
        @Argument salary: String? = null
    ): Job? {
        return jobService.updateJob(jobId, companyId, jobTitle, jobDescription, requiredSkills, requiredExperience, location, salary)
    }

    @MutationMapping
    fun deleteJob(@Argument jobId: Int): Boolean {
        return jobService.deleteJob(jobId)
    }
}
