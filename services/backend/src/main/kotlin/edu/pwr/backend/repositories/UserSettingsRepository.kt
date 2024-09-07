package edu.pwr.backend.repositories


import edu.pwr.backend.entities.UserSettings
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.graphql.data.GraphQlRepository
import org.springframework.stereotype.Repository

@GraphQlRepository
interface UserSettingsRepository : JpaRepository<UserSettings, Int>, JpaSpecificationExecutor<UserSettings> {

}