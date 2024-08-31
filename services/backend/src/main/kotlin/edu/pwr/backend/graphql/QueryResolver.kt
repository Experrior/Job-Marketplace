//// src/main/kotlin/edu/pwr/backend/graphql/QueryResolver.kt
//
//package edu.pwr.backend.graphql
//
//import com.coxautodev.graphql.tools.GraphQLQueryResolver
//import edu.pwr.backend.entities.Application
//import edu.pwr.backend.entities.User
//import edu.pwr.backend.entities.Job
//import edu.pwr.backend.entities.Company
//import edu.pwr.backend.services.ApplicationService
//import edu.pwr.backend.services.UserService
//import edu.pwr.backend.services.JobService
//import edu.pwr.backend.services.CompanyService
//import org.springframework.stereotype.Component
//
//@Component
//class QueryResolver(
//    private val applicationService: ApplicationService,
//    private val userService: UserService,
//    private val jobService: JobService,
//    private val companyService: CompanyService
//) : GraphQLQueryResolver {
//
//    // Applications
//    fun applications(): List<Application> = applicationService.getAllApplications()
//    fun applicationById(applicationId: Int): Application = applicationService.getApplicationById(applicationId)
//
//    // Users
//    fun users(): List<User> = userService.getAllUsers()
//    fun userById(userId: Int): User = userService.getUserById(userId)
//
//    // Jobs
//    fun jobs(): List<Job> = jobService.getAllJobs()
//    fun jobById(jobId: Int): Job = jobService.getJobById(jobId)
//
//    // Companies
//    fun companies(): List<Company> = companyService.getAllCompanies()
//    fun companyById(companyId: Int): Company = companyService.getCompanyById(companyId)
//}
