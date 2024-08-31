package edu.pwr.backend.controllers


import edu.pwr.backend.dto.ExperienceDTO
import edu.pwr.backend.dto.CompanyDTO
import edu.pwr.backend.services.ExperienceService
import edu.pwr.backend.services.CompanyService



import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController

class ExperienceController (val experienceService: ExperienceService) {

    @GetMapping("/experience/{experienceId}")
    fun get( @PathVariable experienceId: Int) : ResponseEntity<ExperienceDTO> {
        return ResponseEntity.ok(experienceService.get(experienceId))
    }

    @PostMapping("/experience/update")
    fun update( @RequestBody experienceDTO: ExperienceDTO) : ResponseEntity<ExperienceDTO> {
        return ResponseEntity.ok(experienceService.update(experienceDTO))
    }

    @DeleteMapping("/experience/{experienceId}")
    fun delete( @PathVariable experienceId: Int) : ResponseEntity<Unit> {
        return if (experienceService.delete(experienceId)) {
            ResponseEntity.ok().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }

}