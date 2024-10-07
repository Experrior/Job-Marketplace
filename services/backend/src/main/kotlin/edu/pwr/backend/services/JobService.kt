package edu.pwr.backend.services

import edu.pwr.backend.config.TargetDataSource
import edu.pwr.backend.entities.Job
import edu.pwr.backend.repositories.JobRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.core.annotation.Order
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.sql.Timestamp
import java.time.Instant


@Service
open class JobService(private val applicationContext: ApplicationContext, private var jobRepository: JobRepository) {


    init {
        println("JobRepository injected: $jobRepository")
    }


    fun getJobById(jobId: Int): Job? {
        return jobRepository.findById(jobId).orElse(null)
    }

    @TargetDataSource
    fun getAllJobs(limit: Int = 10, offset: Int = 0): List<Job> {
        return jobRepository.findAll(PageRequest.of(offset, limit)).content
    }

    fun createJob(
        companyId: Int,
        jobTitle: String,
        jobDescription: String,
        requiredSkills: String,
        requiredExperience: String,
        location: String,
        salary: String? = null
    ): Job {
        val newJob = Job(
            companyId = companyId,
            jobTitle = jobTitle,
            jobDescription = jobDescription,
            requiredSkills = requiredSkills,
            requiredExperience = requiredExperience,
            location = location,
            salary = salary,
            createdAt = Timestamp.from(Instant.now())
        )
        return jobRepository.save(newJob)
    }

    fun updateJob(
        jobId: Int,
        companyId: Int? = null,
        jobTitle: String? = null,
        jobDescription: String? = null,
        requiredSkills: String? = null,
        requiredExperience: String? = null,
        location: String? = null,
        salary: String? = null
    ): Job? {
        val existingJob = jobRepository.findById(jobId).orElse(null) ?: return null

        companyId?.let { existingJob.companyId = it }
        jobTitle?.let { existingJob.jobTitle = it }
        jobDescription?.let { existingJob.jobDescription = it }
        requiredSkills?.let { existingJob.requiredSkills = it }
        requiredExperience?.let { existingJob.requiredExperience = it }
        location?.let { existingJob.location = it }
        salary?.let { existingJob.salary = it }

        return jobRepository.save(existingJob)
    }

    fun deleteJob(jobId: Int): Boolean {
        val job = jobRepository.findById(jobId).orElse(null) ?: return false
        jobRepository.delete(job)
        return true
    }
}
