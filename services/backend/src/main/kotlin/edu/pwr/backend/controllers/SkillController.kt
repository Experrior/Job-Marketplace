package edu.pwr.backend.controllers


import edu.pwr.backend.dto.SkillDTO
import edu.pwr.backend.dto.CompanyDTO
import edu.pwr.backend.services.SkillService
import edu.pwr.backend.services.CompanyService



import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController

class SkillController (val skillService: SkillService) {

    @GetMapping("/skill/{skillId}")
    fun get( @PathVariable skillId: Int) : ResponseEntity<SkillDTO> {
        return ResponseEntity.ok(skillService.get(skillId))
    }

    @PostMapping("/skill/update")
    fun update( @RequestBody skillDTO: SkillDTO) : ResponseEntity<SkillDTO> {
        return ResponseEntity.ok(skillService.update(skillDTO))
    }

    @DeleteMapping("/skill/{skillId}")
    fun delete( @PathVariable skillId: Int) : ResponseEntity<Unit> {
        return if (skillService.delete(skillId)) {
            ResponseEntity.ok().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }

}