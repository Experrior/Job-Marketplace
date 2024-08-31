package edu.pwr.backend.controllers


import edu.pwr.backend.dto.UserDTO
import edu.pwr.backend.dto.CompanyDTO
import edu.pwr.backend.services.UserService
import edu.pwr.backend.services.CompanyService



import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController

class UserController (val userService: UserService) {

    @GetMapping("/user/{userId}")
    fun get( @PathVariable userId: Int) : ResponseEntity<UserDTO> {
        return ResponseEntity.ok(userService.get(userId))
    }

    @PostMapping("/user/update")
    fun update( @RequestBody userDTO: UserDTO) : ResponseEntity<UserDTO> {
        return ResponseEntity.ok(userService.update(userDTO))
    }

    @DeleteMapping("/user/{userId}")
    fun delete( @PathVariable userId: Int) : ResponseEntity<Unit> {
        return if (userService.delete(userId)) {
            ResponseEntity.ok().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }

}