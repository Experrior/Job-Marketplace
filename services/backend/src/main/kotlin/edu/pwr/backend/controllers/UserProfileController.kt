package edu.pwr.backend.controllers


import edu.pwr.backend.dto.UserProfileDTO
import edu.pwr.backend.dto.CompanyDTO
import edu.pwr.backend.services.UserProfileService
import edu.pwr.backend.services.CompanyService



import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController()

class UserProfileController (val userProfileService: UserProfileService) {

    @GetMapping("/userProfile/{userProfileId}")
    fun get( @PathVariable userProfileId: Int) : ResponseEntity<UserProfileDTO> {
        return ResponseEntity.ok(userProfileService.get(userProfileId))
    }

    @PostMapping("/userProfile/update")
    fun update( @RequestBody userProfileDTO: UserProfileDTO) : ResponseEntity<UserProfileDTO> {
        return ResponseEntity.ok(userProfileService.update(userProfileDTO))
    }

    @DeleteMapping("/userProfile/{userProfileId}")
    fun delete( @PathVariable userProfileId: Int) : ResponseEntity<Unit> {
        return if (userProfileService.delete(userProfileId)) {
            ResponseEntity.ok().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }

}