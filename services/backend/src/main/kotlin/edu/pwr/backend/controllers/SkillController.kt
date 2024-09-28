package edu.pwr.backend.controllers

import edu.pwr.backend.entities.Skill
import edu.pwr.backend.services.SkillService
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class SkillController(private val skillService: SkillService) {

    @QueryMapping
    fun skillById(@Argument skillId: Int): Skill? {
        return skillService.getSkillById(skillId)
    }

    @QueryMapping
    fun allSkills(
        @Argument profileId: Int,
        @Argument limit: Int? = 10,
        @Argument offset: Int? = 0
    ): List<Skill> {
        return skillService.getAllSkills(profileId, limit ?: 10, offset ?: 0)
    }

    @MutationMapping
    fun createSkill(
        @Argument profileId: Int,
        @Argument skillName: String,
        @Argument proficiencyLevel: String
    ): Skill? {
        return skillService.createSkill(profileId, skillName, proficiencyLevel)
    }

    @MutationMapping
    fun updateSkill(
        @Argument skillId: Int,
        @Argument skillName: String? = null,
        @Argument proficiencyLevel: String? = null
    ): Skill? {
        return skillService.updateSkill(skillId, skillName, proficiencyLevel)
    }

    @MutationMapping
    fun deleteSkill(@Argument skillId: Int): Boolean {
        return skillService.deleteSkill(skillId)
    }
}
