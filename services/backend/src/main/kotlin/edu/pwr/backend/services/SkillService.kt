package edu.pwr.backend.services

import edu.pwr.backend.entities.Skill
import edu.pwr.backend.repositories.SkillRepository
import edu.pwr.backend.repositories.UserProfileRepository
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.sql.Timestamp
import java.time.Instant

@Service
class SkillService(
    private val skillRepository: SkillRepository,
    private val userProfileRepository: UserProfileRepository
) {

    fun getSkillById(skillId: Int): Skill? {
        return skillRepository.findById(skillId).orElse(null)
    }

    fun getAllSkills(profileId: Int, limit: Int = 10, offset: Int = 0): List<Skill> {
        val profile = userProfileRepository.findById(profileId).orElse(null) ?: return emptyList()
        return skillRepository.findByProfileId(profile, PageRequest.of(offset, limit)).content
    }

    fun createSkill(
        profileId: Int,
        skillName: String,
        proficiencyLevel: String
    ): Skill? {
        val profile = userProfileRepository.findById(profileId).orElse(null) ?: return null

        val newSkill = Skill(
            profileId = profile,
            skillName = skillName,
            proficiencyLevel = proficiencyLevel,
            updatedAt = Timestamp.from(Instant.now())
        )

        return skillRepository.save(newSkill)
    }

    fun updateSkill(skillId: Int, skillName: String? = null, proficencyLevel: String? = null): Skill? {
        val existingSkill = skillRepository.findById(skillId).orElse(null) ?: return null

        skillName?.let { existingSkill.skillName = it }
        proficencyLevel?.let { existingSkill.proficiencyLevel = it }
        existingSkill.updatedAt = Timestamp.from(Instant.now())

        return skillRepository.save(existingSkill)
    }

    fun deleteSkill(skillId: Int): Boolean {
        val skill = skillRepository.findById(skillId).orElse(null) ?: return false
        skillRepository.delete(skill)
        return true
    }
}
