package edu.pwr.backend.controllers


import edu.pwr.backend.dto.UserSettingsDTO
import edu.pwr.backend.dto.CompanyDTO
import edu.pwr.backend.services.UserSettingsService
import edu.pwr.backend.services.CompanyService



import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController()

class UserSettingsController (val userSettingsService: UserSettingsService) {

    @GetMapping("/userSettings/{userSettingsId}")
    fun get( @PathVariable userSettingsId: Int) : ResponseEntity<UserSettingsDTO> {
        return ResponseEntity.ok(userSettingsService.get(userSettingsId))
    }

    @PostMapping("/userSettings/update")
    fun update( @RequestBody userSettingsDTO: UserSettingsDTO) : ResponseEntity<UserSettingsDTO> {
        return ResponseEntity.ok(userSettingsService.update(userSettingsDTO))
    }

    @DeleteMapping("/userSettings/{userSettingsId}")
    fun delete( @PathVariable userSettingsId: Int) : ResponseEntity<Unit> {
        return if (userSettingsService.delete(userSettingsId)) {
            ResponseEntity.ok().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }

}