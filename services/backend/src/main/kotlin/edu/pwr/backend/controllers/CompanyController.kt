package edu.pwr.backend.controllers

import edu.pwr.backend.entities.Company
import edu.pwr.backend.services.CompanyService
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class CompanyController(private val companyService: CompanyService) {

    // Query to get a company by its ID
    @QueryMapping
    fun companyById(@Argument companyId: Int): Company? {
        return companyService.getCompanyById(companyId)
    }

    // Query to get all companies
    @QueryMapping
    fun allCompanies(): List<Company> {
        return companyService.getAllCompanies()
    }

    // Mutation to create a new company
    @MutationMapping
    fun createCompany(
        @Argument companyName: String,
        @Argument location: String,
        @Argument industry: String,
        @Argument description: String,
    ): Company {
        val newCompany = Company(
            companyName = companyName,
            location = location,
            industry = industry,
            description = description,
        )
        return companyService.createCompany(newCompany)
    }

    // Mutation to update a company
    @MutationMapping
    fun updateCompany(
        @Argument companyId: Int,
        @Argument companyName: String,
        @Argument location: String,
        @Argument industry: String,
        @Argument description: String,
        @Argument verified: Boolean
    ): Company? {
        val updatedCompany = Company(
            companyId = companyId,
            companyName = companyName,
            location = location,
            industry = industry,
            description = description,
            verified = verified,
        )
        return companyService.updateCompany(companyId, updatedCompany)
    }

    // Mutation to delete a company by its ID
    @MutationMapping
    fun deleteCompany(@Argument companyId: Int): Boolean {
        return companyService.deleteCompany(companyId)
    }
}