package com.yourcompany.yourproject.graphql.resolvers


import edu.pwr.backend.entities.Company
import edu.pwr.backend.services.CompanyService
import org.springframework.stereotype.Component
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.beans.factory.annotation.Autowired

@Component
class CompanyResolver(@Autowired private val companyService: CompanyService) {

    @QueryMapping
    fun companyById(companyId: Int): Company {
        return companyService.get(companyId)
    }

    @MutationMapping
    fun createCompany(companyName: String, location: String, industry: String, description: String): Company {
        return companyService.update(companyName, location, industry, description)
    }
}
