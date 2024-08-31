package edu.pwr.backend.controllers


import edu.pwr.backend.dto.CompanyDTO

import edu.pwr.backend.services.CompanyService



import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController

class CompanyController (private val companyService: CompanyService) {

    @GetMapping("/company/{companyId}")
    fun get( @PathVariable companyId: Int) : ResponseEntity<CompanyDTO> {
        return ResponseEntity.ok(companyService.get(companyId))
    }

    @PostMapping("/company/update")
    fun update( @RequestBody companyDTO: CompanyDTO) : ResponseEntity<CompanyDTO> {
        return ResponseEntity.ok(companyService.update(companyDTO))
    }

    @DeleteMapping("/company/{companyId}")
    fun delete( @PathVariable companyId: Int) : ResponseEntity<Unit> {
        return if (companyService.delete(companyId)) {
            ResponseEntity.ok().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }

    
    @GetMapping("/user/component/{companyName}")
    fun findAllByCompanyName( @PathVariable companyName: String) : ResponseEntity<List<CompanyDTO>> {
        return ResponseEntity.ok(companyService.findAllByCompanyName(companyName))
    }


}