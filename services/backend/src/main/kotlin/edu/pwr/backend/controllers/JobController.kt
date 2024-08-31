package edu.pwr.backend.controllers


import edu.pwr.backend.dto.JobDTO
import edu.pwr.backend.dto.CompanyDTO
import edu.pwr.backend.services.JobService
import edu.pwr.backend.services.CompanyService



import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController

class JobController (val jobService: JobService) {

    @GetMapping("/job/{jobId}")
    fun get( @PathVariable jobId: Int) : ResponseEntity<JobDTO> {
        return ResponseEntity.ok(jobService.get(jobId))
    }

    @PostMapping("/job/update")
    fun update( @RequestBody jobDTO: JobDTO) : ResponseEntity<JobDTO> {
        return ResponseEntity.ok(jobService.update(jobDTO))
    }

    @DeleteMapping("/job/{jobId}")
    fun delete( @PathVariable jobId: Int) : ResponseEntity<Unit> {
        return if (jobService.delete(jobId)) {
            ResponseEntity.ok().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }

}