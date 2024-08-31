package edu.pwr.backend.repositories

import edu.pwr.backend.entities.*
import org.springframework.data.jpa.repository.JpaRepository

import org.springframework.stereotype.Repository

@Repository
interface UserSettingsRepository : JpaRepository<UserSettings, Int> {


}