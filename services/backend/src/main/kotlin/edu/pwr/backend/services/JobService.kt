package edu.pwr.backend.services;


import edu.pwr.backend.dto.JobDTO
import edu.pwr.backend.repositories.JobRepository
import edu.pwr.backend.repositories.CompanyRepository
import jakarta.persistence.EntityNotFoundException
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
open class JobService(
    val jobRepository: JobRepository
) {
    fun get(jobId: Int): JobDTO {
        return jobRepository.getReferenceById(jobId).toDTO();
    }

    @Transactional
    open fun update(jobDTO: JobDTO): JobDTO {
        val toSave = jobDTO.toEntity()
        return jobRepository.save(toSave).toDTO()
    }
    @Transactional
    open fun delete(jobId: Int): Boolean {
        try {
            val job = jobRepository.getReferenceById(jobId);
            jobRepository.delete(job);
        }catch (e: EntityNotFoundException) {
            return false
        }
        return true
    }



}
