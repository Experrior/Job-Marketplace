package edu.pwr.backend.controllers


import edu.pwr.backend.dto.EducationDTO
import edu.pwr.backend.dto.CompanyDTO
import edu.pwr.backend.services.EducationService
import edu.pwr.backend.services.CompanyService



import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController

class EducationController (val educationService: EducationService) {

    @GetMapping("/education/{educationId}")
    fun get( @PathVariable educationId: Int) : ResponseEntity<EducationDTO> {
        return ResponseEntity.ok(educationService.get(educationId))
    }

    @PostMapping("/education/update")
    fun update( @RequestBody educationDTO: EducationDTO) : ResponseEntity<EducationDTO> {
        return ResponseEntity.ok(educationService.update(educationDTO))
    }

    @DeleteMapping("/education/{educationId}")
    fun delete( @PathVariable educationId: Int) : ResponseEntity<Unit> {
        return if (educationService.delete(educationId)) {
            ResponseEntity.ok().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }

}