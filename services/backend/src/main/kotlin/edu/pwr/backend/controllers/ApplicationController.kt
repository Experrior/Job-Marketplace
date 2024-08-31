package edu.pwr.backend.controllers


import edu.pwr.backend.dto.ApplicationCreationDTO
import edu.pwr.backend.dto.ApplicationDTO
import edu.pwr.backend.entities.Application
import edu.pwr.backend.services.ApplicationService

import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.graphql.data.method.annotation.SchemaMapping
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import java.awt.print.Book


@Controller

class ApplicationController (val applicationService: ApplicationService) {

    @QueryMapping
    fun getById(@Argument id: Int): Application {
        return applicationService.get(id)
    }


//    @GetMapping("/application/{applicationId}")
//    fun get( @PathVariable applicationId: Int) : ResponseEntity<ApplicationDTO> {
//        return ResponseEntity.ok(applicationService.get(applicationId))
//    }
//
//    @PostMapping("/application/update")
//    fun update( @RequestBody applicationCreationDTO: ApplicationCreationDTO) : ResponseEntity<ApplicationDTO> {
//        return ResponseEntity.ok(applicationService.update(applicationCreationDTO))
//    }
//
//    @DeleteMapping("/application/{applicationId}")
//    fun delete( @PathVariable applicationId: Int) : ResponseEntity<Unit> {
//        return if (applicationService.delete(applicationId)) {
//            ResponseEntity.ok().build()
//        } else {
//            ResponseEntity.notFound().build()
//        }
//    }

}